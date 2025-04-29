package com.liuli;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "influxdb")
@Data
public class InfluxdbProperties {
    private String url;
    private String org;
    private String bucket;
    private String token;
    private String logLevel;
    private String readTimeout;
    private String writeTimeout;
    private String connectTimeout;

}
