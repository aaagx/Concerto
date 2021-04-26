package com.example.concerto.pojo;

/**
 * @ Author     ：aaagx.
 * @ Date       ：Created in 23:27 2021/4/25
 * @Version: 1.0$
 */

public class RegisterForm {
    private  String name;
    private String email;
    private String captcha;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
