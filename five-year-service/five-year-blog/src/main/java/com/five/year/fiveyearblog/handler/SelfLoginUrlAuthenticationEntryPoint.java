package com.five.year.fiveyearblog.handler;

import com.five.year.fiveyearblog.util.HttpResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Description
 * @Author zhaoke <zhaokep@yonyou.com>
 * @Version V1.0.0
 * @Date 2019/2/14
 */
public class SelfLoginUrlAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

    public SelfLoginUrlAuthenticationEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(HttpResult.getJsonResult(403,"未登陆"));
        out.flush();
        out.close();
    }
}
