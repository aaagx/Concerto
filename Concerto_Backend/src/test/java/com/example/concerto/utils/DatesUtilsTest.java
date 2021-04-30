package com.example.concerto.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * DatesUtilsTest
 *
 * @Author: LETOO
 * @Date: 2021/4/29 22:01
 * @Version: 1.0
 **/
@Slf4j
class DatesUtilsTest {

    @Test
    void getTermDays1() {
    }

    @Test
    void getTermDays2() {
    }

    @Test
    void getFromToDate() {
    }

    @Test
    void getNWeekTimeInterval() {
    }

    @Test
    void getThisWeekTimeInterval2() throws ParseException {
        //判断是否在本周内
        //根据当前日期本周的日期区间
        Map<String, Date> days = DatesUtils.getThisWeekTimeInterval2();
        log.info("【beginDate】：" + days.get("beginDate") + " // " + "【endDate】：" + days.get("endDate") );
        int end_start = DatesUtils.getTermDays2(new Date(2021-1900,5-1,23), days.get("beginDate"));
        int start_end = DatesUtils.getTermDays2(days.get("endDate"),new Date(2021-1900,5-1,3));
        if(end_start > 0 || start_end > 0){
            log.info("不在本周内");
        } else {
            log.info("在本周内");
        }

    }

    @Test
    void getNWeekTimeIntervalTwo() {
    }

    @Test
    void getThisWeekTimeInterval() {
    }

    @Test
    void getLastWeekTimeInterval() {
    }

    @Test
    void main() {
    }
}