/**
  * Copyright 2022 json.cn 
  */
package com.liuli;


import lombok.Data;

/**
 * 分页信息
 *
 * @author zhanghui
 * @date 2022/09/27 11:25:11
 */
@Data
public class Meta {

    /**
     * 页码
     */
    private Integer page;
    /**
     * 每页显示的数据条数
     */
    private Integer limit;
    /**
     * 是否具有下一页
     */
    private Boolean hasnext;
    /**
     * 数据总条数
     */
    private Integer count;



}