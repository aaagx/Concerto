package com.example.concerto.vo;

import com.example.concerto.pojo.User;
import com.example.concerto.pojo.Userinfo;

import java.util.Date;

/**
 * @ Author     ：aaagx.
 * @ Date       ：Created in 22:36 2021/5/6
 * @Version: 1$
 */

public class ProjectVo {
    Long projectId ;
    String projectName ;
    String projectDescription;
    //@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    Date projectStartTime;
    //@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    Date projectEndTime;
    Userinfo admin;


    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public Date getProjectStartTime() {
        return projectStartTime;
    }

    public void setProjectStartTime(Date projectStartTime) {
        this.projectStartTime = projectStartTime;
    }

    public Date getProjectEndTime() {
        return projectEndTime;
    }

    public void setProjectEndTime(Date projectEndTime) {
        this.projectEndTime = projectEndTime;
    }

    public Userinfo getAdmin() {
        return admin;
    }

    public void setAdmin(Userinfo admin) {
        this.admin = admin;
    }
}
