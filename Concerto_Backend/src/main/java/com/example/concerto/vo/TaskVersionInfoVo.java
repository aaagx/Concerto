package com.example.concerto.vo;

import com.example.concerto.pojo.User;

import java.util.Date;

/**
 * @ Author     ：aaagx.
 * @ Date       ：Created in 20:16 2021/6/10
 * @Version: 1.0$
 */

public class TaskVersionInfoVo {
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

    public Long getTaskVersion() {
        return taskVersion;
    }

    public void setTaskVersion(Long taskVersion) {
        this.taskVersion = taskVersion;
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



    Long taskVersionId ;
    Long taskId ;
    Long taskVersion ;//任务版本
    String taskVersionDescription ; //修改描述
    Date taskVersionModifyTime ; //修改时间
    Long taskVersionModifyUserId ; //修改人ID
    PersonnelVo modifyUser;

    public PersonnelVo getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(PersonnelVo modifyUser) {
        this.modifyUser = modifyUser;
    }
}
