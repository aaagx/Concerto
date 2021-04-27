package com.example.concerto.exception;

/**
 * @ Author     ：aaagx.
 * @ Date       ：Created in 23:00 2021/4/25
 * @Version: 1.0$
 */
//自定义异常
public class CustomException extends RuntimeException{
    //异常错误编码
    private int code ;
    //异常信息
    private String message;

    public  CustomException(int code,String message)
    {
        this.code=code;
        this.message=message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
