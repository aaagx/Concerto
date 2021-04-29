package com.example.concerto.utils;

import java.util.Random;

/**
 * @ Author     ：aaagx.
 * @ Date       ：Created in 17:14 2021/4/27
 * @Version: 1.0$
 */

public class CaptchaUtils {

    public static String randomCaptcha() {
        Random random = new Random( System.currentTimeMillis());
        StringBuffer stringBuffer = new StringBuffer();
        //生成4位的随机数
        for (int i = 0;i<4;i++){
            int i1 = random.nextInt(10);
            stringBuffer.append(i1);
        }
        return stringBuffer.toString();
    }
}
