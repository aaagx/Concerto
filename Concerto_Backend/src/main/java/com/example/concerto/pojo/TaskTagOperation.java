package com.example.concerto.pojo;

/**
 * @author sarise
 * @version 1.0
 * @date 2021/4/20 下午7:37
 */
public class TaskTagOperation {
    Long TaskTagOperationId ;
    Long taskId ;
    Integer taskVersion ;
    Integer taskOperationType ;//1增加 -1删除
    Long tagId;

    public Long getTaskTagOperationId() {
        return TaskTagOperationId;
    }

    public void setTaskTagOperationId(Long taskTagOperationId) {
        TaskTagOperationId = taskTagOperationId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Integer getTaskVersion() {
        return taskVersion;
    }

    public void setTaskVersion(Integer taskVersion) {
        this.taskVersion = taskVersion;
    }

    public Integer getTaskOperationType() {
        return taskOperationType;
    }

    public void setTaskOperationType(Integer taskOperationType) {
        this.taskOperationType = taskOperationType;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }
}
