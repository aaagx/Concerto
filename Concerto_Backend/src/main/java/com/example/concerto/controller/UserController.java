package com.example.concerto.controller;

import com.example.concerto.annotation.PassToken;
import com.example.concerto.annotation.UserLoginToken;
import com.example.concerto.exception.CustomException;
import com.example.concerto.pojo.*;
import com.example.concerto.response.CommonResponse;
import com.example.concerto.service.UserService;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * @ Author     ：aaagx.
 * @ Date       ：Created in 16:28 2021/4/25
 * @Version: 1.0$
 */
@RestController()
public class UserController {
    @Autowired
    UserService userService;

    @UserLoginToken
    @GetMapping("/User/{id}")
    public CommonResponse getUserById(@PathVariable("id") int id) {
        User user = userService.getUserById(id);
        CommonResponse commonResponse = new CommonResponse(200, "ok", user);
        return commonResponse;
    }

    @UserLoginToken
    @GetMapping("/User/info")
    public CommonResponse getUserInfo(HttpSession httpSession) {
        Userinfo userinfo = userService.getUserInfo(httpSession);
        CommonResponse commonResponse = new CommonResponse(200, "ok", userinfo);
        return commonResponse;
    }

    @UserLoginToken
    @PostMapping("/User/info")
    public CommonResponse UpdateUserInfo(@RequestBody Userinfo userinfo, HttpSession httpSession) {
        userService.updateUserInfo(userinfo, httpSession);
        CommonResponse commonResponse = new CommonResponse(200, "用户信息修改成功", "");
        return commonResponse;
    }

    @PassToken
    @PostMapping("/User/Register")
    public CommonResponse Register(@RequestBody RegisterForm registerForm, HttpSession httpSession) {
        userService.Register(registerForm, httpSession);

        CommonResponse commonResponse = new CommonResponse(200, "注册成功", "");

        return commonResponse;
    }

    @PassToken
    @PostMapping("/User/Login")
    public CommonResponse Login(@RequestBody LoginForm loginForm, HttpSession httpSession) {
        String token = userService.login(loginForm, httpSession);
        CommonResponse commonResponse = new CommonResponse(200, "登陆成功", token);
        return commonResponse;
    }

    @PassToken
    @GetMapping("/User/Captcha/{email}")
    public CommonResponse getCaptcha(@PathVariable("email") String email, HttpSession httpSession)
    {
        userService.sendCaptcha(email, httpSession);
        CommonResponse commonResponse = new CommonResponse(200, "验证码已经成功发出", "");
        return commonResponse;
    }

    @UserLoginToken
    @GetMapping("/User/Message")
    public CommonResponse getMessage(HttpSession httpSession) {
        List<Message> messageList = userService.getMessage(httpSession);
        return new CommonResponse(200, "ok", messageList);
    }


    @UserLoginToken
    @PostMapping("/User/Message")
    public CommonResponse setMessage(HttpSession httpSession) {
        userService.setMessage(httpSession);
        return new CommonResponse(200, "成功将消息设置为已读", "");
    }

    @UserLoginToken
    @GetMapping("/User/Schedule/all")
    public CommonResponse getAllSchedule(HttpSession httpSession) {
        List<Task> taskList = userService.getAllSchedule(httpSession);
        return new CommonResponse(200, "ok", taskList);
    }

    @UserLoginToken
    @GetMapping("/User/Schedule/Month")
    public CommonResponse getMonthSchedule(HttpSession httpSession) {
        List<Task> taskList = userService.getmonthSchedule(httpSession);
        return new CommonResponse(200, "ok", taskList);
    }

    @UserLoginToken
    @GetMapping("/User/Schedule/Week")
    public CommonResponse getWeekSchedule(HttpSession httpSession) {
        List<Task> taskList = userService.getWeekSchedule(httpSession);
        return new CommonResponse(200, "ok", taskList);
    }

    @UserLoginToken
    @GetMapping("/User/Schedule/Recommend")
    public CommonResponse getRecommendSchedule(HttpSession httpSession) {
        List<Task> taskList = userService.getRecommendSchedule(httpSession);
        return new CommonResponse(200, "ok", taskList);
    }
    @UserLoginToken
    @GetMapping("/User/Schedule/Day")
    public CommonResponse getDaySchedule(HttpSession httpSession) {
        List<Task> taskList = userService.getDaySchedule(httpSession);
        return new CommonResponse(200, "ok", taskList);
    }
    @UserLoginToken
    @GetMapping("/User/Schedule/Tag")
    public CommonResponse getScheduleTag(HttpSession httpSession) {
        long userId= (long) httpSession.getAttribute("UserId");
        Set<Tag> tagSet=userService.getTags(userId);
        return new CommonResponse(200, "ok", tagSet);
    }
    @UserLoginToken
    @PutMapping("/User/Advice")
    public CommonResponse getDaySchedule(@RequestParam String content,HttpSession httpSession) {
        long userId= (long) httpSession.getAttribute("UserId");
        userService.insertAdvice(userId,content);
        return new CommonResponse(200, "ok", "");
    }
}
