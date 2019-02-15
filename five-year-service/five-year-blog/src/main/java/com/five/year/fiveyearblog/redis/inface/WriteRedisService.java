package com.five.year.fiveyearblog.redis.inface;

/**
 * @Description Redis缓存写接口
 * @Author zhaoke <zhaokep@yonyou.com>
 * @Version V1.0.0
 * @Date 2019/2/15
 */
public interface WriteRedisService <T> {

    /**
     * 写入缓存,并不过期
     * @param key 键
     * @param t 对象
     */
    void put(String key, T t);

    /**
     * 写入缓存,并不过期
     * @param key 键
     * @param t 对象
     *  @param expire 过期时间 -1表示不过期
     */
    void put(String key, T t,long expire);

    /**
     * 删除指定key的缓存
     * @param key 键
     */
    void remove(String key);

    /**
     * 清空缓存
     */
    void clean();

}
