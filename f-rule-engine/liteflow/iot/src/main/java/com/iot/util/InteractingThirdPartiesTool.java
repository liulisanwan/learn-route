package com.iot.util;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iot.aspect.CustomDefaultContext;
import com.iot.database.entity.PlConstant;
import com.iot.database.entity.PlThirdParty;
import com.iot.database.entity.PlThirdPartyData;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 交互第三方工具
 *
 * @author zhanghui
 * @date 2023/09/15 08:36:24
 */
@Slf4j
public class InteractingThirdPartiesTool {


    /**
     * HTTP与第三方交互
     *
     * @param thirdParty 第三方
     * @param context    上下文
     * @return {@code Map<String,Object> }
     * @author zhanghui
     * @date 2023/09/15 08:44:20
     * @see PlThirdParty
     * @see CustomDefaultContext
     * @see Map<String,Object>
     * @since 1.0.0
     */
    public static Map<String,Object> httpInteractingThirdParties(PlThirdParty thirdParty, CustomDefaultContext context) {
        HttpRequest httpRequest;
        List<PlThirdPartyData> plThirdPartyDataList = thirdParty.getPlThirdPartyDataList();
        PlThirdPartyData request = plThirdPartyDataList.stream().filter(plThirdPartyData -> plThirdPartyData.getRequestResponseType().equals("request")).findFirst().get();
        PlThirdPartyData response = plThirdPartyDataList.stream().filter(plThirdPartyData -> plThirdPartyData.getRequestResponseType().equals("response")).findFirst().get();

        Map<String,Object> requestMap = JSON.parseObject(request.getDataValue(), Map.class);
        requestMap.forEach((k,v)->{
            if (context.hasData(k)){
                requestMap.put(k,context.getData(k));
            }else {
                throw new RuntimeException("参数错误");
            }
        });
        log.info("请求参数:{}",requestMap);
        ObjectMapper objectMapper = new ObjectMapper();
        switch (thirdParty.getRequestType()) {
            case "POST":
                httpRequest= HttpRequest.post(thirdParty.getUrl());
                try {
                    httpRequest.body(objectMapper.writeValueAsBytes(requestMap));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                httpRequest= HttpRequest.get(thirdParty.getUrl());
                httpRequest.form(requestMap);
                break;
        }

        String body = httpRequest.execute().body();
        System.err.println(body);
        Map<String,Object> resultMap=new HashMap<>();
        try {
            JsonNode jsonNode1 = objectMapper.readTree(body);
            JsonNode jsonNode2 = objectMapper.readTree(response.getDataValue());
            Boolean equals = compareJsonNodes(jsonNode1,jsonNode2,resultMap);
            if (!equals){
                throw new RuntimeException("响应数据比对失败");
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        List<PlConstant> constantList = context.getData("constantList");
        if (CollectionUtil.isNotEmpty(constantList)){
            resultMap.forEach((k,v)->{
                for (PlConstant plConstant : constantList) {
                    if (k.equals(plConstant.getConstantName())){
                        plConstant.setConstantValue(v.toString());
                    }
                }
            });
        }
        return resultMap;
    }

    /**
     * 比较json节点
     *
     * @param node1 node1
     * @param node2 node2
     * @param map   地图
     * @return {@code Boolean }
     * @author zhanghui
     * @date 2023/10/19 10:40:13
     * @see JsonNode
     * @see JsonNode
     * @see Map<String,Object>
     * @see Boolean
     * @since 1.0.0
     */
    private static Boolean compareJsonNodes(JsonNode node1, JsonNode node2, Map<String,Object> map) {
        if (node1.isObject() && node2.isObject()) {
            // Compare objects
            if (node1.size() != node2.size()) {
                return false;
            }

            for (Iterator<String> it = node1.fieldNames(); it.hasNext();) {
                String fieldName = it.next();
                if (!node2.has(fieldName)) {
                    return false;
                }

                JsonNode child1 = node1.get(fieldName);
                JsonNode child2 = node2.get(fieldName);

                if (child2.isTextual() && child2.textValue().isEmpty()) {
                    // If node2's value is an empty string, print node1's value and skip comparison
                    map.put(fieldName,child1);
                } else if (!compareJsonNodes(child1, child2,map)) {
                    return false;
                }
            }
            return true;
        } else if (node1.isArray() && node2.isArray()) {
            // Compare arrays
            if (node1.size() != node2.size()) {
                return false;
            }

            for (int i = 0; i < node1.size(); i++) {
                if (!compareJsonNodes(node1.get(i), node2.get(i),map)) {
                    return false;
                }
            }
            return true;
        } else {
            // Compare values
            return node1.equals(node2);
        }
    }
}
