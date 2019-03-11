package com.five.year.fiveyearblog.handler;

import com.five.year.fiveyearblog.cache.ValidateCache;
import com.five.year.fiveyearblog.exception.ValidateException;
import com.five.year.fiveyearblog.util.IpUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description 验证码校验拦截器
 * @Author 五岁 <zhaokep@yonyou.com>
 * @Version V1.0.0
 * @Date 2019/3/6
 */
@Component
public class ValidateFilter extends OncePerRequestFilter {

    @Autowired
    private ValidateCache validateCache;

    @Autowired
    private UrlAuthenticationFailureHandler urlAuthenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if(StringUtils.equals("/five-service/login",httpServletRequest.getRequestURI()) && StringUtils.equalsAnyIgnoreCase(httpServletRequest.getMethod(),"post")){
            try {
                this.validate(httpServletRequest);
            } catch (ValidateException e) {
                urlAuthenticationFailureHandler.onAuthenticationFailure(httpServletRequest,httpServletResponse,e);
            }
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    /**
     * 验证码校验
     * @param request 请求
     * @throws ValidateException 验证码异常
     */
    private void validate(HttpServletRequest request) throws ValidateException {
        String validate = request.getParameter("validate");
        String ipAddress = IpUtils.getIPAddress(request);
        if(!validateCache.isKeyExists(ipAddress)){
            throw new ValidateException("验证码失效，请点击验证码刷新");
        }
        String validateCode = validateCache.get(ipAddress);
        if(StringUtils.isBlank(validate)){
            throw new ValidateException("验证码不能为空");
        }
        if(StringUtils.isBlank(validateCode)){
            throw new ValidateException("验证码失效，请点击验证码刷新");
        }
        if(!StringUtils.equals(validate,validateCode)){
            throw new ValidateException("验证码错误，请重新输入");
        }
        validateCache.remove(ipAddress);
    }
}
