package com.five.year.fiveyearblog.entity;

import com.five.year.fiveyearblog.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Setter
@Getter
public class BlogArticle extends BaseEntity {
    /**
     * 标题
     */
    private String title;

    /**
     * 列表显示内容
     */
    private String contentList;

    /**
     * 分类
     */
    private String category ;

    /**
     * 内容
     */
    private String content;

    /**
     * 阅读数
     */
    private BigDecimal readNum ;

    /**
     * 点赞数
     */
    private BigDecimal pointNum ;

    /**
     * 是否公开
     */
    private Integer opened;

    /**
     * 是否置顶
     */
    private Integer isTop ;

    /**
     * 文章类型
     */
    private Integer articleCat;


}