package com.iot.database.service.impl;

import com.github.yulichang.base.MPJBaseServiceImpl;
import com.iot.database.entity.PlListen;
import com.iot.database.mapper.PlListenMapper;
import com.iot.database.service.IPlListenService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 监听表 服务实现类
 * </p>
 *
 * @author zhanghui
 * @since 2023-09-06
 */
@Service
public class PlListenServiceImpl extends MPJBaseServiceImpl<PlListenMapper, PlListen> implements IPlListenService {



    @Override
    public List<PlListen> listByChain(String chainId) {
        return baseMapper.listByChain(chainId);
    }
}
