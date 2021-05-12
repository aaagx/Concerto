package com.example.concerto.fo;

import com.example.concerto.pojo.Tag;
import com.example.concerto.pojo.TaskVersion;
import com.example.concerto.pojo.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

/**
 * @author sarise
 * @version 1.0
 * @date 2021/4/27 下午8:37
 */
public class ModifyTaskForm {
    @NotNull(message = "任务版本修改者id不可为空")
    Long taskVersionModifyUserId;
    @NotNull(message = "任务版本修改描述不可为空")
    String taskVersionDescription;
    @NotNull(message = "项目id不可为空")
    Long taskId;
    @NotBlank(message = "任务标题不可为空")
    String taskTitle ;//标题
    @NotNull(message = "任务类型不可为空")
    Integer taskType ; //0-任务  1-子任务  2-里程碑
    Integer taskPriority ;//非常紧急 紧急 普通
    Date taskStartTime ;
    Date taskEndTime ;
    @NotNull(message = "任务版本不可为空")
    Integer taskVersion ; //前端当前版本

    Set<Tag> addTags;
    Set<Tag> delTags;
    Set<User> addParticipants;
    Set<User> delParticipants;


    public TaskVersion getTaskVersionObj(){
        TaskVersion taskVersion = new TaskVersion();
        taskVersion.setTaskId(taskId);

        //修改历史的信息
        taskVersion.setTaskVersionDescription(taskVersionDescription);
        taskVersion.setTaskVersionModifyUserId(taskVersionModifyUserId);
        taskVersion.setTaskVersionModifyTime(new Date());

        //任务信息
        taskVersion.setTaskTitle(taskTitle);
        taskVersion.setTaskType(taskType);
        taskVersion.setTaskPriority(taskPriority);
        taskVersion.setTaskStartTime(taskStartTime);
        taskVersion.setTaskEndTime(taskEndTime);
        taskVersion.setTaskVersion(this.taskVersion);

        return taskVersion;
    }

    public String getTaskVersionDescription() {
        return taskVersionDescription;
    }

    public void setTaskVersionDescription(String taskVersionDescription) {
        this.taskVersionDescription = taskVersionDescription;
    }

    public Long getTaskVersionModifyUserId() {
        return taskVersionModifyUserId;
    }

    public void setTaskVersionModifyUserId(Long taskVersionModifyUserId) {
        this.taskVersionModifyUserId = taskVersionModifyUserId;
    }

    public Set<Tag> getAddTags() {
        return addTags;
    }

    public void setAddTags(Set<Tag> addTags) {
        this.addTags = addTags;
    }

    public Set<Tag> getDelTags() {
        return delTags;
    }

    public void setDelTags(Set<Tag> delTags) {
        this.delTags = delTags;
    }

    public Set<User> getAddParticipants() {
        return addParticipants;
    }

    public void setAddParticipants(Set<User> addParticipants) {
        this.addParticipants = addParticipants;
    }

    public Set<User> getDelParticipants() {
        return delParticipants;
    }

    public void setDelParticipants(Set<User> delParticipants) {
        this.delParticipants = delParticipants;
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

    public Integer getTaskVersion() {
        return taskVersion;
    }

    public void setTaskVersion(Integer taskVersion) {
        this.taskVersion = taskVersion;
    }

}
