package com.five.year.fiveyearblog.web;

import com.five.year.fiveyearblog.entity.BlogArticle;
import com.five.year.fiveyearblog.service.BlogArticleService;
import com.five.year.fiveyearblog.util.JSONUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * @Description
 * @Author zhaoke <zhaokep@yonyou.com>
 * @Version V1.0.0
 * @Date 2018/12/25
 */
@RestController
@RequestMapping("/blog-article")
public class BlogArticleController {

    @Autowired
    private BlogArticleService blogArticleService;

    @GetMapping
    public BlogArticle findOne(String id){
        return blogArticleService.findOne(id);
    }

    @PostMapping
    public List<BlogArticle> findAll(@RequestBody String json){
        try {
            Map map = JSONUtils.jsonToMap(json);
            Integer pageNum = (Integer)map.get("pageNum");
            Integer pageSize = (Integer)map.get("pageSize");
            Thread.sleep(2000);
            return blogArticleService.findByPage(pageNum,pageSize);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("findAll")
    public List<BlogArticle> findAll(){
        return blogArticleService.findAll(0,5);
    }

}
