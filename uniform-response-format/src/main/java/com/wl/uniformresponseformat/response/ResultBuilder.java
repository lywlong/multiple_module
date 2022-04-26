package com.wl.uniformresponseformat.response;

import java.time.LocalDateTime;

/**
 * @author WangLong
 * @Description:
 * @date 2022/4/11 15:10
 */
public class ResultBuilder {

    //成功无数据返回
    public static BaseResult success(){
        return new BaseResult(200, LocalDateTime.now().toString(),"success");
    }
    //成功有数据返回
    public static <T> BaseResult<T> success(T data){
        return new BaseResult(200, LocalDateTime.now().toString(),"success",data);
    }
    //失败
    public static BaseResult failed(){
        return new BaseResult(500, LocalDateTime.now().toString(),"failed");
    }

}
