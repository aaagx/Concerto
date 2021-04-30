package com.example.concerto.vo;

import com.example.concerto.pojo.Tag;
import com.example.concerto.pojo.Task;
import com.example.concerto.pojo.User;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author sarise
 * @version 1.0
 * @date 2021/4/28 上午10:20
 */
public class TaskVo {
    Long taskId;
    Integer taskVersion;
    String taskTitle ;//标题
    Integer taskPriority ;//非常紧急 紧急 普通
    Integer taskStatus ;
    Integer taskType;
    Date taskStartTime ;
    Date taskEndTime ;


    Set<Tag> tags;
    Set<User> participants;
    List<SubtaskVo> subTasks;

    public void setSimpleInfoByTask(Task task){
        taskId = task.getTaskId();
        taskVersion = task.getTaskVersion();
        taskTitle = task.getTaskTitle();
        taskPriority = task.getTaskPriority();
        taskStatus = task.getTaskStatus();
        taskType = task.getTaskType();
        taskStartTime = task.getTaskStartTime();
        taskEndTime = task.getTaskEndTime();
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Integer getTaskVersion() {
        return taskVersion;
    }

    public void setTaskVersion(Integer taskVersion) {
        this.taskVersion = taskVersion;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
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

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Set<User> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<User> participants) {
        this.participants = participants;
    }

    public List<SubtaskVo> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<SubtaskVo> subTasks) {
        this.subTasks = subTasks;
    }
}
