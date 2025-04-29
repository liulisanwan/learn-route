package com.iot.chain;

import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 测试
 *
 * @author zhanghui
 * @date 2023/10/18 14:07:20
 */
public class Test {
    public static void main(String[] args) {
        ChainBuilderTreeData data= ChainBuilderTreeData.builder().type(ELWrapperType.WhenELWrapper).nodeIdList(Arrays.asList("a")).build();
        ChainBuilderTreeData data2= ChainBuilderTreeData.builder().type(ELWrapperType.WhenELWrapper).nodeIdList(Arrays.asList("a")).id("a1").build();
        ChainBuilderTreeData data3= ChainBuilderTreeData.builder().type(ELWrapperType.ThenELWrapper).nodeIdList(Arrays.asList("a")).id("a2").build();
        ChainBuilderTreeData data4= ChainBuilderTreeData.builder().type(ELWrapperType.WhenELWrapper).nodeIdList(Arrays.asList("a")).build();
        ChainBuilderTreeData data5= ChainBuilderTreeData.builder().type(ELWrapperType.WhenELWrapper).nodeIdList(Arrays.asList("a")).build();
        ChainBuilderTreeData data6= ChainBuilderTreeData.builder().type(ELWrapperType.WhenELWrapper).nodeIdList(Arrays.asList("a")).build();
        ChainBuilderTreeData data7= ChainBuilderTreeData.builder().type(ELWrapperType.WhenELWrapper).nodeIdList(Arrays.asList("a")).build();
        List<ChainBuilderTreeData> childrenList=new ArrayList<>();
        childrenList.add(data4);
        childrenList.add(data5);
        data2.setChildrenList(childrenList);
        childrenList=new ArrayList<>();
        childrenList.add(data6);
        childrenList.add(data7);
        data3.setChildrenList(childrenList);
        childrenList=new ArrayList<>();
        childrenList.add(data2);
        childrenList.add(data3);
        data.setChildrenList(childrenList);
        System.err.println(JSONUtil.toJsonStr(data));
    }
}
