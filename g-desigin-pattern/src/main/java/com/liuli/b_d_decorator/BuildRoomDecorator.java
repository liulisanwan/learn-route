package com.liuli.b_d_decorator;

/**
 * @author pcc
 * 这里是一个简单写法，若是建造房子有多个子类且要求 有不同的装饰
 * 则我们需要创建多个装饰类，这里只是写了单个的子类场景，不过若是装饰都相同，也可以面向BuildRoom
 * 进行装饰，这种只需要一个装饰类即可
 */
public class BuildRoomDecorator implements BuildRoom {

    private BuildRoom buildRoom;

    public BuildRoomDecorator(BuildRoom buildRoom) {
        this.buildRoom = buildRoom;
    }

    @Override
    public void createRoom() {
        buildRoom.createRoom();
        System.out.println("为房子增加装饰：粉刷墙面");
    }
}
