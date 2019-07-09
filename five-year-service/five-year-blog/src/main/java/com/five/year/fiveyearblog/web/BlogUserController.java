package com.five.year.fiveyearblog.web;

import com.five.year.fiveyearblog.util.HttpResult;
import com.five.year.fiveyearblog.entity.BlogUser;
import com.five.year.fiveyearblog.service.BlogUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description 用户权限
 * @Author zhaoke <zhaokep@yonyou.com>
 * @Version V1.0.0
 * @Date 2019/1/24
 */
@RestController
@RequestMapping("/five-service/blog-user")
@Slf4j
public class BlogUserController {

    @Autowired
    private BlogUserService blogUserService;

    @PostMapping("login")
    public String insert(@RequestBody BlogUser user){
        return blogUserService.insert(user);
    }


    @GetMapping("checkLogin")
    public HttpResult checkLogin(HttpServletRequest request)  {
        return blogUserService.checkLogin(request);
    }
}
