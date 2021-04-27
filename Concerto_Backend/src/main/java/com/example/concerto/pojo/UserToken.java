package com.example.concerto.pojo;

/**
 * @ Author     ：aaagx.
 * @ Date       ：Created in 22:30 2021/4/26
 * @Version: 1.0$
 */

public class UserToken {
    int userTokenId;
    long userId;
    String userToken;
    String validateDate;

    public int getUserTokenId() {
        return userTokenId;
    }

    public void setUserTokenId(int userTokenId) {
        this.userTokenId = userTokenId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getValidateDate() {
        return validateDate;
    }

    public void setValidateDate(String validateDate) {
        this.validateDate = validateDate;
    }
}
