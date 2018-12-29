package com.five.year.fiveyearblog.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description
 * @Author zhaoke <zhaokep@yonyou.com>
 * @Version V1.0.0
 * @Date 2018/12/28
 */
@Getter
@Setter
public class BaseEntity implements Serializable {
    /**
     * 主键
     */
    private String id;

    /**
     * 时间戳
     */
    private Date ts;

    /**
     * 创建人
     */
    private String creatId;

    /**
     * 创建时间
     */
    private Date creatTime;

}
