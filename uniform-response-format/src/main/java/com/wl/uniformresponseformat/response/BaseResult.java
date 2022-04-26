package com.wl.uniformresponseformat.response;

import lombok.Data;

/**
 * @author WangLong
 * @Description:
 * @date 2022/4/11 14:44
 */
@Data
public class BaseResult<T> {

    private int code;
    private String time;
    private String message;
    private T data;

    public BaseResult(int code, String time, String message, T data) {
        this.code = code;
        this.time = time;
        this.message = message;
        this.data = data;
    }

    public BaseResult(int code, String time, String message) {
        this.code = code;
        this.time = time;
        this.message = message;
    }
}
