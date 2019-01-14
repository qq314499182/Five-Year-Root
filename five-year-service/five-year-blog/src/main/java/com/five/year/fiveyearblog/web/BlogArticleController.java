package com.five.year.fiveyearblog.web;

import com.five.year.fiveyearblog.entity.BlogArticle;
import com.five.year.fiveyearblog.service.BlogArticleService;
import com.five.year.fiveyearblog.util.JSONUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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

    @GetMapping("findOne")
    public BlogArticle findOne(String id){
        return blogArticleService.findOne(id);
    }

    @PostMapping
    public List<BlogArticle> findAll(@RequestBody Map map){
        Integer pageNum = (Integer)map.get("pageNum");
        Integer pageSize = (Integer)map.get("pageSize");
        return blogArticleService.findByPage(pageNum,pageSize);
    }

    @GetMapping("findAll")
    public List<BlogArticle> findAll(){
        return blogArticleService.findAll(0,5);
    }

    @PostMapping("create")
    public String create(@RequestBody BlogArticle blogArticle){
        return blogArticleService.create(blogArticle);
    }

    @PostMapping("point")
    public String point(@RequestBody Map map){
        return blogArticleService.point((String) map.get("id"));
    }

}
