package com.liuli.entity;

import com.liuli.config.CustomFlowExecutor;
import com.yomahub.liteflow.slot.Slot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 自定义背景
 *
 * @author zhanghui
 * @date 2023/08/22 10:04:48
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CustomContext {

    private CustomFlowExecutor customFlowExecutor;

    private Slot slot;

}
