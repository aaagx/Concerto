package com.example.concerto.exception;

import com.example.concerto.response.CommonResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ Author     ：aaagx.
 * @ Date       ：Created in 23:03 2021/4/25
 * @Version: 1.0$
 */
//全局异常处理器
@ControllerAdvice
public class WebExceptionHandler {

    //处理自定义异常
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public CommonResponse customerException(CustomException e) {
        e.printStackTrace();
        CommonResponse commonResponse=new CommonResponse(e.getCode(),e.getMessage(),"");
        return commonResponse;
    }

    //处理未能捕获（遗漏的）异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonResponse exception(Exception e) {
        e.printStackTrace();
        CommonResponse commonResponse=new CommonResponse(500,"未定义异常 请联系系统管理员", "");
        return commonResponse;
    }
}
