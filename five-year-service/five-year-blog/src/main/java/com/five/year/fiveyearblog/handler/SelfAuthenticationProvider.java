package com.five.year.fiveyearblog.handler;

import com.five.year.fiveyearblog.entity.BlogUser;
import com.five.year.fiveyearblog.util.UserThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //表单输入的用户名
        String username = (String) authentication.getPrincipal();
        //表单输入的密码
        String password = (String) authentication.getCredentials();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if(bCryptPasswordEncoder.matches(password,userDetails.getPassword())){
            UserThreadLocal.threadLocal.set((BlogUser)userDetails);
            return new UsernamePasswordAuthenticationToken(username,password,null);
        }else {
            throw new BadCredentialsException("密码错误");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
