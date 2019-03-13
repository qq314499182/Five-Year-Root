package com.five.year.fiveyearblog.mapper;

import com.five.year.fiveyearblog.entity.BlogArticle;

import java.util.List;

public interface BlogArticleMapper {

    /**
     * 根据id删除对象
     * @param id 主键
     * @return 成功返回1
     */
    int deleteByPrimaryKey(String id);

    /**
     * 新增操作
     * @param record 对象
     * @return 成功返回1
     */
    int insert(BlogArticle record);

    int insertSelective(BlogArticle record);

    BlogArticle selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BlogArticle record);

    int updateByPrimaryKey(BlogArticle record);

    List<BlogArticle> selectAll();

    List<BlogArticle> selectByUser(String createId);
}