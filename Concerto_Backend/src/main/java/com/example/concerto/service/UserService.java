package com.example.concerto.service;

import com.example.concerto.pojo.LoginForm;
import com.example.concerto.pojo.RegisterForm;
import com.example.concerto.pojo.User;
import com.example.concerto.pojo.Userinfo;

import javax.servlet.http.HttpSession;

/**
 * @ Author     ：aaagx.
 * @ Date       ：Created in 16:05 2021/4/25
 * @Version: 1.0$
 */

public interface UserService {

     void Register(RegisterForm user, HttpSession httpSession);

     String login(LoginForm loginForm, HttpSession httpSession);

     User getUserById(int id);

     Userinfo getUserInfo(HttpSession httpSession);

     void sendCaptcha(String email, HttpSession session);
}
