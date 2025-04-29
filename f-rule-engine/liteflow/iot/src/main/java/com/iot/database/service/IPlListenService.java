package com.iot.database.service;

import com.github.yulichang.base.MPJBaseService;
import com.iot.database.entity.PlListen;

import java.util.List;

/**
 * <p>
 * 监听表 服务类
 * </p>
 *
 * @author zhanghui
 * @since 2023-09-06
 */
public interface IPlListenService extends MPJBaseService<PlListen> {

    List<PlListen> listByChain(String chainId);
}
