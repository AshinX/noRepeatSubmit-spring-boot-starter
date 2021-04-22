package com.fengyue95.noRepeatSubmitspringbootstarter.intercepter;

import com.fengyue95.noRepeatSubmitspringbootstarter.intercepter.NoRepeatSubmitIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置
 */
@Configuration
public class WebMvcConfg implements WebMvcConfigurer {

    @Autowired
    private NoRepeatSubmitIntercepter noRepeatSubmitIntercepter;

    /**
     * @return 登录验证拦截器
     * 自定义登录验证拦截器
     */
    @Bean
    public NoRepeatSubmitIntercepter needLoginInterceptor() {
        return noRepeatSubmitIntercepter;
    }

    /**
     * @param registry 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加登录处理拦截器，拦截所有请求，登录请求除外
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(needLoginInterceptor());
        //排除配置
        //interceptorRegistration.excludePathPatterns("/sys/login.json");
        //配置拦截策略
        interceptorRegistration.addPathPatterns("/**");
    }

}
