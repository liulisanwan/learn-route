package com.liuli;

import cn.hutool.core.date.DateUtil;

import java.util.Date;

public class a {
    public static void main(String[] args) {
        long timeLong=1739785143;
        timeLong=timeLong*1000;
        Date date = new Date(timeLong);
        System.out.println(DateUtil.formatDateTime(date));
    }
}
