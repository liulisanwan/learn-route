package com.liuli.factory;

/**
 * 执行类型
 *
 * @author zhanghui
 * @date 2023/09/14 14:47:05
 */
public enum ExecuteType {

    /**
     * 监听
     */
    LISTEN("listen"),
    /**
     * 第三
     */
    THIRD("third"),
    /**
     * 切换交换机到交换机
     */
    SWITCH("switch"),
    /**
     * 如果
     */
    IF("if"),
    /**
     * 而当
     */
    WHILE("while"),
    /**
     * 循环
     */
    FOR("for"),
    /**
     * 中断
     */
    BREAK("break"),
    /**
     * 控制
     */
    CONTROL("control");

    /**
     * 类型
     */
    private String type;

    ExecuteType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
