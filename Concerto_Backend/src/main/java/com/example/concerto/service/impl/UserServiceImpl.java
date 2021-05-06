package com.example.concerto.service.impl;

import com.example.concerto.dao.UserDao;
import com.example.concerto.dao.UserTokenDao;
import com.example.concerto.exception.CustomException;
import com.example.concerto.pojo.*;
import com.example.concerto.service.UserService;
import com.example.concerto.utils.CaptchaUtils;
import com.example.concerto.utils.FormUtils;
import com.example.concerto.utils.TokenUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                //检查表单有效性
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
                //插入用户信息
                User user = new User();
                user.setUserName(registerForm.getName());
                user.setUserPassword(registerForm.getPassword());
                user.setUserEmail(registerForm.getEmail());
                user.setUserSalt("001");
                long insertId = userDao.insertUser(user);

            }
            else {
                throw new CustomException(403, "注册信息有误");
            }
    }

    @Override
    public  String login(LoginForm loginForm, HttpSession httpSession)
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

        //从数据库中取出所有该用户id对应的token
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
        httpSession.setAttribute("UserId",user.getUserId());
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
    public Userinfo getUserInfo(HttpSession httpSession) {
        try {
            long UserId = (long) httpSession.getAttribute("UserId");
            User user=userDao.getUserById(UserId);
            if(user==null)
                throw new CustomException(404,"找不到对应用户");

            Userinfo userinfo=new Userinfo();
            BeanUtils.copyProperties(user,userinfo);
            userinfo.setUserPhone(user.getUserPhone()!=null?user.getUserPhone():"");
            userinfo.setUserIntroducton(user.getUserIntroducton()!=null?user.getUserIntroducton():"");
            return userinfo;
        }
        catch (NullPointerException e)
        {
            throw new CustomException(401,"未进行过登陆操作");
        }
    }

    @Override
    @Transactional
    public void updateUserInfo(Userinfo userinfo, HttpSession httpSession){
        try {
            long UserId = (long) httpSession.getAttribute("UserId");
            User user =new User();
            BeanUtils.copyProperties(userinfo,user);
            user.setUserId(UserId);
            if(userDao.getUserNumByEmail(userinfo.getUserEmail())!=0 )
            {
                throw new CustomException(403, "邮箱已经被注册");
            }
            else  if(userDao.getUserNumByName(userinfo.getUserName())!=0)
            {
                throw new CustomException(403, "用户名已经被注册");
            }
            userDao.UpdateUser(user);
        }
        catch (NullPointerException e)
        {
            throw new CustomException(401,"未进行过登陆操作");
        }


    }
    @Override
    public void sendCaptcha(String email, HttpSession session) {
        if(email==null||email.equals("")) {
            throw new CustomException(403,"email不能为空");
        }
        //发送邮件
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
