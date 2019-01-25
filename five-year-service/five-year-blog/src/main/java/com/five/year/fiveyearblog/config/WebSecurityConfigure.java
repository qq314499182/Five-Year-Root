package com.five.year.fiveyearblog.config;

import com.five.year.fiveyearblog.handler.SelfAuthenticationProvider;
import com.five.year.fiveyearblog.handler.UrlAuthenticationFailureHandler;
import com.five.year.fiveyearblog.handler.UrlAuthenticationSuccessHandler;
import com.five.year.fiveyearblog.handler.UrlLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Description
 * @Author zhaoke <zhaokep@yonyou.com>
 * @Version V1.0.0
 * @Date 2019/1/22
 */
@EnableWebSecurity
@Configuration
public class WebSecurityConfigure extends WebSecurityConfigurerAdapter {

    @Autowired
    private UrlAuthenticationEntryPoint authenticationEntryPoint;

    /**
     * 自定义登录认证
     */
    @Autowired
    private SelfAuthenticationProvider authenticationProvider;

    /**
     * 自定义登录成功处理器
     */
    @Autowired
    private UrlAuthenticationSuccessHandler authenticationSuccessHandler;

    /**
     * 自定义登录失败处理器
     */
    @Autowired
    private UrlAuthenticationFailureHandler authenticationFailureHandler;

    /**
     * 自定义注销处理器
     */
    @Autowired
    private UrlLogoutSuccessHandler logoutSuccessHandler;

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

        http.csrf().disable() //关闭csrf验证
                //自定义未登陆返回结果
                .httpBasic().authenticationEntryPoint(authenticationEntryPoint)
                .and()
                //开放api路径
                .authorizeRequests().antMatchers("/api","/blog-article","/blog-article/findOne","/blog-user").
                permitAll()
                .anyRequest().authenticated()
                //开启自动配置的登陆功能
                .and()
                //自定义登录请求路径(post请求)
                .formLogin().usernameParameter("userName").passwordParameter("userPassword")
                .loginProcessingUrl("/login")
                //验证成功处理器
                .successHandler(authenticationSuccessHandler)
                //验证失败处理器
                .failureHandler(authenticationFailureHandler).permitAll()
                //开启自动配置的注销功能
                .and()
                .logout()
                // .logoutUrl("/nonceLogout") 自定义注销请求路径  默认/logout
                //注销成功处理器
                .logoutSuccessHandler(logoutSuccessHandler).permitAll();


    }
}
