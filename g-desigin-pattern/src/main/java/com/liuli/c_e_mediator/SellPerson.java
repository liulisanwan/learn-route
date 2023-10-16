package com.liuli.c_e_mediator;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author pcc
 */
@Data
@AllArgsConstructor
public class SellPerson {

    String name;
    String address;

    // 把房子交给中介
    public void sellRoom(Mediator mediator) {
        mediator.register(this.name, this.address);
    }
}
