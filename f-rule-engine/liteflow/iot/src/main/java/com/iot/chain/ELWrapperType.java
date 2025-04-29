package com.iot.chain;

/**
 * el包装器类型
 *
 * @author zhanghui
 * @date 2023/10/18 13:57:17
 */
public enum ELWrapperType {
    AndELWrapper("AndELWrapper"),
    CatchELWrapper("CatchELWrapper"),
    ForELWrapper("ForELWrapper"),
    IfELWrapper("IfELWrapper"),
    IteratorELWrapper("IteratorELWrapper"),
    NodeELWrapper("NodeELWrapper"),
    NotELWrapper("NotELWrapper"),
    OrELWrapper("OrELWrapper"),
    SwitchELWrapper("SwitchELWrapper"),
    ThenELWrapper("ThenELWrapper"),
    WhenELWrapper("WhenELWrapper"),
    WhileELWrapper("WhileELWrapper")
    ;

    String name;

    ELWrapperType(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}