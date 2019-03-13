package com.five.year.fiveyearblog.web;

import com.five.year.fiveyearblog.entity.BlogArticle;
import com.five.year.fiveyearblog.service.BlogArticleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Description 用户信息
 * @Author 五岁 <zhaokep@yonyou.com>
 * @Version V1.0.0
 * @Date 2019/3/12
 */@RestController
@RequestMapping("/five-service/user-info")
public class UserInfoController {

    @Autowired
    private BlogArticleService blogArticleService;

    @PostMapping("search/userPage")
    public PageInfo<BlogArticle> findByUserPage(@CookieValue(name = "LOGIN_TOKEN")String cookie, @RequestBody Map map){
        Integer pageNum = (Integer)map.get("pageNum");
        Integer pageSize = (Integer)map.get("pageSize");
        return blogArticleService.findByUserPage(cookie,pageNum,pageSize);
    }
}
