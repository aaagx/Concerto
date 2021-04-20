package com.example.concerto.pojo;

/**
 * @author sarise
 * @version 1.0
 * @date 2021/4/20 下午7:41
 */
public class UserAdvice {
    Long userAdviceId;
    Long userId;
    String adviceContent;

    public Long getUserAdviceId() {
        return userAdviceId;
    }

    public void setUserAdviceId(Long userAdviceId) {
        this.userAdviceId = userAdviceId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAdviceContent() {
        return adviceContent;
    }

    public void setAdviceContent(String adviceContent) {
        this.adviceContent = adviceContent;
    }
}
