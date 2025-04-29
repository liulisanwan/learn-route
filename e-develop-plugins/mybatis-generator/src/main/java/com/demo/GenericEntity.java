package com.demo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;


/**
 * generic entity
 * 通用实体
 *
 * @author:zhanghui
 * @date: 2023/09/25 14:51:21
 */
@Data
public class GenericEntity implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 创建时间戳
     */
    @OrderBy
    //@TableField(fill = FieldFill.INSERT)
    private Long createAt;
    /**
     * 创建人
     */
    //@TableField(fill = FieldFill.INSERT)
    private Long createBy;
    /**
     * 修改时间戳
     */
    //@TableField(fill = FieldFill.INSERT_UPDATE)
    private Long modifyAt;
    /**
     * 修改人
     */
    //@TableField(fill = FieldFill.INSERT_UPDATE)
    private Long modifyBy;
    /**
     * 删除标记
     */
    @TableLogic
    private Boolean deleted = false;

    /**
     * 版本
     */
    @Version
    private Long version;

    public static void main(String[] args) {
        String path = new GenericEntity().getClass().getClassLoader().getResource("/").getPath();
        System.err.println(path);


    }
}
