package com.five.year.fiveyearblog.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * @Description 自定义登陆认证
 * @Author 五岁 <zhaokep@yonyou.com>
 * @Version V1.0.0
 * @Date 2019/1/22
 */
@Component
public class SelfAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private SelfUserDetailsServiceImpl userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //表单输入的用户名
        String username = (String) authentication.getPrincipal();
        //表单输入的密码
        String password = (String) authentication.getCredentials();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if(!userDetails.getPassword().equals(password)){
            throw new BadCredentialsException("密码错误");
        }
        return new UsernamePasswordAuthenticationToken(username,password,null);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
