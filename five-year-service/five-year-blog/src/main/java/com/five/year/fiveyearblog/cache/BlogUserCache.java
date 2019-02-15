package com.five.year.fiveyearblog.cache;

import com.five.year.fiveyearblog.entity.BlogUser;
import com.five.year.fiveyearblog.redis.impl.AbstractRedisHash;
import org.springframework.stereotype.Component;

/**
 * @Description 用户缓存类
 * @Author zhaoke <zhaokep@yonyou.com>
 * @Version V1.0.0
 * @Date 2019/2/15
 */
@Component
public class BlogUserCache extends AbstractRedisHash<BlogUser> {

    @Override
    protected Class<BlogUser> getSerializeClass() {
        return BlogUser.class;
    }

    @Override
    protected String getRedisTable() {
        return RedisTable.BLOG_USER;
    }
}
