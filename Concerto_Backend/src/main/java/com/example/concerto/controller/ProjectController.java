package com.example.concerto.controller;

import com.example.concerto.dao.ProjectDao;
import com.example.concerto.pojo.Project;
import com.example.concerto.response.CommonResponse;
import com.example.concerto.service.ProjectService;
import com.example.concerto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @ Author     ：aaagx.
 * @ Date       ：Created in 22:49 2021/4/28
 * @Version: 1.0$
 */
@RestController()
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @PostMapping("/Project")
    public CommonResponse addProject(@RequestBody Project project, HttpSession httpSession)
    {
        long inviteCode=projectService.insertProject(project,httpSession);
        return  new CommonResponse(200,"成功创建项目",inviteCode);
    }

    @PostMapping("/Project/Join/{ProjectId}")
    public CommonResponse addProject(@PathVariable long ProjectId, HttpSession httpSession)
    {
        projectService.joinProject(httpSession,ProjectId);
        return  new CommonResponse(200,"成功加入项目","");
    }
}
