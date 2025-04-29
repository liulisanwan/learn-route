package com.liuli;

import com.influxdb.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * influxApi
 *
 * @author zhanghui
 * @version 1.0
 * @ClassName: InfluxApi
 * @date 2022/12/12 16:57:03
 */
@Component
public class InfluxApi {

    @Autowired
    public WriteApiBlocking write;

    @Autowired
    public QueryApi query;

    @Autowired
    public DeleteApi delete;

    @Autowired
    public BucketsApi bucket;

    @Autowired
    public InfluxDBClientOptions builderOptions;
}
