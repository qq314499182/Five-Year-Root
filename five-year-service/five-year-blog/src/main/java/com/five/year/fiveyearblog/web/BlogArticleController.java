package com.five.year.fiveyearblog.web;

import com.five.year.fiveyearblog.entity.BlogArticle;
import com.five.year.fiveyearblog.service.BlogArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * @Description 文章
 * @Author zhaoke <zhaokep@yonyou.com>
 * @Version V1.0.0
 * @Date 2018/12/25
 */
@RestController
@RequestMapping("/five-service/blog-article")
public class BlogArticleController {

    @Autowired
    private BlogArticleService blogArticleService;

    @GetMapping("search/findOne")
    public BlogArticle findOne(String id){
        return blogArticleService.findOne(id);
    }

    @PostMapping("search/page")
    public List<BlogArticle> findAll(@RequestBody Map map){
        Integer pageNum = (Integer)map.get("pageNum");
        Integer pageSize = (Integer)map.get("pageSize");
        return blogArticleService.findByPage(pageNum,pageSize);
    }

    @GetMapping("search/findAll")
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
