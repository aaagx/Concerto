package com.example.concerto.service.impl;

import com.example.concerto.dao.*;
import com.example.concerto.exception.CustomException;
import com.example.concerto.pojo.*;
import com.example.concerto.service.UserService;
import com.example.concerto.utils.*;
import freemarker.template.TemplateException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    MessageDao messageDao;

    @Autowired
    TaskDao taskDao;

    @Autowired
    UserAdviceDao userAdviceDao;

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
    public User getUserById(long id) {
        User user=userDao.getUserById(id);
        if(user==null)
            throw new CustomException(404,"找不到对应用户");
        return user;
    }

    @Override
    public Userinfo getUserInfo(HttpSession httpSession) {
            long UserId = (long) httpSession.getAttribute("UserId");
            User user=userDao.getUserById(UserId);
            if(user==null)
                throw new CustomException(404,"找不到对应用户");

            Userinfo userinfo=new Userinfo();
            userinfo.setUserId(UserId);
            BeanUtils.copyProperties(user,userinfo);
            userinfo.setUserPhone(user.getUserPhone()!=null?user.getUserPhone():"");
            userinfo.setUserIntroducton(user.getUserIntroducton()!=null?user.getUserIntroducton():"");
            return userinfo;

    }

    @Override
    @Transactional
    public void updateUserInfo(Userinfo userinfo, HttpSession httpSession){
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

    @Autowired
    MailUtils mailUtils;
    @Override
    public void sendCaptcha(String email, HttpSession session) {
        if(email==null||email.equals("")) {
            throw new CustomException(403,"email不能为空");
        }
        //发送邮件
        String emailServiceCode = CaptchaUtils.randomCaptcha();
        session.setAttribute("Captcha",emailServiceCode);
        mailUtils.sendTemplateMail(email,emailServiceCode);


    }

    @Override
    public List<Message> getMessage(HttpSession session) {
            long UserId = (long) session.getAttribute("UserId");
            List<Message> messageList=messageDao.getMesssageByUserId(UserId);
            return  messageList;

    }

    @Override
    public void setMessage(HttpSession session){
            long UserId = (long) session.getAttribute("UserId");
            messageDao.setMessage(UserId);
    }
    @Override
    public List<Task> getAllSchedule(HttpSession session)
    {
            Date today=new Date();
            long UserId = (long) session.getAttribute("UserId");
            List<Task> taskList=taskDao.getTasksByUserId(UserId);
            Collections.sort(taskList);
            for(Task task:taskList)
            {
                task.setTaskDays(DatesUtils.getTermDays2(today,task.getTaskEndTime()));
                if(task.getSubTasks()!=null) {
                    task.setSubTaskNum(task.getSubTasks().size());
                }
            }
            return  taskList;

    }


    @Override
    public List<Task> getmonthSchedule(HttpSession session) {
            List<Task> taskList=this.getAllSchedule(session);
            //获取本月的第一个和最后一天
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.set(Calendar.DAY_OF_MONTH, 1);
            Date first=cal.getTime();
            cal.roll(Calendar.DAY_OF_MONTH, -1);
            Date last=cal.getTime();
            List<Task> resultList=new ArrayList<>();

            //筛选出本月的任务
            for(Task task:taskList)
            {
                if(task.getTaskStartTime().compareTo(last)<=0&&task.getTaskStartTime().compareTo(first)>=0)
                {
                    resultList.add(task);
                    continue;
                }
                else if(task.getTaskEndTime().compareTo(last)<=0&&task.getTaskEndTime().compareTo(first)>=0)
                {
                    resultList.add(task);
                    continue;
                }
                else if(task.getTaskStartTime().compareTo(first)<0&&task.getTaskEndTime().compareTo(last)>0)
                {
                    resultList.add(task);
                    continue;
                }
            }

            //返回结果集
            Collections.sort(resultList);
            return  resultList;

    }

    @Override
    public List<Task> getWeekSchedule(HttpSession session) {
            List<Task> taskList=this.getAllSchedule(session);
            //获取本周的第一个和最后一天
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.set(Calendar.DAY_OF_WEEK, 1);
            Date first=cal.getTime();
            cal.roll(Calendar.DAY_OF_WEEK, -1);
            Date last=cal.getTime();
            List<Task> resultList=new ArrayList<>();

            //筛选出本周的任务
            for(Task task:taskList)
            {
                if(task.getTaskStartTime().compareTo(last)<=0&&task.getTaskStartTime().compareTo(first)>=0)
                {
                    resultList.add(task);
                    continue;
                }
                else if(task.getTaskEndTime().compareTo(last)<=0&&task.getTaskEndTime().compareTo(first)>=0)
                {
                    resultList.add(task);
                    continue;
                }
                else if(task.getTaskStartTime().compareTo(first)<0&&task.getTaskEndTime().compareTo(last)>0)
                {
                    resultList.add(task);
                    continue;
                }
            }

            //返回结果集
            Collections.sort(resultList);
            return  resultList;
    }

    @Override
    public List<Task> getRecommendSchedule(HttpSession session) {
            List<Task> taskList=this.getAllSchedule(session);
            Collections.sort(taskList);
            return  taskList;
    }

    @Override
    public List<Task> getDaySchedule(HttpSession session) {
        List<Task> taskList=this.getAllSchedule(session);
        Date today=new Date();
        Collections.sort(taskList);

        List<Task> resultList=new ArrayList<>();

        //筛选出本日的任务
        for(Task task:taskList)
        {
            if(task.getTaskStartTime().compareTo(today)<=0&&task.getTaskEndTime().compareTo(today)>=0)
            {
                resultList.add(task);
                continue;
            }
        }
        Collections.sort(resultList);
        return  resultList;
    }
    @Override
    public void insertAdvice(long userId, String content)
    {
        UserAdvice userAdvice=new UserAdvice();
        userAdvice.setUserId(userId);
        userAdvice.setAdviceContent(content);
        userAdviceDao.insertAdvice(userAdvice);
    }

    @Override
    public Set<Tag> getTags(long userId)
    {
        Set<Tag> tagSet=new HashSet<>();
        List<Task> taskList=taskDao.getTasksByUserId(userId);
        for(Task task:taskList)
        {
            Set<Tag> tempTagSet=task.getTags();
            tagSet.addAll(tempTagSet);
        }
        return tagSet;
    }
}
