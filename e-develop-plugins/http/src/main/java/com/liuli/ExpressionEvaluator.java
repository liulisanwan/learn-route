package com.liuli;

import cn.hutool.core.collection.CollectionUtil;

import javax.script.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionEvaluator {
    public static void main(String[] args) {
        String source = "20<a<40";
        String expression ;

        try {
            expression = convertSourceToExpression(source);
            System.err.println(expression);
        } catch (InvalidExpressionException e) {
            System.out.println("转换失败: " + e.getMessage());
            return;
        }

        Double a = 32.0;
        Integer b = 21;
        Map<String, Object> map = new HashMap<>();
        map.put("a", a);
        map.put("b", b);
        try {
            System.out.println(evaluateExpression(expression, map));
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    public static String convertSourceToExpression(String source) throws InvalidExpressionException {
        try {
            Pattern rangePattern = Pattern.compile("(\\d+)<(\\w+)<(\\d+)");
            Pattern logicalPattern = Pattern.compile("(\\w+)==(\\d+)");
            Matcher rangeMatcher = rangePattern.matcher(source);
            Matcher logicalMatcher = logicalPattern.matcher(source);
            StringBuffer sb = new StringBuffer();
            boolean found = false;

            // 处理范围表达式
            while (rangeMatcher.find()) {
                found = true;
                String replacement = "(" + rangeMatcher.group(1) + " < " + rangeMatcher.group(2) + " && " + rangeMatcher.group(2) + " < " + rangeMatcher.group(3) + ")";
                rangeMatcher.appendReplacement(sb, replacement);
            }
            rangeMatcher.appendTail(sb);

            if (!found) {
                // 如果没有找到范围表达式，处理逻辑表达式
                sb = new StringBuffer(); // 重置缓冲区
                while (logicalMatcher.find()) {
                    found = true;
                    String replacement = logicalMatcher.group(1) + " == " + logicalMatcher.group(2);
                    logicalMatcher.appendReplacement(sb, replacement);
                }
                logicalMatcher.appendTail(sb);
            }

            if (!found) {
                throw new InvalidExpressionException("无法解析source表达式: " + source);
            }

            // 检查表达式是否正确结束
            String expr = sb.toString().trim();
            if (!expr.endsWith(")") && !expr.startsWith("(")) {
                // 如果表达式没有括号，尝试加上括号
                if (expr.contains("||") || expr.contains("&&")) {
                    expr = "(" + expr + ")";
                }
            }

            return expr;
        } catch (Exception e) {
            throw new InvalidExpressionException("转换失败: " + e.getMessage(), e);
        }
    }


    public static Object evaluateExpression(String expression, Map<String, Object> map) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        Bindings bindings = new SimpleBindings();
        if (CollectionUtil.isNotEmpty(map)) {
            bindings.putAll(map);
        }
        return engine.eval(expression, bindings);
    }
}

class InvalidExpressionException extends Exception {
    public InvalidExpressionException(String message) {
        super(message);
    }

    public InvalidExpressionException(String message, Throwable cause) {
        super(message, cause);
    }
}