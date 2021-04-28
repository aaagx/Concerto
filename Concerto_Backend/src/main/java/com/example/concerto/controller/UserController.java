package com.example.concerto.controller;

import com.example.concerto.annotation.PassToken;
import com.example.concerto.annotation.UserLoginToken;
import com.example.concerto.exception.CustomException;
import com.example.concerto.pojo.*;
import com.example.concerto.response.CommonResponse;
import com.example.concerto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

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
    public CommonResponse getUserById(@PathVariable("id") int id)
    {
        User user=userService.getUserById(id);
        CommonResponse commonResponse=new CommonResponse(200,"ok",user);
        return  commonResponse;
    }
    @UserLoginToken
    @GetMapping("/User/info")
    public CommonResponse getUserInfo(HttpSession httpSession)
    {
        Userinfo userinfo =userService.getUserInfo(httpSession);
        CommonResponse commonResponse=new CommonResponse(200,"ok",userinfo);
        return  commonResponse;
    }

    @PassToken
    @PostMapping("/User/Register")
    public CommonResponse Register(@RequestBody RegisterForm registerForm,HttpSession httpSession)
    {
        userService.Register(registerForm,httpSession);

        CommonResponse commonResponse=new CommonResponse(200,"注册成功","");

        return  commonResponse;
    }

    @PassToken
    @PostMapping("/User/Login")
    public CommonResponse Login(@RequestBody LoginForm loginForm,HttpSession httpSession)
    {
        String token=userService.login(loginForm,httpSession);
        CommonResponse commonResponse=new CommonResponse(200,"登陆成功",token);
        return  commonResponse;
    }

    @PassToken
    @GetMapping("/User/Captcha/{email}")
    public CommonResponse getCaptcha(@PathVariable("email") String email, HttpSession httpSession)
    {
        userService.sendCaptcha(email,httpSession);
        CommonResponse commonResponse=new CommonResponse(200,"验证码已经成功发出","");
        return  commonResponse;
    }



}
