package com.five.year.fiveyearblog.web;

import com.five.year.fiveyearblog.cache.ValidateCache;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * @Description
 * @Author 五岁 <zhaokep@yonyou.com>
 * @Version V1.0.0
 * @Date 2019/3/4
 */
@Controller
@RequestMapping("/api")
public class ValidateController {

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @Autowired
    private ValidateCache validateCache;
    
    @GetMapping("getCode")
    public void getValidateCode( @NotNull HttpServletResponse response) throws IOException {
        String text = defaultKaptcha.createText();
        response = this.cacheValidateCode(response, text);
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        BufferedImage image = defaultKaptcha.createImage(text);
        ImageIO.write(image,"jpg",jpegOutputStream);
        byte[] captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = response.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

    /**
     * 将验证码放入redis缓存并使用cookie返回页面
     * @param response 响应
     * @param text 验证码
     * @return 响应
     */
    private HttpServletResponse cacheValidateCode(HttpServletResponse response,String text){
        String key = UUID.randomUUID().toString();
        validateCache.put(key,text,60);
        Cookie cookie = new Cookie(key, text);
        response.addCookie(cookie);
        return response;
    }

}
