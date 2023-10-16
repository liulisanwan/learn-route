package com.liuli;

import lombok.Data;

import java.util.List;

@Data
public class ClientResponseData {

    /**
     * 数据
     */
    private List<ClientData> data;
    /**
     * 元
     */
    private Meta meta;
}