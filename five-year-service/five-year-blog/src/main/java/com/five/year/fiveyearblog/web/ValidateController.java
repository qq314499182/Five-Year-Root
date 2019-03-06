package com.five.year.fiveyearblog.web;

import com.five.year.fiveyearblog.cache.ValidateCache;
import com.five.year.fiveyearblog.util.IpUtils;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @Description
 * @Author 五岁 <zhaokep@yonyou.com>
 * @Version V1.0.0
 * @Date 2019/3/4
 */
@Controller
@RequestMapping("/five-service/api")
public class ValidateController {

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @Autowired
    private ValidateCache validateCache;

    @GetMapping("getCode")
    public void getValidateCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String text = defaultKaptcha.createText();
        this.cacheValidateCode(request,response, text);
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        BufferedImage image = defaultKaptcha.createImage(text);
        ImageIO.write(image, "jpg", jpegOutputStream);
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
     *
     * @param response 响应
     * @param text     验证码
     * @return 响应
     */
    private void cacheValidateCode(HttpServletRequest request,HttpServletResponse response, String text) {
        String ipAddress = IpUtils.getIPAddress(request);
        validateCache.put(ipAddress, text, 60);
    }
}
