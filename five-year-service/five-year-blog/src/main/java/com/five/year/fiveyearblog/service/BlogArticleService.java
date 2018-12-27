package com.five.year.fiveyearblog.service;

import com.five.year.fiveyearblog.entity.BlogArticle;
import com.five.year.fiveyearblog.mapper.BlogArticleMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author zhaoke <zhaokep@yonyou.com>
 * @Version V1.0.0
 * @Date 2018/12/25
 */
@Service
public class BlogArticleService {

    @Autowired
    private BlogArticleMapper mapper;

    public BlogArticle findOne(String id) {
        return mapper.selectByPrimaryKey(id);
    }

    public List<BlogArticle> findByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return mapper.selectAll();
    }

    public List<BlogArticle> findAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return mapper.selectAll();
    }
}
