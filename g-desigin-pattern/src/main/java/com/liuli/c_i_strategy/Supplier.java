package com.liuli.c_i_strategy;

import lombok.Data;

/**
 * @author pcc
 * 这是持有策略的商户
 */
@Data
public class Supplier {
    SellStrategy sellStrategy;

    public Supplier(SellStrategy sellStrategy) {
        this.sellStrategy = sellStrategy;
    }

    public void sell() {
        sellStrategy.sell();
    }
}
