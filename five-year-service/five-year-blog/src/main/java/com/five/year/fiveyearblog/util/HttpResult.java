package com.five.year.fiveyearblog.util;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description
 * @Author zhaoke <zhaokep@yonyou.com>
 * @Version V1.0.0
 * @Date 2019/2/15
 */
@Data
public class HttpResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    private Integer state;

    /**
     * 返回信息
     */
    private String message;

    private HttpResult(Integer state, String message) {
        this.state = state;
        this.message = message;
    }

    private static class SingleToHolder {

        private static HttpResult returnResult(Integer state,String messgae){
            return new HttpResult(state,messgae);
        }
    }

    public static HttpResult getResult(Integer state,String message){
        return SingleToHolder.returnResult(state,message);
    }

    public static String getJsonResult(Integer state,String message){
        return JSONUtils.toJSONString(getResult(state,message));
    }

}
