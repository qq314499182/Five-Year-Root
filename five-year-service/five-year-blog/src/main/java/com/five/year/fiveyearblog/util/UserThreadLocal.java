package com.five.year.fiveyearblog.util;

import com.five.year.fiveyearblog.entity.BlogUser;

/**
 * @Description 用户本地共享变量
 * @Author 五岁 <zhaokep@yonyou.com>
 * @Version V1.0.0
 * @Date 2019/3/13
 */
public class UserThreadLocal {

    public static ThreadLocal<BlogUser> threadLocal = new ThreadLocal<>();
}
