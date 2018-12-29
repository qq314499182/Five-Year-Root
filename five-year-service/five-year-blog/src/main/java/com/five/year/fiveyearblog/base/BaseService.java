package com.five.year.fiveyearblog.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
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
        entity.setCreatId("五岁程序员");
        entity.setCreatTime(date);
        return entity;
    }





}
