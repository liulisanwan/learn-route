/**
 * Copyright 2022 json.cn
 */
package com.liuli.entity;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import lombok.Data;

import java.util.Date;

/**
 * 旋思网关读取设备参数
 *
 * @author zhanghui
 * @date 2022/10/24 14:37:31
 */
@Data
public class SymLinkReadEquipmentParam {

    /**
     * 转发标签如果属于虚拟设备,这里是虚拟设备的名称
     */
    private String ppn;
    /**
     * 源标签描述,点位描述
     */
    private String nm;
    /**
     * 秒级时间戳
     */
    private Long ts;
    /**
     * tsm
     */
    private Integer tsm;
    /**
     * 问
     */
    private Integer q;
    /**
     * vt
     */
    private Integer vt;
    /**
     * v
     */
    private Object v;

    /**
     * 数据时间
     */
    private Date dataTime;

    private String t;

    public void setTs(Long ts) {
        this.ts = ts;
        if (ts!=null){
            this.dataTime= DateUtil.date(ts*1000);
        }
    }

    public void setT(String t) {
        this.t = t;
        if (StrUtil.isNotBlank(t)) {
            this.dataTime=DateUtil.parse(t,"yyyy-MM-dd HH:mm:ss");
        }
    }
}