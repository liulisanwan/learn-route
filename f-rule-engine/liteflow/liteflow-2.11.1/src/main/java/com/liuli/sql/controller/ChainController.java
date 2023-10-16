package com.liuli.sql.controller;


import com.liuli.sql.service.ChainService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hui-zhang
 * @since 2023-06-14
 */
@RestController
@RequestMapping("/chain")
@Api(tags = "执行链")
public class ChainController {

    @Autowired
    ChainService chainService;


    public void insertData(){

    }

}

