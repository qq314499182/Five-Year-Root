package com.five.year.fiveyearblog.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @Description 验证码异常
 * @Author 五岁 <zhaokep@yonyou.com>
 * @Version V1.0.0
 * @Date 2019/3/7
 */
public class ValidateException extends AuthenticationException {

    public ValidateException(String msg) {
        super(msg);
    }
}
