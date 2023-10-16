package com.liuli.c_j_visitor;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author pcc
 */

public class Person {
    String name; // 姓名
    String identity; // 身份
}

// 这是学生类，他拥有分数
@EqualsAndHashCode(callSuper = true)
@Data
class Student extends Person {
    Integer score;
}


@Data
@EqualsAndHashCode(callSuper = true)
class Teacher extends Person {
    String level; // 教师职级
}

@Data
@EqualsAndHashCode(callSuper = true)
class Parent extends Person {
    String love; // 父母的爱
}