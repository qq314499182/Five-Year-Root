package com.five.year.fiveyearblog.service;

import com.five.year.fiveyearblog.base.BaseService;
import com.five.year.fiveyearblog.cache.BlogUserCache;
import com.five.year.fiveyearblog.entity.BlogArticle;
import com.five.year.fiveyearblog.entity.BlogUser;
import com.five.year.fiveyearblog.mapper.BlogArticleMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Description
 * @Author zhaoke <zhaokep@yonyou.com>
 * @Version V1.0.0
 * @Date 2018/12/25
 */
@Service
public class BlogArticleService extends BaseService<BlogArticle> {

    @Autowired
    private BlogArticleMapper mapper;

    @Autowired
    private BlogUserCache blogUserCache;

    public BlogArticle findOne(String id) {
        return mapper.selectByPrimaryKey(id);
    }

    public List<BlogArticle> findByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize,"opened,ts desc");
        return mapper.selectAll();
    }

    public List<BlogArticle> findAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return mapper.selectAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public String create(BlogArticle blogArticle) {
        BlogArticle fillBlogArticle = this.fillParam(blogArticle);
        String contentList = this.getContentList(fillBlogArticle.getContent());
        fillBlogArticle.setContentList(contentList);
        int insert = mapper.insert(fillBlogArticle);
        if(insert == 1){
            return "OK";
        }else {
            return "ERROR";
        }
    }

    private String getContentList(@NotNull(message = "content内容不能为空") String content){
        Document document = Jsoup.parseBodyFragment(content);
        String contentList = document.selectFirst("p").text();
        if(contentList.length() > 200){
            contentList = contentList.substring(0, 201)+"...........";
        }
        return contentList;
    }

    @Transactional(rollbackFor = Exception.class)
    public String point(String id) {
        BlogArticle blogArticle = mapper.selectByPrimaryKey(id);
        blogArticle.setPointNum(blogArticle.getPointNum().add(BigDecimal.ONE));
        mapper.updateByPrimaryKeySelective(blogArticle);
        return "OK";
    }

    /**
     * 返回指定用户文章信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<BlogArticle> findByUserPage(String cookie, Integer pageNum, Integer pageSize) {
        if(blogUserCache.isKeyExists(cookie)){
            BlogUser blogUser = blogUserCache.get(cookie);
            PageHelper.startPage(pageNum,pageSize);
            List<BlogArticle> list = mapper.selectByUser(blogUser.getCreateId());
            return new PageInfo<>(list);
        }else {
            return new PageInfo<>();
        }
    }
}
