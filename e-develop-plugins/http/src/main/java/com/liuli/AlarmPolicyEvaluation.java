package com.liuli;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;

public class AlarmPolicyEvaluation {

    public static void main(String[] args) throws Exception {
        // 创建告警策略
        AlarmPolicy alarmPolicy = new AlarmPolicy("1", "2", "and");
        
        // 创建告警条件
        AlarmPolicyCondition alarmPolicyCondition1 = new AlarmPolicyCondition("1", "=", "1", "Long");
        AlarmPolicyCondition alarmPolicyCondition2 = new AlarmPolicyCondition("2", "=", "2", "Long");
        AlarmPolicyCondition alarmPolicyCondition3 = new AlarmPolicyCondition("3", "=", "3", "Long");

        // JSON数据
        String jsonData = "{\"1\":1,\"2\":2,\"3\":4}";
        
        // 解析JSON数据
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonMap = mapper.readValue(jsonData, HashMap.class);
        
        // 评估告警策略
        boolean isAlarmTriggered = evaluateAlarmPolicy(alarmPolicy, 
            new AlarmPolicyCondition[]{alarmPolicyCondition1, alarmPolicyCondition2, alarmPolicyCondition3}, jsonMap);
        
        System.out.println("Alarm Triggered: " + isAlarmTriggered);
    }

    /**
     * 评估告警策略是否触发
     * @param policy 告警策略
     * @param conditions 告警条件数组
     * @param jsonMap 传入的JSON数据
     * @return 如果告警策略被触发返回true，否则返回false
     */
    public static boolean evaluateAlarmPolicy(AlarmPolicy policy, AlarmPolicyCondition[] conditions, Map<String, Object> jsonMap) {
        boolean result = "and".equalsIgnoreCase(policy.getConditionLogic());

        for (AlarmPolicyCondition condition : conditions) {
            Object value = jsonMap.get(condition.getAttributeId());
            if (value == null) {
                // 如果JSON数据中没有对应属性，根据逻辑操作符返回
                if ("and".equalsIgnoreCase(policy.getConditionLogic())) {
                    return false; // 如果有一个条件不满足AND条件就为false
                }
            } else {
                boolean conditionResult = evaluateCondition(condition, value);
                if ("and".equalsIgnoreCase(policy.getConditionLogic())) {
                    result = result && conditionResult;
                } else if ("or".equalsIgnoreCase(policy.getConditionLogic())) {
                    result = result || conditionResult;
                }
            }
        }

        return result;
    }

    /**
     * 评估单个条件是否满足
     * @param condition 告警条件
     * @param actualValue 实际值
     * @return 如果条件满足返回true，否则返回false
     */
    private static boolean evaluateCondition(AlarmPolicyCondition condition, Object actualValue) {
        // 这里假设所有的值都是Long类型，可以根据实际情况修改
        Long actual = Long.parseLong(actualValue.toString());
        Long expected = Long.parseLong(condition.getValue());

        switch (condition.getOperation()) {
            case "=":
                return actual.equals(expected);
            case "!=":
                return !actual.equals(expected);
            case ">":
                return actual > expected;
            case ">=":
                return actual >= expected;
            case "<":
                return actual < expected;
            case "<=":
                return actual <= expected;
            default:
                return false;
        }
    }
}

// 假设的类来表示告警策略和条件
class AlarmPolicy {
    private String id;
    private String name;
    /**
     * 条件逻辑
     */
    private String conditionLogic;

    public AlarmPolicy(String id, String name, String conditionLogic) {
        this.id = id;
        this.name = name;
        this.conditionLogic = conditionLogic;
    }

    public String getConditionLogic() { return conditionLogic; }
}

class AlarmPolicyCondition {
    private String attributeId;
    private String operation;
    private String value;
    private String type;

    public AlarmPolicyCondition(String attributeId, String operation, String value, String type) {
        this.attributeId = attributeId;
        this.operation = operation;
        this.value = value;
        this.type = type;
    }

    public String getAttributeId() { return attributeId; }
    public String getOperation() { return operation; }
    public String getValue() { return value; }
}
