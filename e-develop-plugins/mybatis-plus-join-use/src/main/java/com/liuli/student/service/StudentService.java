package com.liuli.student.service;


import com.github.yulichang.base.MPJBaseService;
import com.liuli.student.dto.StudentDTO;
import com.liuli.student.entity.Student;

import java.util.List;

/**
 * <p>
 * 学生表 服务类
 * </p>
 *
 * @author hui-zhang
 * @since 2023-02-28
 */
public interface StudentService extends MPJBaseService<Student> {

    /**
     * 得到学生所有信息
     *
     * @return {@code List<StudentDTO> }
     * @author zhanghui
     * @date 2023/02/28 09:36:02
     * @see List< StudentDTO >
     * @since 1.0.0
     */
    List<StudentDTO> getStudentAllInfo();
}
