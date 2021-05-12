package com.example.concerto.Interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.concerto.annotation.PassToken;
import com.example.concerto.annotation.UserLoginToken;
import com.example.concerto.exception.CustomException;
import com.example.concerto.pojo.User;
import com.example.concerto.service.UserService;
import com.example.concerto.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

/**
 * @ Author     ：aaagx.
 * @ Date       ：Created in 16:33 2021/4/25
 * @Version: 1.0$
 * @deprison:登录验证拦截器
 */
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token


        // 如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)object;
        Method method=handlerMethod.getMethod();


        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }


        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                // 检查是否有token
                if (token == null) {
                    throw new CustomException(401,"无token，请重新登录");
                }

                // 获取 token 中的 user id
                long userId=-1;
                try
                {
                    userId= TokenUtils.getIdInToken(token);
                    User user = userService.getUserById(userId);
                }
                    catch (JWTDecodeException e) {
                    e.printStackTrace();
                    throw new CustomException(401,"token未通过验证，请重新登录");
                }

                //检查用户是否存在
                User user=userService.getUserById(userId);
                if (user == null) {
                    throw new CustomException(401,"用户不存在，请重新登录");
                }


                //验证token正确性
                try {
                    TokenUtils.checkToken(token,user.getUserPassword());
                } catch (JWTVerificationException e) {
                    e.printStackTrace();
                    throw new CustomException(401,"toeken失效，请重新登录");
                }

                httpServletRequest.getSession().setAttribute("UserId",userId);
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}
