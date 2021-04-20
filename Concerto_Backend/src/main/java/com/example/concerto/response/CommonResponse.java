package com.example.concerto.response;

/**
 * @author sarise
 * @version 1.0
 * @date 2021/4/20 下午7:46
 */
public class CommonResponse {
    /**
     * status
     * 200 成功
     * 400 失败
     * 403 登陆过期
     */
    Integer status;
    String message;
    Object data;

    public CommonResponse(Integer status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
