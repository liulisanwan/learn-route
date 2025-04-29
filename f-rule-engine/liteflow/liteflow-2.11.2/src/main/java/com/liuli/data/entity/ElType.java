package com.liuli.data.entity;

/**
 * el类型
 *
 * @author zhanghui
 * @date 2023/10/16 15:15:47
 */
public enum ElType {
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

    ElType(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
