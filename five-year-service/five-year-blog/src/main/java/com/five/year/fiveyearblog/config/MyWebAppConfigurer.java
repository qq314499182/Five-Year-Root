package com.five.year.fiveyearblog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Description  解决跨域访问
 * @Author zhaoke <zhaokep@yonyou.com>
 * @Version V1.0.0
 * @Date 2018/12/11
 */
@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurationSupport {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }
}
