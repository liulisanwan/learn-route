package com.liuli.student.service.impl;


import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.liuli.grand.entity.Grand;
import com.liuli.school.entity.School;
import com.liuli.student.dao.StudentDao;
import com.liuli.student.dto.StudentDTO;
import com.liuli.student.entity.Student;
import com.liuli.student.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 学生表 服务实现类
 * </p>
 *
 * @author hui-zhang
 * @since 2023-02-28
 */
@Service
public class StudentServiceImpl extends MPJBaseServiceImpl<StudentDao, Student> implements StudentService {

    @Override
    public List<StudentDTO> getStudentAllInfo() {
        //SELECT t.id,t.name,t.sex,t.age,t.grand_id,
        // t1.charge_person, t1.school_id,t1.name AS grandName,
        // t2.location,t2.phone,t2.school_charge_person,t2.name AS schoolName
        // FROM student t
        // LEFT JOIN grand t1 ON (t1.id = t.grand_id)
        // LEFT JOIN school t2 ON (t2.id = t1.school_id)
        //构建多表连接查询语句
        MPJLambdaWrapper<Student> wrapper = new MPJLambdaWrapper<Student>()
                //查询Student类的所有信息
                .selectAll(Student.class)
                //查询Grand表的chargePerson和schoolId
                .select(Grand::getChargePerson,Grand::getSchoolId)
                //将Grand表的chargePerson字段名改为grandChargePerson
                .selectAs(Grand::getName,StudentDTO::getGrandName)
                //查询School表的location,phone,schoolChargePerson
                .select(School::getLocation,School::getPhone,School::getSchoolChargePerson)
                //将School表的location字段名改为schoolLocation
                .selectAs(School::getName,StudentDTO::getSchoolName)
                //左连接Grand表,条件为Student表的grandId=Grand表的id
                .leftJoin(Grand.class,Grand::getId,Student::getGrandId)
                //左连接School表,条件为Grand表的schoolId=School表的id
                .leftJoin(School.class,School::getId,Grand::getSchoolId);
        //返回多表连接查询结果
        return this.selectJoinList(StudentDTO.class, wrapper);
    }
}
