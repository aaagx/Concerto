package com.example.concerto.pojo;

/**
 * @author sarise
 * @version 1.0
 * @date 2021/4/20 下午7:40
 */
public class Message {
    Long messageId;
    Long userId;
    String messageContent;//  comment "进入项目成功\失败  用户管理的项目有新的申请"
    Integer messageStatus;//   comment "已读\未读"

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Integer getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(Integer messageStatus) {
        this.messageStatus = messageStatus;
    }
}
