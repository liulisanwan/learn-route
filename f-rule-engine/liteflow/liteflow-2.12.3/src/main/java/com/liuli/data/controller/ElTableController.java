package com.liuli.data.controller;

import com.ivy.vueflow.parser.entity.FlowData;
import com.ivy.vueflow.parser.graph.Graph;
import com.ivy.vueflow.parser.graph.GraphInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ZhangHui
 * @since 2024-09-23
 */
@RestController
@Api(tags = "el-table")
@RequestMapping("/el-table")
public class ElTableController {


    @PostMapping("/jsonToEL")
    @ApiOperation(value = "json转EL")
    public void jsonToEL(@RequestBody FlowData data) throws Exception {
            Graph graph = new Graph(data);
            GraphInfo graphInfo = graph.toELInfo();
            String result = graphInfo.toString();
            System.out.println(result);

    }
}
