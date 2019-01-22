package com.five.year.fiveyearblog.config;

import com.five.year.fiveyearblog.handler.SelfAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Description
 * @Author zhaoke <zhaokep@yonyou.com>
 * @Version V1.0.0
 * @Date 2019/1/22
 */
@Configuration
public class WebSecurityConfigure extends WebSecurityConfigurerAdapter {

    /**
     * 自定义登录认证
     */
    @Autowired
    private SelfAuthenticationProvider authenticationProvider;

    /**
     * 登录认证
     * @param auth 登陆管理器
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        //添加自定义登陆认证
        auth.authenticationProvider(authenticationProvider);
    }

    /**
     * 具体配置登陆细节
     * @param http 登陆访问对象
     * @throws Exception 登陆异常
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }
}
