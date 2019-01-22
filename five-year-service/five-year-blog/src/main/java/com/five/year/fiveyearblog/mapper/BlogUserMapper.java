package com.five.year.fiveyearblog.mapper;

import com.five.year.fiveyearblog.entity.BlogUser;

/**
 * @Description
 * @Author zhaoke
 * @Date 2019/01/22
 */
public interface BlogUserMapper {

    int deleteByPrimaryKey(String id);

    int insert(BlogUser record);

    int insertSelective(BlogUser record);

    BlogUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BlogUser record);

    int updateByPrimaryKey(BlogUser record);

    BlogUser loadUserByUsername(String username);
}