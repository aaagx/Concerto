package com.example.concerto.pojo;

import java.util.Date;

/**
 * @author sarise
 * @version 1.0
 * @date 2021/4/26 下午9:00
 */
public class TaskVersion {
    Long taskVersionId ;
    Long taskId ;

    String taskVersionDescription ; //修改描述
    Date taskVersionModifyTime ; //修改时间
    Long taskVersionModifyUserId ; //修改人ID

    String taskTitle ;//标题
    Integer taskType ; //里程碑 任务 子任务
    Integer taskPriority ;//非常紧急 紧急 普通
    Date taskStartTime ;
    Date taskEndTime ;
    Integer taskVersion ;//任务版本

    public Long getTaskVersionId() {
        return taskVersionId;
    }

    public void setTaskVersionId(Long taskVersionId) {
        this.taskVersionId = taskVersionId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskVersionDescription() {
        return taskVersionDescription;
    }

    public void setTaskVersionDescription(String taskVersionDescription) {
        this.taskVersionDescription = taskVersionDescription;
    }

    public Date getTaskVersionModifyTime() {
        return taskVersionModifyTime;
    }

    public void setTaskVersionModifyTime(Date taskVersionModifyTime) {
        this.taskVersionModifyTime = taskVersionModifyTime;
    }

    public Long getTaskVersionModifyUserId() {
        return taskVersionModifyUserId;
    }

    public void setTaskVersionModifyUserId(Long taskVersionModifyUserId) {
        this.taskVersionModifyUserId = taskVersionModifyUserId;
    }

    public Integer getTaskVersion() {
        return taskVersion;
    }

    public void setTaskVersion(Integer taskVersion) {
        this.taskVersion = taskVersion;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public Integer getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(Integer taskPriority) {
        this.taskPriority = taskPriority;
    }

    public Date getTaskStartTime() {
        return taskStartTime;
    }

    public void setTaskStartTime(Date taskStartTime) {
        this.taskStartTime = taskStartTime;
    }

    public Date getTaskEndTime() {
        return taskEndTime;
    }

    public void setTaskEndTime(Date taskEndTime) {
        this.taskEndTime = taskEndTime;
    }
}
