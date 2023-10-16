package com.liuli.controller.retry;


import com.liuli.dynamic.retry.CustomRetryListener;
import com.liuli.dynamic.retry.RetryFailConsumer;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.stereotype.Component;

/**
 * <p> 自定义消息重试 </p>
 *
 * @author zhanghui
 * @description
 * @date 2023/7/10 15:59
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class TestRetryListener implements CustomRetryListener {

    private final RabbitTemplate rabbitTemplate;

    /**
     * 在重试过程中记录消息失败后的日志->第几次,消息内容,异常原因
     *
     * @param context   上下文
     * @param callback  回调
     * @param throwable throwable
     * @author zhanghui
     * @date 2023/08/08 17:14:18
     * @see RetryContext
     * @see RetryCallback <T, E>
     * @see Throwable
     * @since 1.0.0
     */
    @SneakyThrows
    @Override
    public <E extends Throwable, T> void onRetry(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
        log.info("[消费者失败回调-第{}次] 接收消息: {} 异常原因：{}", context.getRetryCount(), this.getData(callback), throwable.getCause().getMessage());
    }

    /**
     * 最后一次重试->记录消息失败后的日志{消息内容,异常原因}并将消息发送到失败队列
     *
     * @param context   上下文
     * @param callback  回调
     * @param throwable throwable
     * @author zhanghui
     * @date 2023/08/08 17:14:59
     * @see RetryContext
     * @see RetryCallback<T, E>
     * @see Throwable
     * @since 1.0.0
     */
    @Override
    public <E extends Throwable, T> void lastRetry(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
        String msgData = this.getData(callback);
        log.info("[消费者失败回调-lastRetry] 接收消息: {} 异常原因：{}", msgData, throwable.getCause().getMessage());
        this.rabbitTemplate.convertAndSend(RetryFailConsumer.RETRY_EXCHANGE, RetryFailConsumer.RETRY_FAILURE_KEY, msgData);
    }

}
