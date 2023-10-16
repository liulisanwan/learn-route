package com.liuli.c_i_strategy;

/**
 * @author pcc
 * 这是电商卖东西的策略接口
 *
 * 当需要使用if、else对场景进行判断然后每个场景还拥有很多逻辑时，我们
 * 就可以使用策略模式对不同的算法进行隔离，解耦
 *
 */
public interface SellStrategy {
    void sell();
}

class Strategy618 implements SellStrategy{
    @Override
    public void sell() {
        System.out.println("618活动商品现提价100元再降价200元");
    }
}

class Strategy1111 implements SellStrategy{
    @Override
    public void sell() {
        System.out.println("1111活动商品现提价100元再降价300元");
    }
}



