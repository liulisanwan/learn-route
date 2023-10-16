package com.liuli.data.util;

import com.liuli.data.entity.ElReceiveData;
import com.liuli.data.entity.ElType;
import com.yomahub.liteflow.builder.el.ELWrapper;

import java.util.ArrayList;
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
        * WHEN(node("d"),WHEN(node("b1")),WHEN(node("d1"))).id("a2")
        * */
        ElReceiveData data= ElReceiveData.builder().type(ElType.WhenELWrapper).NodeId("a").build();
        ElReceiveData data2= ElReceiveData.builder().type(ElType.WhenELWrapper).NodeId("b").id("a1").build();
        ElReceiveData data3= ElReceiveData.builder().type(ElType.WhenELWrapper).NodeId("d").id("a2").build();
        ElReceiveData data4= ElReceiveData.builder().type(ElType.WhenELWrapper).NodeId("b1").build();
        ElReceiveData data5= ElReceiveData.builder().type(ElType.WhenELWrapper).NodeId("d1").build();
        ElReceiveData data6= ElReceiveData.builder().type(ElType.WhenELWrapper).NodeId("b1").build();
        ElReceiveData data7= ElReceiveData.builder().type(ElType.WhenELWrapper).NodeId("d1").build();
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
        ELWrapper elWrapper = ElDataToString.dataToString(data);
        System.err.println(elWrapper.toEL());

    }
}
