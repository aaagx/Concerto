package com.example.concerto.service.impl;

import com.example.concerto.dao.ProjectManagementDao;
import com.example.concerto.dao.TaskDao;
import com.example.concerto.dao.TaskInfoDao;
import com.example.concerto.exception.CustomException;
import com.example.concerto.fo.SubtaskForm;
import com.example.concerto.pojo.Project;
import com.example.concerto.pojo.Tag;
import com.example.concerto.pojo.Task;
import com.example.concerto.service.ProjectManagementService;
import com.example.concerto.utils.DatesUtils;
import com.example.concerto.vo.PersonnelVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

/**
 * ProjectManagementServiceImpl
 *
 * @Author: LETOO
 * @Date: 2021/4/28 10:04
 * @Version: 2.0
 **/
@Slf4j
@Service
public class ProjectManagementServiceImpl implements ProjectManagementService {

    @Autowired
    ProjectManagementDao projectDao;

    @Autowired
    TaskDao taskDao;

    @Autowired
    TaskInfoDao taskInfoDao;


    @Override
    public boolean updateProject(Project project) {
        if (project.getProjectId() == null) {
            throw new CustomException(400, "无效处理（项目id缺失）");
        }

        int result = projectDao.updateProject(project);
        if (result <= 0) {
            throw new CustomException(500, "更新项目信息失败！");
        }
        return true;
    }

    @Override
    public List<PersonnelVo> getProjectAllMember(Long projectId) {
        if (projectId == null) {
            throw new CustomException(400, "无效处理（项目id缺失）");
        }

        List<PersonnelVo> userList = projectDao.getProjectAllMember(projectId);
        if (userList.isEmpty() || userList.equals(null)) {
            throw new CustomException(500, "获取该项目全部成员失败！");
        }
        return userList;
    }

    @Override
    public List<PersonnelVo> getProjectApplicant(Long projectId) {
        if (projectId == null) {
            throw new CustomException(400, "无效处理（项目id缺失）");
        }

        List<PersonnelVo> userList = projectDao.getProjectApplicant(projectId);
        if (userList.isEmpty() || userList.equals(null)) {
            throw new CustomException(500, "获取该项目的申请人员失败！");
        }
        return userList;
    }

    //发消息：TODO
    @Override
    public boolean updateApplicationAuth(Long projectId, Long userId, String operation) {
        int result = 0;
        if (projectId == null || userId == null) {

            throw new CustomException(400, "无效处理（项目/用户id缺失）");
        }

        if (operation.equals("true")) {
            result = projectDao.updateUserRole(projectId, userId, 1
            );
            //发消息：TODO
        } else if (operation.equals("false")) {
            //删除记录
            result = projectDao.deleteUserProjectRecord(projectId, userId);
            //发消息：TODO
        } else {
            throw new CustomException(400, "选择出错！");
        }

        if (result <= 0) {
            throw new CustomException(500, "处理申请人员失败！");
        }

        return true;
    }

    @Override
    public List<Task> getProjectAllTask(Long projectId) {
        if (projectId == null) {
            throw new CustomException(400, "无效处理（项目id缺失）");
        }

        //获取项目的所有任务id
        List<Long> longList = projectDao.selectAllTaskID(projectId);
        if (longList.isEmpty() || longList.equals(null) || longList.size() == 0) {
            return null;
        }
        List<Task> taskList = new ArrayList<Task>();

        for (Long taskId : longList) {
            //获取每个任务的基本信息
            Task tempTask = taskDao.queryTaskBaseInfo(taskId);
            if (tempTask == null) {
                tempTask = new Task();
            }

            //设置任务的子任务list
            List<Task> subTasks = taskDao.querySubtaskByTaskId(taskId);
            if (!subTasks.isEmpty() && !subTasks.equals(null)) {
                tempTask.setSubTasks(subTasks);
                //设置子任务总数
                tempTask.setSubTaskNum();
                //设置已完成子任务数
                tempTask.setSubTaskCompletedNum();
            }
            //设置任务所需天数
            tempTask.setTaskDays();
            //获取任务的 tagSet
            tempTask.setTags(taskInfoDao.selectTagsByTaskId(taskId));
            //获取任务的 参与者Set
            tempTask.setParticipants(taskInfoDao.selectParticipantsByTaskId(taskId));
            //获取任务的 commentsList
            tempTask.setComments(taskInfoDao.selectCommentsByTaskId(taskId));

            taskList.add(tempTask);
        }

        return taskList;
    }

    @Override
    public List<Task> getProjectWeekTask(Long projectId) throws ParseException {
        if (projectId == null) {
            throw new CustomException(400, "无效处理（项目id缺失）");
        }

        //获取项目的所有任务id
        List<Long> longList = projectDao.selectAllTaskID(projectId);
        if (longList.isEmpty() || longList.equals(null) || longList.size() == 0) {
            return null;
        }
        List<Task> taskList = new ArrayList<Task>();

        for (Long taskId : longList) {
            //获取每个任务的基本信息
            Task tempTask = taskDao.queryTaskBaseInfo(taskId);
            if (tempTask == null) {
                tempTask = new Task();
            } else {
                //判断是否在本周内
                //根据当前日期本周的日期区间
                Map<String, Date> days = DatesUtils.getThisWeekTimeInterval2();
                int start_end = DatesUtils.getTermDays2(tempTask.getTaskEndTime(), days.get("beginDate"));
                int end_start = DatesUtils.getTermDays2(days.get("endDate"), tempTask.getTaskStartTime());
                if (end_start > 0 || start_end > 0) { //不在本周内：跳过
                    continue;
                }
            }

            //设置任务的子任务list
            List<Task> subTasks = taskDao.querySubtaskByTaskId(taskId);
            if (!subTasks.isEmpty() && !subTasks.equals(null)) {
                tempTask.setSubTasks(subTasks);
                //设置子任务总数
                tempTask.setSubTaskNum();
                //设置已完成子任务数
                tempTask.setSubTaskCompletedNum();
            }
            //设置任务所需天数
            tempTask.setTaskDays();
            //获取任务的 tagSet
            tempTask.setTags(taskInfoDao.selectTagsByTaskId(taskId));
            //获取任务的 参与者Set
            tempTask.setParticipants(taskInfoDao.selectParticipantsByTaskId(taskId));
            //获取任务的 commentsList
            tempTask.setComments(taskInfoDao.selectCommentsByTaskId(taskId));

            taskList.add(tempTask);
        }

        return taskList;
    }

    @Override
    public Set<Tag> getProjectAllTag(Long projectId) throws ParseException {
        Set<Tag> projectTagSet = new HashSet<>();

        if (projectId == null) {
            throw new CustomException(400, "无效处理（项目id缺失）");
        }

        //获取项目的所有任务id
        List<Long> longList = projectDao.selectAllTaskID(projectId);
        if (longList.isEmpty() || longList.equals(null) || longList.size() == 0) {
            return null;
        }

        for (Long taskId : longList) {
            //获取每个任务的tagSet
            Set<Tag> tags = taskInfoDao.selectTagsByTaskId(taskId);
            projectTagSet.addAll(tags);
        }

        return projectTagSet;
    }


}
