package com.example.concerto.service.impl;

import com.example.concerto.dao.UserDao;
import com.example.concerto.dao.UserTokenDao;
import com.example.concerto.exception.CustomException;
import com.example.concerto.pojo.LoginForm;
import com.example.concerto.pojo.RegisterForm;
import com.example.concerto.pojo.User;
import com.example.concerto.pojo.UserToken;
import com.example.concerto.service.UserService;
import com.example.concerto.utils.FormUtils;
import com.example.concerto.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Override
    public void Register(RegisterForm registerForm) {

            if (FormUtils.checkForm(registerForm)) {
                if(userDao.getUserNumByEmail(registerForm.getEmail())!=0 )
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
}
