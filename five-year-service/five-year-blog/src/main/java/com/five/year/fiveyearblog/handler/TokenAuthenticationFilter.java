package com.five.year.fiveyearblog.handler;

import com.five.year.fiveyearblog.entity.BlogUser;
import com.five.year.fiveyearblog.util.TokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @Description
 * @Author zhaoke <zhaokep@yonyou.com>
 * @Version V1.0.0
 * @Date 2019/2/22
 */
public class TokenAuthenticationFilter extends BasicAuthenticationFilter {

    public TokenAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = null;
        //判断请求中是否携带token
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for (Cookie cookie : cookies) {
                if(TokenUtils.REDIS_TABLE.equals(cookie.getName())){
                    token = cookie.getValue();
                }
            }
        }
        if(StringUtils.isNotBlank(token)){
            BlogUser user = TokenUtils.getUserByToken(token);
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),user.getPassword(),new ArrayList<>()));
        }
        super.doFilterInternal(request, response, chain);
    }
}
