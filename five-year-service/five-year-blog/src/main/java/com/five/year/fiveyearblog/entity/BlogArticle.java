package com.five.year.fiveyearblog.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
@Setter
@Getter
public class BlogArticle {

    private String id;
    private String title;
    private String category;
    private String context;
    private BigDecimal readNum;
    private BigDecimal pointNum;
    private Short opened;
    private Short top;
    private Short articleCat;
    private Date ts;
    private String creatId;
    private Date creatTime;

}