package com.liuli;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 数据
 *
 * @author zhanghui
 * @date 2023/05/29 13:22:22
 */
@Data
public class ClientData {

    /**
     * 机上马克斯
     */
    private Integer inflight_max;
    /**
     * recv问
     */
    private Integer recv_cnt;

    /**
     * 发送消息体
     */
    private Integer send_msg;
    /**
     * 在创建
     */
    private Date created_at;
    /**
     * mqueue下降
     */
    private Integer mqueue_dropped;
    /**
     * ip地址
     */
    private String ip_address;

    /**
     * 用户名
     */
    private String username;
    /**
     * 区
     */
    private String zone;
    /**
     * 最初名字
     */
    private String proto_name;
    /**
     * 连接
     */
    private boolean connected;

    /**
     * 是桥
     */
    private Boolean is_bridge;
    /**
     * 等待rel问
     */
    private Integer awaiting_rel_cnt;
    /**
     * mqueue马克斯
     */
    private Integer mqueue_max;
    /**
     * 干净开始
     */
    private boolean clean_start;
    /**
     * 邮箱len
     */
    private Integer mailbox_len;
    /**
     * 连接在
     */
    private Date connected_at;
    /**
     * 减少
     */
    private long reductions;
    /**
     * keepalive
     */
    private Integer keepalive;

    /**
     * clientid
     */
    private String clientid;
    /**
     *
     */
    private Boolean is_persistent;
    private String mountpoint;

    /**
     * mqueue len
     */
    private Integer mqueue_len;
    private Integer expiry_interval;
    /**
     * 发送pkt
     */
    private Integer send_pkt;

    /**
     * 订阅马克斯
     */
    private String subscriptions_max;
    /**
     * recv 10月
     */
    private Integer recv_oct;
    /**
     * 发送10月
     */
    private Integer send_oct;
    /**
     * 使authn
     */
    private Boolean enable_authn;
    /**
     * 订阅问
     */
    private Integer subscriptions_cnt;

    /**
     * 挂载点
     */
    private String mountpoInteger;

    /**
     * 侦听器
     */
    private String listener;
    /**
     * 节点
     */
    private String node;
    /**
     * 到期时间间隔
     */
    private Integer expiry_Integererval;

    /**
     * recv pkt
     */
    private Integer recv_pkt;
    /**
     * 原型版本
     */
    private Integer proto_ver;
    /**
     * 堆大小
     */
    private Integer heap_size;
    /**
     * 港口
     */
    private Integer port;

    /**
     * 等待rel马克斯
     */
    private Integer awaiting_rel_max;

    /**
     * 把问
     */
    private Integer send_cnt;
    /**
     * 飞行中问
     */
    private Integer inflight_cnt;

    private Integer recv_msg;

    /**
     * 下降了
     */
    @JsonProperty(value = "recv_msg.dropped")
    private Integer dropped;
    /**
     * 等待pubrel超时
     */
    @JsonProperty(value = "recv_msg.dropped.await_pubrel_timeout")
    private Integer await_pubrel_timeout;
    /**
     * qos0
     */
    @JsonProperty(value = "recv_msg.qos0")
    private Integer qos0;
    /**
     * qos1
     */
    @JsonProperty(value = "recv_msg.qos1")
    private Integer qos1;
    /**
     * qos2
     */
    @JsonProperty(value = "recv_msg.qos2")
    private Integer qos2;
    /**
     * 发送了
     */
    @JsonProperty(value = "send_msg.dropped")
    private Integer send_dropped;
    /**
     * 过期
     */
    @JsonProperty(value = "send_msg.dropped.expired")
    private Integer expired;
    /**
     * 队列满
     */
    @JsonProperty(value = "send_msg.dropped.queue_full")
    private Integer queue_full;
    /**
     * 太大
     */
    @JsonProperty(value = "send_msg.dropped.too_large")
    private Integer too_large;
    /**
     * 发送qos0
     */
    @JsonProperty(value = "send_msg.qos0")
    private Integer send_qos0;
    /**
     * 发送qos1
     */
    @JsonProperty(value = "send_msg.qos1")
    private Integer send_qos1;
    /**
     * 发送qos2
     */
    @JsonProperty(value = "send_msg.qos2")
    private Integer send_qos2;

    private List<?> ws_cookie;
}