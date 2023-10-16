package com.liuli.util;

import java.io.Serializable;

/**
 * 结果
 *
 * @author zhanghui
 * @date 2023/01/30 09:54:41
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    private int code;
    private String msg;
    private T data;


    private Result(T data) {
        this.code = 200;
        this.msg = "success";
        this.data = data;
    }

    private Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    private Result(int code, T data) {
        this.code = code;
        this.data = data;
    }




    public Result() {
    }

    /**
     * 成功
     *
     * @param data 数据
     * @return {@link Result }<{@link T }>
     * @author zhanghui
     * @date 2023/01/29 19:41:44
     * @since 1.0.0
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>(data);
    }

    /**
     * 成功
     *
     * @return {@link Result }<{@link T }>
     * @author zhanghui
     * @date 2023/01/29 19:41:47
     * @since 1.0.0
     */
    public static <T> Result<T> success() {
        return success(null);
    }




    public static <T> Result<T> know(int code,T data) {
        return new Result<T>(code,data);
    }

    public static <T> Result<T> error(String msg) {
        return new Result<T>(500,msg);
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}
