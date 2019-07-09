package com.five.year.fiveyearblog.base;

import com.five.year.fiveyearblog.entity.BlogUser;
import com.five.year.fiveyearblog.util.UserThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.UUID;

/**
 * @Description 服务基类
 * @Author zhaoke <zhaokep@yonyou.com>
 * @Version V1.0.0
 * @Date 2018/12/28
 */
@Slf4j
public class BaseService<TEntity extends BaseEntity> {

    /**
     * 填充公共属性
     * @param entity 实体对象
     * @return  填充后的实体对象
     */
    protected TEntity fillParam(TEntity entity){
        Assert.notNull(entity, "entity 参数不能为空");
        final Date date = new Date();
        entity.setId(UUID.randomUUID().toString());
        entity.setTs(date);
        BlogUser blogUser = UserThreadLocal.threadLocal.get();
        //区分注册还是其他
        if(blogUser != null){
            entity.setCreateId(blogUser.getId());
        }
        entity.setCreateTime(date);
        return entity;
    }





}
