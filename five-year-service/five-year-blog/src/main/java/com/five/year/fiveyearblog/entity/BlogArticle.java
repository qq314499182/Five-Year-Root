package com.five.year.fiveyearblog.entity;

import com.five.year.fiveyearblog.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
@Setter
@Getter
public class BlogArticle extends BaseEntity {
    /**
     * 标题
     */
    private String title;

    /**
     * 分类
     */
    private String category = "后端语言";

    /**
     * 内容
     */
    private String content;

    /**
     * 阅读数
     */
    private BigDecimal readNum = BigDecimal.ZERO;

    /**
     * 点赞数
     */
    private BigDecimal pointNum = BigDecimal.ZERO;

    /**
     * 是否公开
     */
    private Short opened = 0;

    /**
     * 是否置顶
     */
    private Short top = 0;

    /**
     * 文章类型
     */
    private Short articleCat = 0;


}