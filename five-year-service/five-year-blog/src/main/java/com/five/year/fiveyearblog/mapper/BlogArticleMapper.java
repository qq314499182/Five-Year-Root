package com.five.year.fiveyearblog.mapper;

import com.five.year.fiveyearblog.entity.BlogArticle;

import java.util.List;

public interface BlogArticleMapper {

    int deleteByPrimaryKey(String id);

    int insert(BlogArticle record);

    int insertSelective(BlogArticle record);

    BlogArticle selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BlogArticle record);

    int updateByPrimaryKey(BlogArticle record);

    List<BlogArticle> selectAll();
}