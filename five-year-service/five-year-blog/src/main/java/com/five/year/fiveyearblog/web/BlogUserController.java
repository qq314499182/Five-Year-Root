package com.five.year.fiveyearblog.web;

import com.five.year.fiveyearblog.entity.BlogUser;
import com.five.year.fiveyearblog.service.BlogUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author zhaoke <zhaokep@yonyou.com>
 * @Version V1.0.0
 * @Date 2019/1/24
 */
@RestController
@RequestMapping("/blog-user")
public class BlogUserController {

    @Autowired
    private BlogUserService blogUserService;

    @PostMapping
    public String insert(@RequestBody BlogUser user){
        return blogUserService.insert(user);
    }
}
