package com.example.concerto.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * DatesUtils
 *
 * @Author: LETOO
 * @Date: 2021/4/29 20:24
 * @Version: 1.0
 **/
@Slf4j
public class DatesUtils {

    static public SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 使用原生JDK进行计算，在JDK8中提供了更为直接和完善的方法
     *
     * @param one
     * @param two
     * @return
     */
    public static long getTermDays1(Date one, Date two) {
        long difference = (one.getTime() - two.getTime()) / 86400000;
        return Math.abs(difference);
    }

    /**
     * date2比date1多的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int getTermDays2(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2) { //同一年
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) //闰年
                {
                    timeDistance += 366;
                } else { //不是闰年
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2 - day1);
        } else { //不同年
            return day2 - day1;
        }
    }


    /**
     * 获取起止日期
     *
     * @param sdf    需要显示的日期格式
     * @param date   需要参照的日期
     * @param n      最近n周
     * @param option 0 开始日期；1 结束日期
     * @param k      0 包含本周 1 不包含本周
     * @return
     */
    public static String getFromToDate(SimpleDateFormat sdf, Date date, int n, int option, int k) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        int offset = 0 == option ? 1 - dayOfWeek : 7 - dayOfWeek;
        int amount = 0 == option ? offset - (n - 1 + k) * 7 : offset - k * 7;
        calendar.add(Calendar.DATE, amount);
        return sdf.format(calendar.getTime());
    }

    /**
     * 根据当前日期获得最近n周的日期区间（包含本周）
     *
     * @param n
     * @param sdf
     * @return
     */
    public static String getNWeekTimeInterval(int n, SimpleDateFormat sdf) {
        String beginDate = getFromToDate(sdf, new Date(), n, 0, 0);
        String endDate = getFromToDate(sdf, new Date(), n, 1, 0);
        return beginDate + "," + endDate;
    }

    /**
     * 根据当前日期获得本周日期区间（包含本周）
     *
     * @param
     * @return
     * @throws ParseException
     */
    public static Map<String,Date> getThisWeekTimeInterval2() throws ParseException {
        Map<String,Date> days = new HashMap<>();
        String beginDate = getFromToDate(sdf, new Date(), 1, 0, 0);
        String endDate = getFromToDate(sdf, new Date(), 1, 1, 0);
        days.put("beginDate",sdf.parse(beginDate));
        days.put("endDate",sdf.parse(endDate));
        return days;
    }

    /**
     * 根据当前日期获得最近n周的日期区间（不包含本周）
     *
     * @param n
     * @param sdf
     * @return
     */
    public static String getNWeekTimeIntervalTwo(int n, SimpleDateFormat sdf) {
        String beginDate = getFromToDate(sdf, new Date(), n, 0, 1);
        String endDate = getFromToDate(sdf, new Date(), n, 1, 1);
        return beginDate + "," + endDate;
    }

    /**
     * 根据当前日期获得本周的日期区间（本周周一和周日日期）
     *
     * @param sdf
     * @return
     */
    public static String getThisWeekTimeInterval(SimpleDateFormat sdf) {
        return getNWeekTimeInterval(1, sdf);
    }

    /**
     * 根据当前日期获得上周的日期区间（上周周一和周日日期）
     *
     * @param sdf
     * @return
     */
    public static String getLastWeekTimeInterval(SimpleDateFormat sdf) {
        return getNWeekTimeIntervalTwo(1, sdf);
    }

    public static void main(String[] args) {
        System.out.println(getThisWeekTimeInterval(new SimpleDateFormat("yyyy-MM-dd"))); // 获得本周的日期区间
        System.out.println(getLastWeekTimeInterval(new SimpleDateFormat("yyyy-MM-dd"))); // 获得上周的日期区间
    }


}
