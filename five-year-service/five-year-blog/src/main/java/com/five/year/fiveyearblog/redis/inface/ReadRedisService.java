package com.five.year.fiveyearblog.redis.inface;

import java.util.List;
import java.util.Set;

/**
 * @Description
 * @Author zhaoke <zhaokep@yonyou.com>
 * @Version V1.0.0
 * @Date 2019/2/15
 */
public interface ReadRedisService <T> {

    /**
     * 根据键获取缓存中的对象
     * @param key 键
     * @return T 对象
     */
    T get(String key);

    /**
     * 获取所有缓存对象
     * @return  List<T> 所有对象
     */
    List<T> getAll();

    /**
     * 获取所有键值key
     * @return Set<String> 所有key
     */
    Set<String> getKeys();

    /**
     * 键值key是否存在
     * @param key 键
     * @return boolean
     */
    boolean isKeyExists(String key);

    /**
     * Redis缓存计数器
     * @return long
     */
    long count();

}
