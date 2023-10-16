package com.liuli.entity;


import lombok.*;

import java.io.Serializable;

/**
 * <p>
 * 学生表
 * </p>
 *
 * @author hui-zhang
 * @since 2023-02-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private Integer id;

    /**
     * 名字
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 班级id
     */
    private String grand_id;


}
