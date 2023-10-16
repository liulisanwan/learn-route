package com.liuli.c_c_command;

/**
 * @author pcc
 * 命令都是需要接收者执行的，这里的TV就是接收者
 */
public class TV {
    public void powerOn() {
        System.out.println("TV power on");
    }

    public void powerOff() {
        System.out.println("TV power off");
    }
}


class PowerOnCommand extends Command {

    TV tv;

    public PowerOnCommand(TV tv){
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.powerOn();
    }
}

class PowerOffCommand extends Command {

    TV tv;

    public PowerOffCommand(TV tv){
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.powerOff();
    }
}




