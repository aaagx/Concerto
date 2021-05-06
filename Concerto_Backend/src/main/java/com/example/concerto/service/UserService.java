package com.example.concerto.service;

import com.example.concerto.pojo.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ Author     ：aaagx.
 * @ Date       ：Created in 16:05 2021/4/25
 * @Version: 1.0$
 */

public interface UserService {

     void Register(RegisterForm user, HttpSession httpSession);

     String login(LoginForm loginForm, HttpSession httpSession);

     User getUserById(long id);

     Userinfo getUserInfo(HttpSession httpSession);

    void updateUserInfo(Userinfo userinfo, HttpSession httpSession);

    void sendCaptcha(String email, HttpSession session);

    List<Message> getMessage(HttpSession session);

    void setMessage(HttpSession session);

    List<Task> getAllSchedule(HttpSession session)
    ;
    List<Task> getweekSchedule(HttpSession session)
    ;
    List<Task> getmonthSchedule(HttpSession session)
    ;

    List<Task> getWeekSchedule(HttpSession session);


    List<Task> getRecommendSchedule(HttpSession session);

    List<Task> getDaySchedule(HttpSession session);
}
