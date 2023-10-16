package com.liuli.c_h_state;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author pcc
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Process extends State{

    String name; // 流程名
    String desc; // 流程描述

    State state; // 流程状态


    @Override
    public void handlerEvent() {
        state.handlerEvent();
    }
}