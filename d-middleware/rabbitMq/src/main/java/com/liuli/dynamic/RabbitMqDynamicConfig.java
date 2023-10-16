package com.liuli.dynamic;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.liuli.dynamic.enums.RabbitExchangeTypeEnum;
import com.liuli.dynamic.property.RabbitModuleProperty;
import com.liuli.dynamic.property.RabbitModulePropertys;
import com.liuli.dynamic.retry.CustomRetryListener;
import com.liuli.dynamic.service.impl.AbsProducerService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p> RabbitMQ动态队列初始化器 </p>
 *
 * @author zhanghui
 * @description
 * @date 2022/7/8 10:37
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class RabbitMqDynamicConfig implements SmartInitializingSingleton {

    /**
     * MQ链接工厂
     */
    private final ConnectionFactory connectionFactory;
    /**
     * MQ操作管理器
     */
    private final AmqpAdmin amqpAdmin;
    /**
     * YML配置参数
     */
    private final RabbitModulePropertys rabbitModulePropertys;
    private final RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.listener.simple.retry.max-attempts}")
    private Integer maxAttempts;


    /**
     * 在单例实例化后->确定容器已经实例化所有非延迟加载的单例bean
     * (connectionFactory,amqpAdmin,rabbitModulePropertys,rabbitTemplate)后->调用此方法
     *
     * @author zhanghui
     * @date 2023/09/22 09:30:13
     * @since 1.0.0
     */
    @Override
    public void afterSingletonsInstantiated() {
        log.info("RabbitMQ 根据配置动态创建和绑定队列、交换机");
        this.declareRabbitModule();
    }

    /**
     * RabbitMQ 根据配置动态创建和绑定队列、交换机
     */
    private void declareRabbitModule() {
        //获取yml文件的配置列表
        List<RabbitModuleProperty> rabbitModulePropertyList = this.rabbitModulePropertys.getModuleList();
        //如果配置列表为空，直接返回
        if (CollectionUtil.isEmpty(rabbitModulePropertyList)) {
            return;
        }
        //遍历配置列表
        for (RabbitModuleProperty rabbitModuleProperty : rabbitModulePropertyList) {
            // 配置参数校验
            this.configParamValidate(rabbitModuleProperty);

            // 创建队列
            Queue queue = this.convertQueue(rabbitModuleProperty.getQueue());
            // 创建交换机
            Exchange exchange = this.convertExchange(rabbitModuleProperty.getExchange());
            // 绑定关系
            this.queueBindExchange(queue, exchange, rabbitModuleProperty);

            // 绑定生产者
            this.bindProducer(rabbitModuleProperty);
            // 绑定消费者
            this.bindConsumer(queue, exchange, rabbitModuleProperty);
        }
    }

    /**
     * RabbitMQ动态配置参数校验
     */
    public void configParamValidate(RabbitModuleProperty rabbitModuleProperty) {
        String routingKey = rabbitModuleProperty.getRoutingKey();

        Assert.isTrue(StrUtil.isNotBlank(routingKey), "[RabbitMQ] RoutingKey 未配置");

        Assert.isTrue(rabbitModuleProperty.getExchange() != null, "[RabbitMQ] routingKey:{} 未配置exchange", routingKey);
        Assert.isTrue(StrUtil.isNotBlank(rabbitModuleProperty.getExchange().getName()), "[RabbitMQ] routingKey:{} 未配置exchange的name属性", routingKey);

        Assert.isTrue(rabbitModuleProperty.getQueue() != null, "[RabbitMQ] routingKey:{} 未配置queue", routingKey);
        Assert.isTrue(StrUtil.isNotBlank(rabbitModuleProperty.getQueue().getName()), "[RabbitMQ] routingKey:{} 未配置queue的name属性", routingKey);

        if (StrUtil.isNotBlank(rabbitModuleProperty.getRetry())) {
            Assert.isTrue(StrUtil.isNotBlank(rabbitModuleProperty.getConsumer()), "[RabbitMQ] queue:{} 配置消息重试但未配置消费者", rabbitModuleProperty.getQueue().getName());
        }
    }

    /**
     * 转换生成RabbitMQ队列
     */
    public Queue convertQueue(RabbitModuleProperty.Queue queue) {

        // 获取队列参数
        Map<String, Object> arguments = queue.getArguments();

        // 转换ttl的类型为long 延时队列
        if (arguments != null && arguments.containsKey("x-message-ttl")) {
            arguments.put("x-message-ttl", Convert.toLong(arguments.get("x-message-ttl")));
        }

        // 是否需要绑定死信队列
        String deadLetterExchange = queue.getDeadLetterExchange();
        String deadLetterRoutingKey = queue.getDeadLetterRoutingKey();
        // 如果死信交换机和死信路由键都不为空，则需要绑定死信队列
        if (StrUtil.isNotBlank(deadLetterExchange) && StrUtil.isNotBlank(deadLetterRoutingKey)) {
            if (arguments == null) {
                arguments = new HashMap<>(16);
            }
            // 设置死信交换机
            arguments.put("x-dead-letter-exchange", deadLetterExchange);
            // 设置死信
            arguments.put("x-dead-letter-routing-key", deadLetterRoutingKey);
        }
        //队列的名称,队列消息是否持久化,是否排他,是否自动删除,队列参数(是否是延时队列,是否绑定死信队列)
        return new Queue(queue.getName(), queue.isDurable(), queue.isExclusive(), queue.isAutoDelete(), arguments);
    }


    /**
     * 转换生成RabbitMQ交换机
     */
    public Exchange convertExchange(RabbitModuleProperty.Exchange exchangeInfo) {
        // 获取交换机类型
        RabbitExchangeTypeEnum exchangeType = exchangeInfo.getType();
        // 获取交换机名称
        String exchangeName = exchangeInfo.getName();
        // 获取是否持久化
        boolean isDurable = exchangeInfo.isDurable();
        // 是否自动删除
        boolean isAutoDelete = exchangeInfo.isAutoDelete();
        // 获取参数
        Map<String, Object> arguments = exchangeInfo.getArguments();

        // 根据类型生成交换机
        AbstractExchange exchange = null;
        //5种交换机类型,直连,主题,扇形,头,延时(需在rabbitmq加载插件)
        switch (exchangeType) {
            case DIRECT:
                exchange = new DirectExchange(exchangeName, isDurable, isAutoDelete, arguments);
                break;
            case TOPIC:
                exchange = new TopicExchange(exchangeName, isDurable, isAutoDelete, arguments);
                break;
            case FANOUT:
                exchange = new FanoutExchange(exchangeName, isDurable, isAutoDelete, arguments);
                break;
            case HEADERS:
                exchange = new HeadersExchange(exchangeName, isDurable, isAutoDelete, arguments);
                break;
            case DELAY:
                if (arguments == null) {
                    arguments = new HashMap<String, Object>(1) {{
                        this.put("x-delayed-type", "direct");
                    }};
                }
                exchange = new CustomExchange(exchangeName, "x-delayed-message", isDurable, isAutoDelete, arguments);
                break;
            default:
                log.warn("[RabbitMQ] 未匹配到交换机类型");
                break;
        }
        return exchange;
    }

    /**
     * 队列绑定交换机
     */
    private void queueBindExchange(Queue queue, Exchange exchange, RabbitModuleProperty rabbitModuleProperty) {
        log.debug("[RabbitMQ] 初始化交换机: {}", rabbitModuleProperty.getExchange().getName());
        String routingKey = rabbitModuleProperty.getRoutingKey();
        String queueName = rabbitModuleProperty.getQueue().getName();
        String exchangeName = rabbitModuleProperty.getExchange().getName();

        Binding binding = new Binding(queueName, Binding.DestinationType.QUEUE, exchangeName, routingKey, null);

        // 创建队列、交换机、建立绑定
        // 如果队列不存在则创建队列
        this.amqpAdmin.declareQueue(queue);
        // 如果交换机不存在则创建交换机
        this.amqpAdmin.declareExchange(exchange);
        // 绑定队列到交换机
        this.amqpAdmin.declareBinding(binding);
        log.debug("[RabbitMQ] 队列绑定交换机 队列:{} 交换机:{} 交换机类型：{}", queueName, exchangeName, rabbitModuleProperty.getExchange().getType());
    }

    /**
     * 绑定生产者
     */
    private void bindProducer(RabbitModuleProperty rabbitModuleProperty) {
        try {
            String producer = rabbitModuleProperty.getProducer();
            if (StrUtil.isBlank(producer)) {
                return;
            }
            // 获取生产者
            AbsProducerService producerService = SpringUtil.getBean(producer);
            // 设置交换机
            producerService.setExchange(rabbitModuleProperty.getExchange().getName());
            // 设置路由键
            producerService.setRoutingKey(rabbitModuleProperty.getRoutingKey());
            log.debug("[RabbitMQ] 绑定生产者: {}", producer);
        } catch (Exception e) {
            log.error("[RabbitMQ] 无法在容器中找到该生产者[{}]，若需要此生产者请做具体实现", rabbitModuleProperty.getConsumer());
            throw e;
        }
    }

    /**
     * 绑定消费者
     */
    @SneakyThrows
    private void bindConsumer(Queue queue, Exchange exchange, RabbitModuleProperty rabbitModuleProperty) {
        String retry = rabbitModuleProperty.getRetry();
        String consumer = rabbitModuleProperty.getConsumer();
        CustomRetryListener customRetryListener = null;
        try {
            if (StrUtil.isNotBlank(retry)) {
                customRetryListener = SpringUtil.getBean(retry);
            }
        } catch (Exception e) {
            log.error("[RabbitMQ] 无法在容器中找到该重试类[{}]，若需要重试请做具体实现", retry);
            throw e;
        }
        if (StrUtil.isBlank(retry)) {
            return;
        }
        try {
            ConsumerContainerFactory factory = ConsumerContainerFactory.builder()
                    // 设置连接工厂
                    .connectionFactory(this.connectionFactory)
                    // 设置队列
                    .queue(queue)
                    // 设置交换机
                    .exchange(exchange)
                    // 设置消费者
                    .consumer(SpringUtil.getBean(consumer))
                    // 设置重试回调
                    .retryListener(customRetryListener)
                    // 自动确认
                    .autoAck(rabbitModuleProperty.getAutoAck())
                    // 操作MQ管理器
                    .amqpAdmin(this.amqpAdmin)
                    // 设置最大重试次数
                    .maxAttempts(this.maxAttempts)
                    // rabbitTemplate对象
                    .rabbitTemplate(this.rabbitTemplate)
                    .build();
            SimpleMessageListenerContainer container = factory.getObject();
            if (Objects.nonNull(container)) {
                container.start();
            }
            log.debug("[RabbitMQ] 绑定消费者: {}", consumer);
        } catch (Exception e) {
            log.error("[RabbitMQ] 无法在容器中找到该消费者[{}]，若需要此消费者请做具体实现", consumer);
            throw e;
        }
    }

}
