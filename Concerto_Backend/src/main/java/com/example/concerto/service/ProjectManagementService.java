package com.example.concerto.service;

import com.example.concerto.pojo.Project;
import com.example.concerto.pojo.Task;
import com.example.concerto.vo.PersonnelVo;

import java.text.ParseException;
import java.util.List;

/**
 * ProjectManagementService
 *
 * @Author: LETOO
 * @Date: 2021/4/28 9:59
 * @Version: 1.0
 **/
public interface ProjectManagementService {
    /**
     * 更新项目信息
     * @param project
     * @return
     */
    boolean updateProject(Project project);

    /**
     * 获取项目的全部人员（管理者、普通成员）
     * @param projectId
     * @return
     */
    List<PersonnelVo> getProjectAllMember(Long projectId);

    /**
     * 获取项目的申请人员
     * @param projectId
     * @return
     */
    List<PersonnelVo> getProjectApplicant(Long projectId);

    /**
     * 处理申请人员
     * @param projectId
     * @param userId
     * @param operation
     * @return
     */
    boolean updateApplicationAuth(Long projectId, Long userId, String operation);

    /**
     * 获取项目全部任务
     * @param projectId
     * @return
     */
    List<Task> getProjectAllTask(Long projectId);

    /**
     * 获取项目本周任务
     * @param projectId
     * @return
     */
    List<Task> getProjectWeekTask(Long projectId) throws ParseException;
}
