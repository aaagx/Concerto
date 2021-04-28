package com.example.concerto.pojo;

/**
 * @ Author     ：aaagx.
 * @ Date       ：Created in 19:20 2021/4/27
 * @Version: 1.0$
 */

public class Userinfo {
    Long userId;
    String userPhone;
    String userName;
    String userEmail;
    String userIntroducton; //用户介绍

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserIntroducton() {
        return userIntroducton;
    }

    public void setUserIntroducton(String userIntroducton) {
        this.userIntroducton = userIntroducton;
    }
}
