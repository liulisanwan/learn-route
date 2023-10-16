package com.liuli.b_d_decorator;

/**
 * 这是建造房子的接口
 * 假设这个接口有一个建造方法的方法，createRoom
 * 他有一个实现类BuildRoomImpl,这个类负责真正去建造一个房子
 * 这里对实现类BuildRoomImpl进行装饰，增加一些功能
 */
public interface BuildRoom {
    void createRoom();
}
class BuildRoomImpl implements BuildRoom{

    @Override
    public void createRoom() {
        System.out.println("建造了一个房子");
    }
}


