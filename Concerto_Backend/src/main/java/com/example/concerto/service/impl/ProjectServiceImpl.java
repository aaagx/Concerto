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
import com.example.concerto.vo.ProjectVo;
import org.apache.ibatis.binding.BindingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
    @Transactional
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

    @Transactional
    @Override
    public void joinProject(HttpSession httpSession,long projectId)
    {
        try {
                long userId= (long) httpSession.getAttribute("UserId");
                if(userProjectDao.checkUserProjectIn(userId,projectId)>0)
                {
                    throw new CustomException(401,"请勿重复加入项目");
                }
                else if(userProjectDao.checkUserProjectRe(userId,projectId)>0)
                {
                    throw new CustomException(401,"审核中");
                }


                UserProject userProject=new UserProject();
                userProject.setProject_id(projectId);
                userProject.setUser_id(userId);
                userProject.setUser_role(0);
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
            e.printStackTrace();
            throw  new CustomException(400,"尝试加入一个无管理者的项目或者项目不存在");
        }
    }

    @Override
    public List<ProjectVo> getAllProject(HttpSession httpSession) {
            long userId= (long) httpSession.getAttribute("UserId");
            System.out.println(userId);
            List<Project> projectList=userProjectDao.getProjectsByUser(userId);
            List<ProjectVo> resultList = new ArrayList<>();
            for(Project project:projectList)
            {
                ProjectVo projectVo=new ProjectVo();
                projectVo.setProjectDescription(project.getProjectDescription()==null?"":project.getProjectDescription());
                projectVo.setProjectName(project.getProjectName());
                projectVo.setProjectEndTime(project.getProjectEndTime());
                projectVo.setProjectStartTime(project.getProjectStartTime());
                projectVo.setProjectId(project.getProjectId());

                projectVo.setAdmin(userProjectDao.getAdminByProject(project.getProjectId()));
                resultList.add(projectVo );
            }
            return  resultList;

    }
}
