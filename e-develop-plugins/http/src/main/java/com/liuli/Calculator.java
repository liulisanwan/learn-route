package com.liuli;

import java.util.Stack;

public class Calculator {

    public static void main(String[] args) {
        String expression = "32 < (40 + 5) && 50 >= 25 || 10 != 10";
        try {
            System.out.println("Result: " + evaluateExpression(expression));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static boolean evaluateExpression(String expression) throws IllegalArgumentException {
        // 预处理表达式，移除空格
        expression = expression.replaceAll("\\s+", "");

        Stack<Boolean> booleanStack = new Stack<>();
        Stack<Integer> intStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (Character.isDigit(c)) {
                // 解析数字
                StringBuilder sb = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    sb.append(expression.charAt(i++));
                }
                intStack.push(Integer.parseInt(sb.toString()));
                i--; // 回退，因为for循环会自动i++
            } else if (c == '(') {
                operatorStack.push(c);
            } else if (c == ')') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    evaluateOperator(operatorStack, intStack, booleanStack);
                }
                if (operatorStack.isEmpty() || operatorStack.pop() != '(') {
                    throw new IllegalArgumentException("Mismatched parentheses");
                }
            } else if (isOperator(c)) {
                while (!operatorStack.isEmpty() && precedence(operatorStack.peek()) >= precedence(c)) {
                    evaluateOperator(operatorStack, intStack, booleanStack);
                }
                operatorStack.push(c);
            } else {
                throw new IllegalArgumentException("Invalid character in expression: " + c);
            }
        }

        // 处理剩余的运算符
        while (!operatorStack.isEmpty()) {
            if (operatorStack.peek() == '(') {
                throw new IllegalArgumentException("Mismatched parentheses");
            }
            evaluateOperator(operatorStack, intStack, booleanStack);
        }

        if (booleanStack.size() != 1 || !intStack.isEmpty()) {
            throw new IllegalArgumentException("Invalid expression: Mismatched operators or operands");
        }

        return booleanStack.pop(); // 假设表达式结果为boolean
    }

    private static void evaluateOperator(Stack<Character> operatorStack, Stack<Integer> intStack, Stack<Boolean> booleanStack) throws IllegalArgumentException {
        if (operatorStack.isEmpty()) {
            throw new IllegalArgumentException("Invalid expression: Operator stack is empty");
        }

        char op = operatorStack.peek();
        if (isLogicalOperator(op)) {
            if (booleanStack.size() < (op == '!' ? 1 : 2)) {
                throw new IllegalArgumentException("Invalid expression: Not enough operands for logical operator");
            }
        } else if (isRelationalOperator(op)) {
            if (intStack.size() < 2) {
                throw new IllegalArgumentException("Invalid expression: Not enough operands for relational operator");
            }
        } else {
            if (intStack.size() < 2) {
                throw new IllegalArgumentException("Invalid expression: Not enough operands for arithmetic operator");
            }
        }

        op = operatorStack.pop(); // 确保在检查后再弹出操作符

        if (isLogicalOperator(op)) {
            if (op == '!') {
                boolean b1 = booleanStack.pop();
                booleanStack.push(evaluateLogical(op, b1, false)); // 这里假设第二个操作数是false
            } else {
                boolean b2 = booleanStack.pop();
                boolean b1 = booleanStack.pop();
                booleanStack.push(evaluateLogical(op, b1, b2));
            }
        } else if (isRelationalOperator(op)) {
            int n2 = intStack.pop();
            int n1 = intStack.pop();
            booleanStack.push(evaluateRelational(op, n1, n2));
        } else {
            int n2 = intStack.pop();
            int n1 = intStack.pop();
            intStack.push(evaluateArithmetic(op, n1, n2));
        }
    }


    private static int evaluateArithmetic(char op, int n1, int n2) {
        switch(op) {
            case '+': return n1 + n2;
            case '-': return n1 - n2;
            case '*': return n1 * n2;
            case '/':
                if (n2 == 0) throw new ArithmeticException("Division by zero");
                return n1 / n2;
            case '%': return n1 % n2;
            case '&': return n1 & n2;
            case '|': return n1 | n2;
            case '^': return n1 ^ n2;
            case '<': return n1 << n2;
            case '>': return n1 >> n2;
            default: throw new IllegalArgumentException("Unknown operator: " + op);
        }
    }

    private static boolean evaluateRelational(char op, int n1, int n2) {
        switch(op) {
            case '<': return n1 < n2;
            case '>': return n1 > n2;
            case '=': return n1 == n2;
            case 'n': return n1 != n2; // '!=' 替换为 'n'
            case 'L': return n1 <= n2; // '<=' 替换为 'L'
            case 'G': return n1 >= n2; // '>=' 替换为 'G'
            default: throw new IllegalArgumentException("Unknown operator: " + op);
        }
    }

    private static boolean evaluateLogical(char op, boolean b1, boolean b2) {
        switch(op) {
            case '&': return b1 && b2;
            case '|': return b1 || b2;
            case '!': return !b1;
            default: throw new IllegalArgumentException("Unknown operator: " + op);
        }
    }

    private static boolean isOperator(char c) {
        return "+-*/%&|^<>=!()nLG".indexOf(c) != -1;
    }

    private static boolean isLogicalOperator(char c) {
        return c == '&' || c == '|' || c == '!';
    }

    private static boolean isRelationalOperator(char c) {
        return "<>=nLG".indexOf(c) != -1;
    }

    private static int precedence(char op) {
        switch (op) {
            case '!': return 3; // 逻辑非的优先级最高
            case '*': case '/': case '%': return 2; // 乘法、除法和取模
            case '+': case '-': return 1; // 加法和减法
            case '<': case '>': case 'L': case 'G': return 1; // 关系运算符
            case '=': case 'n': return 1; // 相等和不等运算符
            case '&': return -1; // 按位与
            case '|': return -2; // 按位或
            case '(': return -100; // 左括号
            default: return 0;
        }
    }
}
