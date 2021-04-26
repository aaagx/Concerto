package com.example.concerto.controller;

import com.example.concerto.annotation.PassToken;
import com.example.concerto.annotation.UserLoginToken;
import com.example.concerto.exception.CustomException;
import com.example.concerto.pojo.Project;
import com.example.concerto.pojo.RegisterForm;
import com.example.concerto.pojo.User;
import com.example.concerto.response.CommonResponse;
import com.example.concerto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PassToken
    @PostMapping("/User")
    public CommonResponse Register(@RequestBody RegisterForm registerForm)
    {
        userService.Register(registerForm);

        CommonResponse commonResponse=new CommonResponse(200,"ok",null);
        return  commonResponse;
    }

}
