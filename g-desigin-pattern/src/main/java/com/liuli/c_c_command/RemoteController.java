package com.liuli.c_c_command;

/**
 * @author pcc
 * 这是遥控器类，负责统筹各种命令
 */
public class RemoteController {
    private Command PowerOnCommand;
    private Command PowerOffCommand;

    public RemoteController(Command PowerOnCommand, Command PowerOffCommand) {
        this.PowerOnCommand = PowerOnCommand;
        this.PowerOffCommand = PowerOffCommand;
    }

    public void powerOn() {
        PowerOnCommand.execute();
    }

    public void powerOff() {
        PowerOffCommand.execute();
    }
}
