package com.five.year.fiveyearblog.service;

import com.five.year.fiveyearblog.base.BaseEntity;
import com.five.year.fiveyearblog.base.BaseService;
import com.five.year.fiveyearblog.entity.BlogArticle;
import com.five.year.fiveyearblog.mapper.BlogArticleMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public BlogArticle findOne(String id) {
        return mapper.selectByPrimaryKey(id);
    }

    public List<BlogArticle> findByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return mapper.selectAll();
    }

    public List<BlogArticle> findAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return mapper.selectAll();
    }

    @Transactional
    public String create(BlogArticle blogArticle) {
        BlogArticle fillBlogArticle = this.fillParam(blogArticle);
        int insert = mapper.insert(fillBlogArticle);
        System.out.println(insert);
        return "OK";
    }
}
