package com.liuli.data.entity;

/**
 * 精简流解析异常
 *
 * @author zhanghui
 * @date 2023/10/18 09:34:58
 */
public class LiteFlowParseException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private String message;


    public LiteFlowParseException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
