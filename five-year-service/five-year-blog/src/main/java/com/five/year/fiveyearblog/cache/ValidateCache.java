package com.five.year.fiveyearblog.cache;

import com.five.year.fiveyearblog.redis.impl.AbstractRedisHash;
import org.springframework.stereotype.Component;

/**
 * @Description 验证码缓存类
 * @Author 五岁 <zhaokep@yonyou.com>
 * @Version V1.0.0
 * @Date 2019/3/5
 */
@Component
public class ValidateCache extends AbstractRedisHash<String> {

    @Override
    protected Class<String> getSerializeClass() {
        return String.class;
    }

    @Override
    protected String getRedisTable() {
        return RedisTable.VALIDATE_CODE;
    }
}
