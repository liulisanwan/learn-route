package com.iot.database.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.iot.database.entity.PlListen;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 监听表 Mapper 接口
 * </p>
 *
 * @author zhanghui
 * @since 2023-09-06
 */
public interface PlListenMapper extends MPJBaseMapper<PlListen> {

    List<PlListen> listByChain(@Param("chainId") String chainId);
}
