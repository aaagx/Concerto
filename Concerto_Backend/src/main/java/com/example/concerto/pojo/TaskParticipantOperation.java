package com.example.concerto.pojo;

/**
 * @author sarise
 * @version 1.0
 * @date 2021/4/20 下午7:31
 */
public class TaskParticipantOperation {
    Long taskParticipantOperationId ;
    Long taskId ;
    Long taskVersion ;
    Integer taskOperationType ;//1增加 -1删除
    Long participantId;


    public Long getTaskParticipantOperationId() {
        return taskParticipantOperationId;
    }

    public void setTaskParticipantOperationId(Long taskParticipantOperationId) {
        this.taskParticipantOperationId = taskParticipantOperationId;
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

    public Integer getTaskOperationType() {
        return taskOperationType;
    }

    public void setTaskOperationType(Integer taskOperationType) {
        this.taskOperationType = taskOperationType;
    }

    public Long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }
}
