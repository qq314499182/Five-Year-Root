package com.five.year.fiveyearblog.util;

import com.five.year.fiveyearblog.cache.BlogUserCache;
import com.five.year.fiveyearblog.entity.BlogUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @Description 用户token管理工具类
 * @Author zhaoke <zhaokep@yonyou.com>
 * @Version V1.0.0
 * @Date 2019/2/22
 */
@Component
public class TokenUtils {

    private static  BlogUserCache blogUserCache;

    @Autowired()
    private void setBlogUserCache(BlogUserCache blogUserCache){
        TokenUtils.blogUserCache = blogUserCache;
    }

    //cookie名称
    public static final String REDIS_TABLE = "LOGIN_TOKEN";

    // 过期时间是1800秒，既是30分钟
    private static final long EXPIRATION = 1800L;

    // 选择了记住我之后的过期时间为7天
    private static final long EXPIRATION_REMEMBER = 604800L;

    /**
     * 生产token,并按照 token - user 将用户信息存入缓存中
     * @param request 用户请求
     * @return Cookie 带有token的cookie信息
     */
    public static Cookie createToken(HttpServletRequest request){
        String userName = request.getParameter("userName");
        String userPassword = request.getParameter("userPassword");
        Boolean rememberMe = "1".equals(request.getParameter("rememberMe"));
        long expiration = rememberMe ? EXPIRATION_REMEMBER : EXPIRATION;
        String token = UUID.randomUUID().toString();
        blogUserCache.put(token,new BlogUser(userName,userPassword),expiration);
        return new Cookie(REDIS_TABLE,token);
    }

    /**
     * 根据token获取用户信息
     * @param token 用户手牌
     * @return BlogUser 用户信息
     */
    public static BlogUser getUserByToken(String token){
        return blogUserCache.get(token);
    }

}
