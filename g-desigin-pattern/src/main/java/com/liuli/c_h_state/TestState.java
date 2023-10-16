package com.liuli.c_h_state;

/**
 * @author pcc
 */
public class TestState {
    public static void main(String[] args) {
        Process process = new Process();
        process.setState(new StartState());
        // 在合适的场景进行状态变更的处理操作
        process.handlerEvent();

        process.setState(new DealState());
        // 在合适的场景进行状态变更的处理操作
        process.handlerEvent();

        process.setState(new CompleteState());
        // 在合适的场景进行状态变更的处理操作
        process.handlerEvent();

    }
}