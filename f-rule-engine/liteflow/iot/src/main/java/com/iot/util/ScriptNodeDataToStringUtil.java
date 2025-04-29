package com.iot.util;

import com.iot.custom_node.CustomNodeExecuteType;

/**
 * 脚本节点数据转字符串工具类
 *
 * @author zhanghui
 * @date 2023/09/14 09:00:16
 */
public class ScriptNodeDataToStringUtil {


    /**
     * 无效脚本节点数据
     *
     * @param customNodeExecuteType 执行类型
     * @return {@code String }
     * @author zhanghui
     * @date 2023/09/14 09:00:50
     * @see CustomNodeExecuteType
     * @see String
     * @since 1.0.0
     */
    public static String voidScriptNodeData(CustomNodeExecuteType customNodeExecuteType){
        String data="import com.iot.factory.ExecuteType;\n" +
                "import com.iot.custom_node.NodeExecuteFactory;\n" +
                "import com.yomahub.liteflow.script.ScriptExecuteWrap;\n" +
                "import com.yomahub.liteflow.script.body.JaninoCommonScriptBody;\n" +
                "import com.yomahub.liteflow.spi.holder.ContextAwareHolder;\n" +
                "public class Demo implements JaninoCommonScriptBody {\n" +
                "    public Void body(ScriptExecuteWrap executeWrap) {\n" +
                "        NodeExecuteFactory nodeExecuteFactory = (NodeExecuteFactory) ContextAwareHolder.loadContextAware().getBean(NodeExecuteFactory.class);\n" +
                "        nodeExecuteFactory.execute("+getNodeExecuteType(customNodeExecuteType)+",executeWrap);\n" +
                "        return null;\n" +
                "}}";
        return data;
    }

    public static String voidTestData(){
        String data="import com.yomahub.liteflow.script.ScriptExecuteWrap;\n" +
                "import com.yomahub.liteflow.script.body.JaninoCommonScriptBody;\n" +
                "\n" +
                "\n" +
                "public class ListenDemo2 implements JaninoCommonScriptBody {\n" +
                "    public Void body(ScriptExecuteWrap executeWrap) {\n" +
                "        String nodeId = executeWrap.cmp.getNodeId();\n" +
                "        System.err.println(nodeId+\"执行了\");\n" +
                "        return null;\n" +
                "    }\n" +
                "}";
        return data;
    }


    /**
     * 选择脚本节点数据
     *
     * @return {@code String }
     * @author zhanghui
     * @date 2023/09/14 09:00:55
     * @see String
     * @since 1.0.0
     */
    public static String switchScriptNodeData(){
        String data="import com.iot.factory.ExecuteType;\n" +
                "import com.iot.custom_node.NodeExecuteFactory;\n" +
                "import com.yomahub.liteflow.script.ScriptExecuteWrap;\n" +
                "import com.yomahub.liteflow.script.body.JaninoSwitchScriptBody;\n" +
                "import com.yomahub.liteflow.spi.holder.ContextAwareHolder;\n" +
                "\n" +
                "public class Demo3 implements JaninoSwitchScriptBody {\n" +
                "    \n" +
                "    public String body(ScriptExecuteWrap executeWrap) {\n" +
                "        NodeExecuteFactory nodeExecuteFactory = (NodeExecuteFactory) ContextAwareHolder.loadContextAware().getBean(NodeExecuteFactory.class);\n" +
                "        String key = nodeExecuteFactory.execute(ExecuteType.SWITCH, executeWrap).toString();\n" +
                "        return key;\n" +
                "    }\n" +
                "}";
        return data;
    }

    /**
     * 如果脚本节点数据
     *
     * @return {@code String }
     * @author zhanghui
     * @date 2023/09/14 09:01:03
     * @see String
     * @since 1.0.0
     */
    public static String ifScriptNodeData(){
        String data="import com.iot.factory.ExecuteType;\n" +
                "import com.iot.custom_node.NodeExecuteFactory;\n" +
                "import com.yomahub.liteflow.script.ScriptExecuteWrap;\n" +
                "import com.yomahub.liteflow.script.body.JaninoIfScriptBody;\n" +
                "import com.yomahub.liteflow.spi.holder.ContextAwareHolder;\n" +
                "\n" +
                "public class IfDemo implements JaninoIfScriptBody {\n" +
                "    \n" +
                "    public Boolean body(ScriptExecuteWrap executeWrap) {\n" +
                "        NodeExecuteFactory nodeExecuteFactory = (NodeExecuteFactory) ContextAwareHolder.loadContextAware().getBean(NodeExecuteFactory.class);\n" +
                "        Boolean execute = (Boolean)nodeExecuteFactory.execute(ExecuteType.IF, executeWrap);\n" +
                "        return execute;\n" +
                "    }\n" +
                "}}";
        return data;
    }



    private static String getNodeExecuteType(CustomNodeExecuteType customNodeExecuteType){
        return "ExecuteType."+ customNodeExecuteType.name();
    }
}
