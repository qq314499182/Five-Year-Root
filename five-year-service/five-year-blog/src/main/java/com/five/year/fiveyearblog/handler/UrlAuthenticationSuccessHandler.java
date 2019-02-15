package com.five.year.fiveyearblog.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Description 登陆成功处理类
 * @Author zhaoke <zhaokep@yonyou.com>
 * @Version V1.0.0
 * @Date 2019/1/23
 */
@Component
public class UrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setStatus(200);
        PrintWriter writer = httpServletResponse.getWriter();
        String sb = "{\"status\":\"200\",\"msg\":\"" + "登陆成功" + "\"}";
        writer.write(sb);
        writer.flush();
        writer.close();
    }
}
