package com.iot.exception;

/**
 * 自定义节点未发现异常
 *
 * @author zhanghui
 * @date 2023/10/19 10:49:37
 */
public class CustomNodeNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private String message;

    public CustomNodeNotFoundException(String message) {
        this.message=message;
    }


    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
