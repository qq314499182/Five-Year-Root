package com.five.year.fiveyearblog.service;

import com.five.year.fiveyearblog.base.BaseService;
import com.five.year.fiveyearblog.entity.BlogUser;
import com.five.year.fiveyearblog.mapper.BlogUserMapper;
import com.five.year.fiveyearblog.util.HttpResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.security.Escape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @Author zhaoke <zhaokep@yonyou.com>
 * @Version V1.0.0
 * @Date 2019/1/22
 */
@Service
@Slf4j
public class BlogUserService extends BaseService<BlogUser> {

    @Autowired
    private BlogUserMapper blogUserMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional(rollbackFor = Exception.class)
    public String insert(BlogUser user){
        try {
            user = super.fillParam(user);
            //对密码进行加密
            String encode = bCryptPasswordEncoder.encode(user.getPassword());
            user.setUserPassword(encode);
            int insert = blogUserMapper.insert(user);
            if( insert > 0 ){
                return HttpResult.getJsonResult(200,"注册成功");
            } else {
                return HttpResult.getJsonResult(500,"注册失败");
            }
        } catch (Exception e) {
            log.error("[注册失败] : ",e);
            return HttpResult.getJsonResult(500,e.getMessage());
        }
    }

    public HttpResult checkLogin(HttpServletRequest request) {
        log.info("该用户已登陆");
        return HttpResult.getResult(200,"用户已登陆");
    }
}
