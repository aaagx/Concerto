package com.example.concerto.configuration;

import com.example.concerto.Interceptor.AuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ Author     ：aaagx.
 * @ Date       ：Created in 17:09 2021/4/25
 * @Version: 1.0$
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    AuthenticationInterceptor authenticationInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns("/**");
    }
}