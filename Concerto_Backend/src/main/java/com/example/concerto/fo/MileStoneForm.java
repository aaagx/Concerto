package com.example.concerto.fo;

import com.example.concerto.pojo.TaskPo;
import com.example.concerto.pojo.TaskVersion;

import java.util.Date;

/**
 * @author sarise
 * @version 1.0
 * @date 2021/4/28 下午9:40
 */
public class MileStoneForm {
    Long taskVersionModifyUserId;
    Long projectId;
    String taskTitle ;
    Date taskStartTime ;


    public TaskPo getTaskPo(){
        TaskPo initTask = new TaskPo();

        //任务信息
        initTask.setTaskVersion(0);
        initTask.setTaskStatus(0);
        //initTask.setParentTaskId(-1L);
        initTask.setProjectId(projectId);

        return initTask;
    }


    public TaskVersion getTaskVersionObj(){
        TaskVersion initTaskVersion = new TaskVersion();

        //修改历史的信息
        initTaskVersion.setTaskVersionDescription("里程碑");
        initTaskVersion.setTaskVersionModifyUserId(taskVersionModifyUserId);
        initTaskVersion.setTaskVersionModifyTime(new Date());

        //任务信息
        initTaskVersion.setTaskTitle(taskTitle);
        initTaskVersion.setTaskType(2);
        initTaskVersion.setTaskPriority(0);
        initTaskVersion.setTaskStartTime(taskStartTime);
        initTaskVersion.setTaskEndTime(taskStartTime);
        initTaskVersion.setTaskVersion(0);

        return initTaskVersion;
    }

    public Long getTaskVersionModifyUserId() {
        return taskVersionModifyUserId;
    }

    public void setTaskVersionModifyUserId(Long taskVersionModifyUserId) {
        this.taskVersionModifyUserId = taskVersionModifyUserId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public Date getTaskStartTime() {
        return taskStartTime;
    }

    public void setTaskStartTime(Date taskStartTime) {
        this.taskStartTime = taskStartTime;
    }
}
