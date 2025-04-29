package com.liuli;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 请求数据
 *
 * @author zhanghui
 * @date 2024/10/09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestData {
    private String bomType;
    private JSONObject data;
}
