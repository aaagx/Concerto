package com.example.concerto.controller;

import com.example.concerto.exception.CustomException;
import com.example.concerto.pojo.Project;
import com.example.concerto.pojo.Tag;
import com.example.concerto.pojo.Task;
import com.example.concerto.response.CommonResponse;
import com.example.concerto.service.ProjectManagementService;
import com.example.concerto.service.ProjectService;
import com.example.concerto.vo.PersonnelVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;
import java.util.Set;

/**
 * ProjectController
 *
 * @Author: LETOO
 * @Date: 2021/4/28 1:23
 * @Version: 1.0
 **/
@Slf4j
@RestController
@RequestMapping("/project")
public class ProjectController2 {

    @Autowired
    ProjectService projectService;

    @Autowired
    ProjectManagementService projectManagementService;

    @PostMapping()
    public CommonResponse addProject(@RequestBody Project project, HttpSession httpSession)
    {
        long inviteCode=projectService.insertProject(project,httpSession);
        return  new CommonResponse(200,"成功创建项目",inviteCode);
    }

    @PostMapping("/join/{projectId}")
    public CommonResponse addProject(@PathVariable long projectId, HttpSession httpSession)
    {
        projectService.joinProject(httpSession,projectId);
        return  new CommonResponse(200,"成功加入项目","");
    }


    /**
     * 修改项目信息
     * @param project
     * @return
     */
    @PutMapping()
    public CommonResponse modifyProject(@RequestBody Project project) {
        projectManagementService.updateProject(project);
        return new CommonResponse(200,"修改项目信息成功！","");
    }

    /**
     * 获取该项目全部成员信息（用户名、邮箱）
     * @param projectId
     * @return
     */
    @GetMapping("/member")
    public CommonResponse getProjectMember(Long projectId) {
        List<PersonnelVo> userList=  projectManagementService.getProjectAllMember(projectId);
        return new CommonResponse(200 , "获取该项目全部成员成功！" , userList);
    }

    /**
     * 获取该项目的申请人员信息（用户名、邮箱）
     * @param projectId
     * @return
     */
    @GetMapping("/applicant")
    public CommonResponse getProjectApplicant(Long projectId) {
        List<PersonnelVo> userList=  projectManagementService.getProjectApplicant(projectId);
        return new CommonResponse(200 , "获取该项目的申请人员成功！" , userList);
    }

    /**
     * 处理项目的申请人员
     * @param projectId
     * @param userId
     * @param operation
     * @return
     */
    @PostMapping("/applicant/auth")
    public CommonResponse applicationAuth(Long projectId, Long userId, String operation) {
        projectManagementService.updateApplicationAuth(projectId, userId, operation);
        return new CommonResponse(200 , "处理申请人员成功！" , "");
    }

    /**
     * 获取全部任务
     * @param projectId
     * @return
     */
    @GetMapping("/task/all")
    public CommonResponse getProjectAllTask(Long projectId) {
        List<Task> taskList = projectManagementService.getProjectAllTask(projectId);
        if(taskList == null || taskList.size() == 0){
            return new CommonResponse(200 , "该项目还没有任务！" , "");
        }
        return new CommonResponse(200 , "获取全部任务成功！" , taskList);
    }

    /**
     * 获取本周任务
     * @param projectId
     * @return
     * @throws ParseException
     */
    @GetMapping("/task/week")
    public CommonResponse getProjectWeekTask(Long projectId) throws ParseException {
        List<Task> taskList = projectManagementService.getProjectWeekTask(projectId);
        if(taskList == null || taskList.size() == 0){
            return new CommonResponse(200 , "该项目本周还没有任务！" , "");
        }
        return new CommonResponse(200 , "获取本周任务成功！" , taskList);
    }

    /**
     * 获取项目的所有tag
     * @param projectId
     * @return
     * @throws ParseException
     */
    @GetMapping("/tag")
    public CommonResponse getProjectAllTag(Long projectId) throws ParseException {
        Set<Tag> projectTagSet  = projectManagementService.getProjectAllTag(projectId);
        if(projectTagSet == null || projectTagSet.size() == 0){
            return new CommonResponse(200 , "该项目本周因为还没有任务，所以也没有tag！" , "");
        }
        return new CommonResponse(200 , "获取任务的所有tag成功！" , projectTagSet);
    }

}
