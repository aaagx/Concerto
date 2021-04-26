package com.example.concerto.service.impl;

import com.example.concerto.dao.UserDao;
import com.example.concerto.exception.CustomException;
import com.example.concerto.pojo.RegisterForm;
import com.example.concerto.pojo.User;
import com.example.concerto.service.UserService;
import com.example.concerto.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ Author     ：aaagx.
 * @ Date       ：Created in 16:06 2021/4/25
 * @Version: $
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Override
    public void Register(RegisterForm registerForm) {
        if(userDao.getUserNumByEmail(registerForm.getEmail())==0 && userDao.getUserNumByName(registerForm.getName())==0) {
            User user=new User();
            user.setUserName(registerForm.getName());
            user.setUserPassword(registerForm.getPassword());
            user.setUserEmail(registerForm.getEmail());
            user.setUserPhone("1");
            String token = TokenUtils.CreateToken(user);
            System.out.println(token);
            System.out.println(TokenUtils.getIdInToken(token));
            user.setUserSalt("001");
            user.setUserToken(token);
            int uid=userDao.insertUser(user);
        }
        else
        {
            throw new CustomException(403,"用户名/邮箱已经被注册");
        }
    }

    @Override
    public User getUserById(int id) {
        User user=userDao.getUserById(id);
        if(user==null)
            throw new CustomException(404,"找不到对应用户");
        return user;
    }
}
