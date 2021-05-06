package com.example.concerto.service.impl;

import com.example.concerto.dao.MessageDao;
import com.example.concerto.dao.ProjectDao;
import com.example.concerto.dao.ProjectManagementDao;
import com.example.concerto.dao.UserProjectDao;
import com.example.concerto.exception.CustomException;
import com.example.concerto.pojo.Message;
import com.example.concerto.pojo.Project;
import com.example.concerto.pojo.UserProject;
import com.example.concerto.service.ProjectService;
import com.example.concerto.utils.FormUtils;
import org.apache.ibatis.binding.BindingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ Author     ：aaagx.
 * @ Date       ：Created in 22:32 2021/4/28
 * @Version: 1.0$
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectDao projectDao;
    @Autowired
    UserProjectDao userProjectDao;
    @Autowired
    MessageDao messageDao;
    @Autowired
    ProjectManagementDao projectManagementDao;

    @Override
    public long insertProject(Project project, HttpSession httpSession) {
            if(FormUtils.checkForm(project)) {
                long userId= (long) httpSession.getAttribute("UserId");
                projectDao.insertProject(project);
                long projectId =project.getProjectId();
                UserProject userProject=new UserProject();
                userProject.setProject_id(projectId);
                userProject.setUser_id(userId);
                userProject.setUser_role(2);
                userProjectDao.addUserProject(userProject);
                return  projectId;
            }
            else
            {
                throw new CustomException(403,"用户输入信息不完整");
            }
    }
    @Override
    public void joinProject(HttpSession httpSession,long projectId)
    {
        try {
                long userId= (long) httpSession.getAttribute("UserId");
                if(userProjectDao.checkUserProject(userId,projectId)>0)
                {
                    throw new CustomException(401,"请勿重复加入项目");
                }


                UserProject userProject=new UserProject();
                userProject.setProject_id(projectId);
                userProject.setUser_id(userId);
                userProject.setUser_role(2);
                userProjectDao.addUserProject(userProject);

                //新建一个发给项目管理者的消息项目并且存入数据库
                Message message =new Message();
                message.setMessageStatus(0);
                message.setMessageContent("有新用户加入项目");
                long managerId = userProjectDao.getProjectManager(projectId);//对项目管理者发消息
                message.setUserId(managerId);
                messageDao.insertMessage(message);
        }
        catch (BindingException e)
        {
            throw  new CustomException(400,"尝试加入一个无管理者的项目");
        }
    }

    @Override
    public List<Project> getAllProject(HttpSession httpSession) {
            long userId= (long) httpSession.getAttribute("UserId");
            System.out.println(userId);
            List<Project> projectList=userProjectDao.getProjectsByUser(userId);
            return  projectList;

    }
}
