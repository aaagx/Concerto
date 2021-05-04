package com.example.concerto.fo;

import com.example.concerto.pojo.TaskPo;
import com.example.concerto.pojo.TaskVersion;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author sarise
 * @version 1.0
 * @date 2021/4/28 下午9:40
 */
public class MileStoneForm {
    @NotNull(message = "任务版本修改者id不可为空")
    Long taskVersionModifyUserId;
    @NotNull(message = "项目id不可为空")
    Long projectId;
    @NotBlank(message = "里程碑名不可为空")
    String taskTitle ;
    @NotNull(message = "里程碑时间不可为空")
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
