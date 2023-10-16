package com.liuli.student.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 学生dto
 *
 * @author zhanghui
 * @date 2023/02/28 09:11:57
 */
@Data
public class StudentDTO implements Serializable {

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
    private String grandId;

    /**
     * 名称
     */
    private String grandName;

    /**
     * 负责人
     */
    private String chargePerson;

    /**
     * 学校id
     */
    private Integer schoolId;

    /**
     * 名称
     */
    private String schoolName;

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

