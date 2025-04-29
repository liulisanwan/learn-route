package com.iot.litelfow.enums;

/**
 * 脚本类型
 *
 * @author zhanghui
 * @date 2023/09/14 16:29:32
 */
public enum ScriptType {

    /**
     * 脚本
     */
    SCRIPT("script"),

    /**
     * IF脚本
     */
    IF_SCRIPT("if_script"),

    /**
     * While脚本
     */
    WHILE_SCRIPT("while_script"),

    /**
     * 对于脚本
     */
    FOR_SCRIPT("for_script"),
    /**
     * 选择脚本
     */
    SWITCH_SCRIPT("switch_script"),

    /**
     * 中断脚本
     */
    BREAK_SCRIPT("break_script");

    /**
     * 类型
     */
    private String type;

    ScriptType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
