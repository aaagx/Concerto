package com.example.concerto.service.impl;

import com.example.concerto.dao.ProjectDao;
import com.example.concerto.dao.UserProjectDao;
import com.example.concerto.exception.CustomException;
import com.example.concerto.pojo.Project;
import com.example.concerto.service.ProjectService;
import com.example.concerto.utils.FormUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Override
    public List<Project> getAllProject() {
        return null;
    }

    @Override
    @Transactional
    public long insertProject(Project project, HttpSession httpSession) {
        try {
            if(FormUtils.checkForm(project)) {
                long userId= (long) httpSession.getAttribute("UserId");
                projectDao.insertProject(project);
                long projectId =project.getProjectId();
                userProjectDao.addUserProject(projectId,userId,2);
                return  projectId;
            }
            else
            {
                throw new CustomException(403,"用户输入信息不完整");
            }
        }
        catch (NullPointerException e)
        {
            throw  new CustomException(401,"用户未登录");
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
                userProjectDao.addUserProject(userId,projectId,0);
        }
        catch (NullPointerException e)
        {
            throw  new CustomException(401,"用户未登录");
        }
    }


}
