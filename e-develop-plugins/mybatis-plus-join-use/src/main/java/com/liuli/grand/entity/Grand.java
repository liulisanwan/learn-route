package com.liuli.grand.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 班级
 * </p>
 *
 * @author hui-zhang
 * @since 2023-02-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Grand implements Serializable {

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
     * 负责人
     */
    private String chargePerson;

    /**
     * 学校id
     */
    private Integer schoolId;


}
