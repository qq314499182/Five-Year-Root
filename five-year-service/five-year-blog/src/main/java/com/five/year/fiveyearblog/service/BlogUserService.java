package com.five.year.fiveyearblog.service;

import com.five.year.fiveyearblog.base.BaseService;
import com.five.year.fiveyearblog.entity.BlogUser;
import com.five.year.fiveyearblog.mapper.BlogUserMapper;
import org.apache.tomcat.util.security.Escape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description
 * @Author zhaoke <zhaokep@yonyou.com>
 * @Version V1.0.0
 * @Date 2019/1/22
 */
@Service
public class BlogUserService extends BaseService<BlogUser> {

    @Autowired
    private BlogUserMapper blogUserMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional(rollbackFor = Exception.class)
    public String insert(BlogUser user){
        user = super.fillParam(user);
        //对密码进行加密
        String encode = bCryptPasswordEncoder.encode(user.getPassword());
        user.setUserPassword(encode);
        int insert = blogUserMapper.insert(user);
        if( insert > 0 ){
            return "{\"state\":\"200\"}";
        } else {
            return "{\"state\":\"500\"}";
        }
    }
}
