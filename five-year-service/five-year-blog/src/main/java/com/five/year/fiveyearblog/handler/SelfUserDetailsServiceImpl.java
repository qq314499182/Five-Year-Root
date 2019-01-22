package com.five.year.fiveyearblog.handler;

import com.five.year.fiveyearblog.entity.BlogUser;
import com.five.year.fiveyearblog.mapper.BlogUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * @Description
 * @Author zhaoke <zhaokep@yonyou.com>
 * @Version V1.0.0
 * @Date 2019/1/22
 */
@Component
public class SelfUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private BlogUserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BlogUser blogUser = mapper.loadUserByUsername(username);
        if(ObjectUtils.isEmpty(blogUser)){
            throw new UsernameNotFoundException("根据用户名未找到用户信息");
        }
        return blogUser;
    }
}
