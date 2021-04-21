package com.example.concerto.pojo;

import java.util.Date;

/**
 * @author sarise
 * @version 1.0
 * @date 2021/4/20 下午7:38
 */
public class TaskComment {
    Long taskCommentId;
    Long taskId;
    String commentContent;
    Date commentTime;
    Long taskCommentUserId;

    public Long getTaskCommentId() {
        return taskCommentId;
    }

    public void setTaskCommentId(Long taskCommentId) {
        this.taskCommentId = taskCommentId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public Long getTaskCommentUserId() {
        return taskCommentUserId;
    }

    public void setTaskCommentUserId(Long taskCommentUserId) {
        this.taskCommentUserId = taskCommentUserId;
    }
}
