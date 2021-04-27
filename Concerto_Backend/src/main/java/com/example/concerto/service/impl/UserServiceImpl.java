package com.example.concerto.service.impl;

import com.example.concerto.dao.UserDao;
import com.example.concerto.dao.UserTokenDao;
import com.example.concerto.exception.CustomException;
import com.example.concerto.pojo.LoginForm;
import com.example.concerto.pojo.RegisterForm;
import com.example.concerto.pojo.User;
import com.example.concerto.pojo.UserToken;
import com.example.concerto.service.UserService;
import com.example.concerto.utils.CaptchaUtils;
import com.example.concerto.utils.FormUtils;
import com.example.concerto.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ Author     ：aaagx.
 * @ Date       ：Created in 16:06 2021/4/25
 * @Version: $
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    UserTokenDao userTokenDao;
    @Autowired
    JavaMailSender mailSender;

    @Override
    public void Register(RegisterForm registerForm, HttpSession httpSession) {

            if (FormUtils.checkForm(registerForm)) {
                String Captcha= (String)httpSession.getAttribute("Captcha");
                if(Captcha==null||!Captcha.equals(registerForm.getCaptcha())) {
                    throw new CustomException(403, "验证码不正确");
                }
                else  if(userDao.getUserNumByEmail(registerForm.getEmail())!=0 )
                {
                    throw new CustomException(403, "邮箱已经被注册");
                }
                else  if(userDao.getUserNumByName(registerForm.getName())!=0)
                {
                    throw new CustomException(403, "用户名已经被注册");
                }
                else  if(userDao.getUserNumByPhone(registerForm.getPhone())!=0)
                {
                    throw new CustomException(403, "手机号已经被注册");
                }
                User user = new User();
                user.setUserName(registerForm.getName());
                user.setUserPassword(registerForm.getPassword());
                user.setUserEmail(registerForm.getEmail());
                user.setUserPhone(registerForm.getPhone());
                user.setUserSalt("001");
                int insertId = userDao.insertUser(user);

            }
            else {
                throw new CustomException(403, "注册信息有误");
            }
    }

    @Override
    public  String login(LoginForm loginForm)
    {
        if(!FormUtils.checkForm(loginForm))
        {
            throw new CustomException(403, "登录信息不完整");
        }
        String email=loginForm.getEmail();
        String password=loginForm.getPassword();
        User user=userDao.getUserByEmail(email);
        if(user==null||!user.getUserPassword().equals(password))
        {
            throw new CustomException(403, "登录失败");
        }
        List<String> tokenlist=userTokenDao.getTokenByUserId(user.getUserId());
        String token;
        if(tokenlist.isEmpty())
        {
            token = TokenUtils.CreateToken(user);
            UserToken userToken = new UserToken();
            userToken.setUserToken(token);
            userToken.setUserId(user.getUserId());
            userTokenDao.insertUserToken(userToken);
        }
        else
        {
            token=tokenlist.get(0);
        }
        return token;
    }


    @Override
    public User getUserById(int id) {
        User user=userDao.getUserById(id);
        if(user==null)
            throw new CustomException(404,"找不到对应用户");
        return user;
    }


    @Override
    public void sentCaptcha(String email, HttpSession session) {
        if(email==null||email.equals("")) {
            throw new CustomException(403,"email不能为空");
        }
        String emailServiceCode = CaptchaUtils.randomCaptcha();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("注册验证码");
        message.setText("注册验证码是：" + emailServiceCode);
        message.setFrom("675452601@qq.com");
        message.setTo(email);
        mailSender.send(message);
        session.setAttribute("Captcha",emailServiceCode);
    }
}
