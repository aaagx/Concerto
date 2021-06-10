package com.example.concerto.controller;

import com.example.concerto.annotation.PassToken;
import com.example.concerto.annotation.UserLoginToken;
import com.example.concerto.dao.ProjectDao;
import com.example.concerto.pojo.Project;
import com.example.concerto.pojo.TaskVersionInfo;
import com.example.concerto.response.CommonResponse;
import com.example.concerto.service.ProjectService;
import com.example.concerto.service.TaskVersionService;
import com.example.concerto.service.UserService;
import com.example.concerto.vo.ProjectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ Author     ：aaagx.
 * @ Date       ：Created in 22:49 2021/4/28
 * @Version: 1.0$
 */
@RestController()
public class ProjectController {
    @Autowired
    ProjectService projectService;
    @UserLoginToken
    @PostMapping("/Project")
    public CommonResponse addProject(@RequestBody Project project, HttpSession httpSession)
    {
        long inviteCode=projectService.insertProject(project,httpSession);
        return  new CommonResponse(200,"成功创建项目",inviteCode);
    }

    
    @UserLoginToken
    @GetMapping("/Project")
    public CommonResponse getProject( HttpSession httpSession)
    {
        List<ProjectVo> projectList=projectService.getAllProject(httpSession);
        return  new CommonResponse(200,"ok",projectList);
    }

    @UserLoginToken
    @PostMapping("/Project/Join/{ProjectId}")
    public CommonResponse joinProject(@PathVariable long ProjectId, HttpSession httpSession)
    {
        projectService.joinProject(httpSession,ProjectId);
        return  new CommonResponse(200,"成功加入项目","");
    }



}
