package com.five.year.fiveyearblog.web;

import com.five.year.fiveyearblog.entity.BlogArticle;
import com.five.year.fiveyearblog.service.BlogArticleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


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
    public PageInfo<BlogArticle> findAll(Integer pageNum, Integer pageSize){
        return blogArticleService.findByPage(pageNum,pageSize);
    }

    @GetMapping("findAll")
    public List<BlogArticle> findAll(){
        return blogArticleService.findAll(0,5);
    }

}
