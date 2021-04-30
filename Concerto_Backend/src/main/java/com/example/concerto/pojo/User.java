package com.example.concerto.pojo;

import java.util.Objects;

/**
 * @author sarise
 * @version 1.0
 * @date 2021/4/20 下午7:15
 */
public class User {
    Long userId;
    String userPhone;
    String userName;
    String userEmail;
    String userPassword;
    String userSalt; //用于加密
    String userIntroducton; //用户介绍


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) &&
                Objects.equals(userPhone, user.userPhone) &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(userEmail, user.userEmail) &&
                Objects.equals(userPassword, user.userPassword) &&
                Objects.equals(userSalt, user.userSalt) &&
                Objects.equals(userIntroducton, user.userIntroducton);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userPhone, userName, userEmail, userPassword, userSalt, userIntroducton);
    }

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

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserSalt() {
        return userSalt;
    }

    public void setUserSalt(String userSalt) {
        this.userSalt = userSalt;
    }

    public String getUserIntroducton() {
        return userIntroducton;
    }

    public void setUserIntroducton(String userIntroducton) {
        this.userIntroducton = userIntroducton;
    }
}
