package com.example.concerto.fo;

import com.example.concerto.pojo.TaskPo;
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
public class SubtaskForm {
    @NotNull(message = "任务版本修改者id不可为空")
    Long taskVersionModifyUserId;
    @NotNull(message = "父任务id不可为空")
    Long parentTaskId;
    @NotBlank(message = "任务标题不可为空")
    String taskTitle ;//标题

    Set<User> participants;

    public TaskVersion getTaskVersionObj(){
        TaskVersion taskVersion = new TaskVersion();

        //修改信息
        taskVersion.setTaskVersionDescription("子任务");
        taskVersion.setTaskVersionModifyUserId(taskVersionModifyUserId);
        taskVersion.setTaskVersionModifyTime(new Date());

        //任务信息
        taskVersion.setTaskTitle(taskTitle);
        taskVersion.setTaskType(1);
        taskVersion.setTaskPriority(0);
        taskVersion.setTaskVersion(0);

        return taskVersion;
    }

    public TaskPo getTaskPo(){
        TaskPo initTask = new TaskPo();

        //任务信息
        initTask.setTaskVersion(0);
        initTask.setTaskStatus(0);
        initTask.setParentTaskId(parentTaskId);

        return initTask;
    }

    public Long getTaskVersionModifyUserId() {
        return taskVersionModifyUserId;
    }

    public void setTaskVersionModifyUserId(Long taskVersionModifyUserId) {
        this.taskVersionModifyUserId = taskVersionModifyUserId;
    }

    public Long getParentTaskId() {
        return parentTaskId;
    }

    public void setParentTaskId(Long parentTaskId) {
        this.parentTaskId = parentTaskId;
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
