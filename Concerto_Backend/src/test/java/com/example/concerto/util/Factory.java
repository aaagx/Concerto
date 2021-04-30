package com.example.concerto.util;

import com.example.concerto.fo.SubtaskForm;
import com.example.concerto.pojo.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

/**
 * @author sarise
 * @version 1.0
 * @date 2021/4/28 下午2:17
 */
public class Factory {
    public static User getUserInstance(Long userId,String userName){
        User user = new User();
        user.setUserId(userId);
        user.setUserName(userName);
        return user;
    }

    public static Date getDate(String dateStr){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd", Locale.CHINA);
        try {
            calendar.setTime(simpleDateFormat.parse(dateStr));
            return calendar.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static SubtaskForm getSubtask(String title, Set<User> users){
        SubtaskForm subtaskForm = new SubtaskForm();
        subtaskForm.setParticipants(users);
        subtaskForm.setTaskTitle(title);
        return subtaskForm;
    }
    public static void main(String[] args) {
        Date date = getDate("2020-10-10");
        System.out.println(date.toString());
    }

}
