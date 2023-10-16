package com.liuli.school.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 学校
 * </p>
 *
 * @author hui-zhang
 * @since 2023-02-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class School implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 学校负责人
     */
    private String schoolChargePerson;

    /**
     * 地址
     */
    private String location;

    /**
     * 学校电话
     */
    private String phone;


}
