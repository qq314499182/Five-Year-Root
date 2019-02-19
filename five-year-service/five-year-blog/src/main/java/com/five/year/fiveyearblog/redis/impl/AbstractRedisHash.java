package com.five.year.fiveyearblog.redis.impl;

import com.five.year.fiveyearblog.redis.inface.ReadRedisService;
import com.five.year.fiveyearblog.redis.inface.WriteRedisService;
import com.five.year.fiveyearblog.util.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Description HashRedis抽象基类
 * @Author zhaoke <zhaokep@yonyou.com>
 * @Version V1.0.0
 * @Date 2019/2/15
 */
public abstract class AbstractRedisHash<T> implements WriteRedisService<T>, ReadRedisService<T> {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private HashOperations<String, String, String> hashOperations;

    /**
     * 获取对象字节码,用于序列化与反序列化
     * @return 对象.class
     */
    protected abstract Class<T> getSerializeClass();

    /**
     * 获取缓存空间名称--相当于数据库表面
     * @return 空间名称
     */
    protected abstract String getRedisTable();

    @Override
    public T get(String key) {
        return JSONUtils.jsonToObject(hashOperations.get(this.getRedisTable(),key),this.getSerializeClass());
    }

    @Override
    public List<T> getAll() {
        return JSONUtils.jsonToList(JSONUtils.toJSONString(hashOperations.values(this.getRedisTable())),this.getSerializeClass());
    }

    @Override
    public Set<String> getKeys() {
        return hashOperations.keys(this.getRedisTable());
    }

    @Override
    public boolean isKeyExists(String key) {
        return hashOperations.hasKey(this.getRedisTable(),key);
    }

    @Override
    public long count() {
        return hashOperations.size(this.getRedisTable());
    }

    @Override
    public void put(String key, T t) {
        this.put(key,t,-1);
    }

    @Override
    public void put(String key, T t, long expire) {
        hashOperations.put(this.getRedisTable(), key, JSONUtils.toJSONString(t));
        if (expire != -1) {
            stringRedisTemplate.expire(this.getRedisTable(), expire, TimeUnit.SECONDS);
        }
    }

    @Override
    public void remove(String key) {
        hashOperations.delete(this.getRedisTable(),key);
    }

    @Override
    public void clean() {
        this.getKeys().forEach(key -> hashOperations.delete(this.getRedisTable(),key));
    }
}
