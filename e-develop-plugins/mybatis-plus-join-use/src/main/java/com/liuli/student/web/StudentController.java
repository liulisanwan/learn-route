package com.liuli.student.web;


import com.liuli.student.dto.StudentDTO;
import com.liuli.student.service.StudentService;
import com.liuli.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 学生表 前端控制器
 * </p>
 *
 * @author hui-zhang
 * @since 2023-02-28
 */
@RestController
@RequestMapping("/student")
@Api
public class StudentController {

    @Autowired
    StudentService studentService;

    @ApiOperation("联动查询数据")
    @GetMapping("/getStudentAllInfo")
    public Result<List<StudentDTO>> getStudentAllInfo(){
        List<StudentDTO> studentAllInfo = studentService.getStudentAllInfo();
        return Result.success(studentAllInfo);
    }

}

