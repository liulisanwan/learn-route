package com.liuli.config;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.CorsEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementPortType;
import org.springframework.boot.actuate.endpoint.ExposableEndpoint;
import org.springframework.boot.actuate.endpoint.web.*;
import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.annotation.ServletEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p> RabbitMQ配置类 </p>
 *
 * @author zhanghui
 * @description
 * @date 2022/7/8 10:32
 */
@Slf4j
@Configuration
public class RabbitMqConfig {

    /**
     * 生产者配置
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);

        /**
         * 1、消息发送回调
         */
        // 设置开启Mandatory,才能触发回调函数,无论消息推送结果怎么样都强制调用回调函数
        rabbitTemplate.setMandatory(true);

        // 确认消息送到交换机(Exchange)回调
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            // do your business
            log.debug("[确认消息送到交换机(Exchange)回调] 是否成功:[{}] 数据：[{}] 异常：[{}]", ack, JSONUtil.toJsonStr(correlationData), cause);
        });

        // 确认消息送到队列(Queue)回调 -- 只有在出现错误时才回调，延时队列也会触发！
        rabbitTemplate.setReturnsCallback(returnedMessage -> {
            // do your business
            log.error("[确认消息送到队列(Queue)回调] 返回信息：[{}]", JSONUtil.toJsonStr(returnedMessage));
        });

        /**
         * 2、配置自定义消息转换器
         * rabbitmq默认的消息转换器 {@link org.springframework.amqp.support.converter.SimpleMessageConverter}
         */
        rabbitTemplate.setMessageConverter(new CustomMessageConverter());

        return rabbitTemplate;
    }


    /**
     * 消费者配置
     */
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        /**
         * 配置自定义消息转换器
         * rabbitmq默认的消息转换器 {@link org.springframework.amqp.support.converter.SimpleMessageConverter}
         */
        factory.setMessageConverter(new CustomMessageConverter());

        return factory;
    }


    /**
     * web端点servlet处理程序映射->配置swagger3能正常启动(springboot2.6.8版本以上需配置此方法并且在配置文件中配置matching-strategy: ant_path_matcher)
     * spring:
     *   mvc:
     *     pathmatch:
     *       matching-strategy: ant_path_matcher
     *
     * @param webEndpointsSupplier        web端点供应商
     * @param servletEndpointsSupplier    servlet端点供应商
     * @param controllerEndpointsSupplier 控制器终端供应商
     * @param endpointMediaTypes          端点媒体类型
     * @param corsProperties              歌珥属性
     * @param webEndpointProperties       web端点属性
     * @param environment                 环境
     * @return {@code WebMvcEndpointHandlerMapping }
     * @author zhanghui
     * @date 2023/08/02 09:08:59
     * @see WebEndpointsSupplier
     * @see ServletEndpointsSupplier
     * @see ControllerEndpointsSupplier
     * @see EndpointMediaTypes
     * @see CorsEndpointProperties
     * @see WebEndpointProperties
     * @see Environment
     * @see WebMvcEndpointHandlerMapping
     * @since 1.0.0
     */
    @Bean
    public WebMvcEndpointHandlerMapping webEndpointServletHandlerMapping(WebEndpointsSupplier webEndpointsSupplier, ServletEndpointsSupplier servletEndpointsSupplier, ControllerEndpointsSupplier controllerEndpointsSupplier, EndpointMediaTypes endpointMediaTypes, CorsEndpointProperties corsProperties, WebEndpointProperties webEndpointProperties, Environment environment) {
        List<ExposableEndpoint<?>> allEndpoints = new ArrayList();
        Collection<ExposableWebEndpoint> webEndpoints = webEndpointsSupplier.getEndpoints();
        allEndpoints.addAll(webEndpoints);
        allEndpoints.addAll(servletEndpointsSupplier.getEndpoints());
        allEndpoints.addAll(controllerEndpointsSupplier.getEndpoints());
        String basePath = webEndpointProperties.getBasePath();
        EndpointMapping endpointMapping = new EndpointMapping(basePath);
        boolean shouldRegisterLinksMapping = this.shouldRegisterLinksMapping(webEndpointProperties, environment, basePath);
        return new WebMvcEndpointHandlerMapping(endpointMapping, webEndpoints, endpointMediaTypes, corsProperties.toCorsConfiguration(), new EndpointLinksResolver(allEndpoints, basePath), shouldRegisterLinksMapping, null);
    }


    /**
     * 注册链接映射
     *
     * @param webEndpointProperties web端点属性
     * @param environment           环境
     * @param basePath              基本路径
     * @return boolean
     * @author zhanghui
     * @date 2023/08/02 09:09:26
     * @see WebEndpointProperties
     * @see Environment
     * @see String
     * @see boolean
     * @since 1.0.0
     */
    private boolean shouldRegisterLinksMapping(WebEndpointProperties webEndpointProperties, Environment environment, String basePath) {
        return webEndpointProperties.getDiscovery().isEnabled() && (StringUtils.hasText(basePath) || ManagementPortType.get(environment).equals(ManagementPortType.DIFFERENT));
    }
}
