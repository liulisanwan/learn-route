package com.iot.database.controller;

import com.alibaba.fastjson.JSON;
import com.iot.chain.ChainBuilderTreeData;
import com.iot.util.TreeDataBuildELWrapperUtil;
import com.yomahub.liteflow.builder.el.ELWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * el表达式测试控制器
 *
 * @author zhanghui
 * @date 2023/10/18 14:03:14
 */
@RestController
@RequestMapping("/ELTest")
@Api(tags = "EL表达式测试")
public class ELTestController {



    @GetMapping("/convert")
    @ApiOperation("转换")
    public String convert(String treeData){
        ChainBuilderTreeData chainBuilderTreeData = JSON.parseObject(treeData, ChainBuilderTreeData.class);
        ELWrapper elWrapper = TreeDataBuildELWrapperUtil.convertOTreeDataToELWrapper(chainBuilderTreeData);
        return elWrapper.toEL();
    }

}
