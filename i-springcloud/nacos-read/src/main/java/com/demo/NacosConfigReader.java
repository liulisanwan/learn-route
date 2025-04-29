package com.demo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.endpoint.event.RefreshEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;

@Component
public class NacosConfigReader {

    private final RestTemplate restTemplate;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public NacosConfigReader(RestTemplate restTemplate, ApplicationEventPublisher eventPublisher) {
        this.restTemplate = restTemplate;
        this.eventPublisher = eventPublisher;
    }

    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        // 设置连接超时时间
        requestFactory.setConnectTimeout(5000);
        // 设置读取超时时间
        requestFactory.setReadTimeout(5000);

        return new RestTemplate(requestFactory);
    }

    public String getParameterValue(String configDataId, String paramName) {
        String url = "http://192.168.116.143:8848/nacos/v1/cs/configs";
        String dataId = configDataId + ".yml";
        String response = restTemplate.getForObject(url + "?dataId=" + dataId, String.class);
        // 解析YAML文件内容，获取参数值
        // 这里可以使用任何适合你的方式解析YAML文件，例如使用Jackson库、SnakeYAML库等
        // 假设你使用Jackson库解析YAML文件，示例代码如下：
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        try {
            Map<String, Object> yamlMap = objectMapper.readValue(response, new TypeReference<Map<String, Object>>() {});
            Object paramValue = yamlMap.get(paramName);
            if (paramValue != null) {
                return paramValue.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void refreshConfig() {
        eventPublisher.publishEvent(new RefreshEvent(this, null, "refresh config"));
    }
}
