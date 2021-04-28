package com.example.concerto.utils;

import com.example.concerto.dao.UserDao;
import com.example.concerto.exception.CustomException;
import com.example.concerto.pojo.LoginForm;
import com.example.concerto.pojo.RegisterForm;
import org.springframework.stereotype.Component;

/**
 * @ Author     ：aaagx.
 * @ Date       ：Created in 23:11 2021/4/26
 * @Version: 1.0$
 */
public class FormUtils {
    public static boolean checkForm(RegisterForm registerForm) throws  CustomException
    {
        if(registerForm.getEmail().equals("")||registerForm.getEmail()==null
                || registerForm.getCaptcha().equals("") ||registerForm.getCaptcha()==null
                || registerForm.getName().equals("") ||registerForm.getName()==null)
        {
            return false;
        }

        return true;

    }
    public static boolean checkForm(LoginForm loginForm) throws  CustomException
    {
        if(loginForm.getPassword()==null|| loginForm.getPassword().equals("")
            ||loginForm.getEmail()==null ||loginForm.getEmail().equals("")
        )
        {
            return false;
        }
        return true;

    }
}
