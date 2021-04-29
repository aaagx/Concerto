package com.example.concerto.vo;

import com.example.concerto.pojo.Task;
import com.example.concerto.pojo.User;

import java.util.Set;

/**
 * @author sarise
 * @version 1.0
 * @date 2021/4/28 上午10:20
 */
public class SubtaskVo {
    Long taskId;
    String taskTitle ;//标题
    Integer taskStatus ; //完成状态

    Set<User> participants;


    public void setSimpleInfoByTask(Task task){
        taskId = task.getTaskId();
        taskTitle = task.getTaskTitle();
        taskStatus = task.getTaskStatus();
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

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Set<User> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<User> participants) {
        this.participants = participants;
    }
}
