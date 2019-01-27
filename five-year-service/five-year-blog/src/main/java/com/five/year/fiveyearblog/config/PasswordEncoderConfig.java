package com.five.year.fiveyearblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Description
 * @Author zhaoke <zhaokep@yonyou.com>
 * @Version V1.0.0
 * @Date 2019/1/27
 */
@Configuration
public class PasswordEncoderConfig {

    /**
     * BCryptPasswordEncoder方法采用SHA-256 +随机盐+密钥对密码进行加密
     * @return
     */
    @Bean()
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
