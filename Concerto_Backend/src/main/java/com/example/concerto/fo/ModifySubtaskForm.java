package com.example.concerto.fo;

import com.example.concerto.pojo.User;

import java.util.Set;

/**
 * @author sarise
 * @version 1.0
 * @date 2021/4/28 下午10:25
 */
public class ModifySubtaskForm {
    Long taskId;
    String taskTitle ;//标题

    Set<User> participants;

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

    public Set<User> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<User> participants) {
        this.participants = participants;
    }
}
