package com.iot.aspect;

import java.util.HashMap;
import java.util.Map;

/**
 * 需要移除常数
 *
 * @author zhanghui
 * @date 2023/09/12 14:26:55
 */
public enum NeedRemoveConstant {

    /**
     * 脚本节点映射
     */
    ScriptNodeMap("scriptNodeMap"),
    /**
     * 测试
     */
    Test("test");

    /**
     * 常数
     */
    private String constant;


    /**
     * 方面不断
     *
     * @param constant 常数
     * @return {@code  }
     * @author zhanghui
     * @date 2023/09/12 14:27:16
     * @see String
     * @since 1.0.0
     */
    NeedRemoveConstant(String constant) {
        this.constant = constant;
    }

    /**
     * 获得恒定
     *
     * @return {@code String }
     * @author zhanghui
     * @date 2023/09/12 14:27:16
     * @see String
     * @since 1.0.0
     */
    public String getConstant() {
        return constant;
    }

    /**
     * 设置恒定
     *
     * @param constant 常数
     * @author zhanghui
     * @date 2023/09/12 14:27:16
     * @see String
     * @since 1.0.0
     */
    public void setConstant(String constant) {
        this.constant = constant;
    }

    /**
     * 得到常数映射
     *
     * @return {@code Map<String,String> }
     * @author zhanghui
     * @date 2023/09/12 14:27:16
     * @see Map<String,String>
     * @since 1.0.0
     */
    public static Map<String,String> getConstantMap(){
        Map<String,String> map = new HashMap<>();
        for (NeedRemoveConstant needRemoveConstant : NeedRemoveConstant.values()) {
            map.put(needRemoveConstant.name(), needRemoveConstant.getConstant());
        }
        return map;
    }
}
