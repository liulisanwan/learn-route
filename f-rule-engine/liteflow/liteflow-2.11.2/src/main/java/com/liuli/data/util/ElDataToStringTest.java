package com.liuli.data.util;

import cn.hutool.json.JSONUtil;
import com.liuli.data.entity.ElReceiveData;
import com.liuli.data.entity.ElType;
import com.yomahub.liteflow.builder.el.ELWrapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 将数据转换为字符串测试
 *
 * @author zhanghui
 * @date 2023/10/16 17:04:53
 */
public class ElDataToStringTest {

    public static void main(String[] args) {
        /*
         *
         * WHEN()
         * node("a")
         * WHEN(node("b"),WHEN(node("b1")),WHEN(node("d1"))).id("a1")
         * node("b")
         * (node("b1"),node("b2"))
         * (node("d1"),node("d2"))
         * WHEN(node("d"),WHEN(node("b1")),WHEN(node("d1"))).id("a2")
         *
         *
         *
         *
         *
         *
         * node("a")
         * WHEN(node("b"),WHEN(node("b1")),WHEN(node("d1"))).id("a1")
         * WHEN(node("d"),WHEN(node("b1")),WHEN(node("d1"))).id("a2")
         *
         * node("b")
         * WHEN(node("b1")),WHEN(node("d1"))
         *
         *
         * */
        ElReceiveData data= ElReceiveData.builder().type(ElType.WhenELWrapper).nodeIdList(Arrays.asList("a")).build();
        ElReceiveData data2= ElReceiveData.builder().type(ElType.WhenELWrapper).nodeIdList(Arrays.asList("a")).id("a1").build();
        ElReceiveData data3= ElReceiveData.builder().type(ElType.ThenELWrapper).nodeIdList(Arrays.asList("a")).id("a2").build();
        ElReceiveData data4= ElReceiveData.builder().type(ElType.WhenELWrapper).nodeIdList(Arrays.asList("a")).build();
        ElReceiveData data5= ElReceiveData.builder().type(ElType.WhenELWrapper).nodeIdList(Arrays.asList("a")).build();
        ElReceiveData data6= ElReceiveData.builder().type(ElType.WhenELWrapper).nodeIdList(Arrays.asList("a")).build();
        ElReceiveData data7= ElReceiveData.builder().type(ElType.WhenELWrapper).nodeIdList(Arrays.asList("a")).build();
        List<ElReceiveData> childrenList=new ArrayList<>();
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
        ELWrapper elWrapper = ElDataToString.convertObjectToELWrapper(data);
        System.err.println(elWrapper.toEL(true));

    }
}
