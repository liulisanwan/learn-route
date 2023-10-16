package com.liuli.c_c_command;

/**
 * @author pcc
 * 当真正使用时代码的清晰度就会非常高了，而不会看着都是一坨，
 * 同时解耦了命令的发送者和执行者，两者之间没有直接引用，而是通过命令对象进行command的调用
 * 这样就避免了调用者与接收者之间的耦合。
 * 命令模式的关键在于引入了命令对象，将调用者与接收者解耦。
 */
public class TestCommand {
    public static void main(String[] args) {
        TV tv  = new TV();
        RemoteController remoteController = new RemoteController(new PowerOnCommand(tv),new PowerOffCommand(tv));
        remoteController.powerOn();
        remoteController.powerOff();
    }
}