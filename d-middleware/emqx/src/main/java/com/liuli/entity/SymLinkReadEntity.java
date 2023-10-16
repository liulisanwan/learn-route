/**
  * Copyright 2022 json.cn 
  */
package com.liuli.entity;

import lombok.Data;

import java.util.List;

/**
 * 旋思网关读取数据主体
 *
 * @author zhanghui
 * @date 2022/10/24 14:36:41
 */
@Data
public class SymLinkReadEntity {

    /**
     * 版本
     */
    private Integer ver;
    /**
     * 中期
     */
    private String mid;
    /**
     * 计
     */
    private String meter;
    /**
     * cid
     */
    private String cid;
    /**
     * 数据
     */
    private List<SymLinkReadEquipmentParam> datas;


}