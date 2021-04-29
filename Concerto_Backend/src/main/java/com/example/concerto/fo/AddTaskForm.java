package com.example.concerto.fo;

import com.example.concerto.pojo.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author sarise
 * @version 1.0
 * @date 2021/4/28 上午9:32
 */
public class AddTaskForm {
    Long taskVersionModifyUserId;
    Long projectId;
    String taskTitle ;//标题
    Integer taskPriority ;//非常紧急 紧急 普通
    Date taskStartTime ;
    Date taskEndTime ;

    Set<Tag> tags;
    Set<User> participants;
    List<SubtaskForm> subTasks;

    public TaskVersion getTaskVersionObj(){
        TaskVersion initTaskVersion = new TaskVersion();

        //修改历史的信息
        initTaskVersion.setTaskVersionDescription("任务初始化");
        initTaskVersion.setTaskVersionModifyUserId(taskVersionModifyUserId);
        initTaskVersion.setTaskVersionModifyTime(new Date());

        //任务信息
        initTaskVersion.setTaskTitle(taskTitle);
        initTaskVersion.setTaskType(0);
        initTaskVersion.setTaskPriority(taskPriority==null?0:taskPriority);
        initTaskVersion.setTaskStartTime(taskStartTime);
        initTaskVersion.setTaskEndTime(taskEndTime);
        initTaskVersion.setTaskVersion(0);

        return initTaskVersion;
    }

    public TaskPo getTaskPo(){
        TaskPo initTask = new TaskPo();

        //任务信息
        initTask.setTaskVersion(0);
        initTask.setTaskStatus(0);
        //initTask.setParentTaskId(-1L);
        initTask.setProjectId(projectId);

        return initTask;
    }


    public Long getTaskVersionModifyUserId() {
        return taskVersionModifyUserId;
    }

    public void setTaskVersionModifyUserId(Long taskVersionModifyUserId) {
        this.taskVersionModifyUserId = taskVersionModifyUserId;
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

    public List<SubtaskForm> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<SubtaskForm> subTasks) {
        this.subTasks = subTasks;
    }

}
