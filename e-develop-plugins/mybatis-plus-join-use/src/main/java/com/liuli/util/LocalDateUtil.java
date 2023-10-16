package com.liuli.util;


import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

/**
 * 当地日期工具类
 *
 * @author hui-zhang
 * @date 2021/10/14 14:56:28
 */
public class LocalDateUtil {


    /**
     * 日期字符串ymd
     * 日期转字符串格式为年月日
     *
     * @param date 日期
     * @return {@link String}
     */
    public static String DateToStringYMD(Date date) {
        return DateUtil.format(date, "yyyy-MM-dd");
    }

    /**
     * 日期字符串ymd
     *
     * @param date    日期
     * @param replace 取代
     * @return {@link String}
     */
    public static String DateToStringYMD(Date date, String replace) {
        return DateUtil.format(date, "yyyy-MM-dd").replace(replace, "");
    }

    /**
     * 日期字符串ymdhms
     * 日期转字符串 格式为年月日时分秒
     *
     * @param date 日期
     * @return {@link String}
     */
    public static String DateToStringYMDHMS(Date date) {
        return DateUtil.format(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 日期字符串ym
     *
     * @param date 日期
     * @return {@link String}
     */
    public static String DateToStringYM(Date date) {
        return DateUtil.format(date, "yyyy-MM");
    }

    /**
     * hms日期字符串
     *
     * @param date 日期
     * @return {@link String}
     */
    public static String DateToStringHMS(Date date) {
        return DateUtil.formatTime(date);
    }

    /**
     * 日期字符串嗯
     *
     * @param date 日期
     * @return {@link String}
     */
    public static String DateToStringHM(Date date) {
        return DateUtil.format(date, "HH:mm");
    }

    /**
     * 字符串ymd日期
     * 字符串转日期 格式为年月日
     *
     * @param date 日期
     * @return {@link Date}
     */
    public static Date StringToDateYMD(String date) {
        return DateUtil.parse(date, "yyyy-MM-dd");
    }

    public static Date StringYearToDateYMD(String date) {
        return DateUtil.parse(date, "yyyy年MM月dd日");
    }

    /**
     * 字符串ym日期
     *
     * @param date 日期
     * @return {@link Date}
     */
    public static Date StringToDateYM(String date) {
        return DateUtil.parse(date, "yyyy-MM");
    }

    public static Date StringYearToDateYM(String date) {
        return DateUtil.parse(date, "yyyy年MM月");
    }

    /**
     * 字符串ymdhms日期
     * 字符串转日期 格式为年月日时分秒
     *
     * @param date 日期
     * @return {@link Date}
     */
    public static Date StringToDateYMDHMS(String date) {

        return DateUtil.parse(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String StringToStringYM(String date) {
        return DateUtil.format(DateUtil.parse(date, "yyyy-MM"), "yyyy年MM月");
    }


    /**
     * 字符串ymdhms2日期
     *
     * @param date 日期
     * @return {@link Date}
     */
    public static Date StringToDateYMDHMSXieGang(String date) {
        return DateUtil.parse(date, "yyyy/MM/dd HH:mm:ss");
    }

    /**
     * 字符串hms日期
     *
     * @param date 日期
     * @return {@link Date}
     */
    public static Date StringToDateHMS(String date) {
        return DateUtil.parse(date, "HH:mm:ss");
    }


    /**
     * 字符串到目前为止嗯
     *
     * @param date 日期
     * @return {@link Date}
     */
    public static Date StringToDateHM(String date) {
        return DateUtil.parse(date, "HH:mm");
    }


    public static String calculate(String dateString) {
        Date date = null;
        Long between = null;
        String calculate = null;
        switch (dateString.length()) {
            case 10:
                date = DateUtil.parse(dateString, "yyyy-MM-dd");
                between = DateUtil.between(new Date(), date, DateUnit.DAY);
                calculate = between + "d";
                break;
            case 13:
                date = DateUtil.parse(dateString, "yyyy-MM-dd HH");
                between = DateUtil.between(new Date(), date, DateUnit.HOUR);
                calculate = between + "h";
                break;
            case 16:
                date = DateUtil.parse(dateString, "yyyy-MM-dd HH:mm");
                between = DateUtil.between(date, new Date(), DateUnit.MINUTE);
                calculate = between + "m";
                break;
            case 19:
                date = DateUtil.parse(dateString, "yyyy-MM-dd HH:mm:ss");
                between = DateUtil.between(new Date(), date, DateUnit.SECOND);
                calculate = between + "s";
                break;
            default:
        }
        return "-" + calculate;
    }

    public static Long calculateLong(String dateString) {
        ChronoUnit unit = null;
        Date date = null;
        Long between = null;
        switch (dateString.length()) {
            case 10:
                date = DateUtil.parse(dateString, "yyyy-MM-dd");
                between = DateUtil.between(new Date(), date, DateUnit.DAY);
                unit = ChronoUnit.DAYS;
                break;
            case 13:
                date = DateUtil.parse(dateString, "yyyy-MM-dd HH");
                between = DateUtil.between(new Date(), date, DateUnit.HOUR);
                unit = ChronoUnit.HOURS;
                break;
            case 16:
                date = DateUtil.parse(dateString, "yyyy-MM-dd HH:mm");
                between = DateUtil.between(date, new Date(), DateUnit.MINUTE);
                unit = ChronoUnit.MINUTES;
                break;
            case 19:
                date = DateUtil.parse(dateString, "yyyy-MM-dd HH:mm:ss");
                between = DateUtil.between(new Date(), date, DateUnit.SECOND);
                unit = ChronoUnit.SECONDS;
                break;
            default:
        }
        return between * -1;
    }

    /**
     * 添加日期
     *
     * @param map  地图
     * @param name 名字
     * @return {@link Map}<{@link String}, {@link Object}>
     */
    public static Map<String, Object> addDate(Map<String, Object> map, String name) {
        if (map.get(name) == null) {
            map.put(name, LocalDateUtil.DateToStringYMD(new Date()));
        } else if (map.get(name) != null) {
            if (StrUtil.isEmpty(map.get(name).toString())) {
                map.put(name, LocalDateUtil.DateToStringYMD(new Date()));
            }
        }
        return map;
    }

    /**
     * 字符串转date(年)
     *
     * @param startTime 开始时间
     * @return {@link Date }
     * @author zhanghui
     * @date 2022/12/12 16:07:03
     * @since 1.0.0
     */
    public static Date StringToDateY(String startTime) {
        return DateUtil.parse(startTime, "yyyy");
    }

    public static String DateToStringMD(Date date) {
        return DateUtil.format(date, "MM-dd");
    }
}
