package com.example.concerto.pojo;

/**
 * @author sarise
 * @version 1.0
 * @date 2021/4/20 下午7:41
 */
public class UserProject {
    Long user_project_id;
    Long user_id;
    Long project_id;
    Integer user_role; //"0成员 1管理者 2候选人" comment "加入项目 未通过准入则为候选人"

    public Long getUser_project_id() {
        return user_project_id;
    }

    public void setUser_project_id(Long user_project_id) {
        this.user_project_id = user_project_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getProject_id() {
        return project_id;
    }

    public void setProject_id(Long project_id) {
        this.project_id = project_id;
    }

    public Integer getUser_role() {
        return user_role;
    }

    public void setUser_role(Integer user_role) {
        this.user_role = user_role;
    }
}
