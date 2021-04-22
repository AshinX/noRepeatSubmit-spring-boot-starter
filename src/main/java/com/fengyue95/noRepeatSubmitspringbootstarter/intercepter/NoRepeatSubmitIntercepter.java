package com.fengyue95.noRepeatSubmitspringbootstarter.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fengyue95.noRepeatSubmitspringbootstarter.util.IpAddressUtil;
import com.google.gson.GsonBuilder;

import lombok.extern.slf4j.Slf4j;

/**
 * @author fengyue
 * @date 2021/4/22
 */
@Component(value = "noRepeatSubmitIntercepter")
@Slf4j
public class NoRepeatSubmitIntercepter implements HandlerInterceptor {

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        String ipAddress = IpAddressUtil.getIpAddress(request);
        log.info("noRepeatSubmitIntercepter.preHandle.ipAddress:{}", ipAddress);
        log.info("noRepeatSubmitIntercepter.preHandle.request:{}", new GsonBuilder().create().toJson(request));
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {
        log.info("noRepeatSubmitIntercepter.afterCompletion.request:{}", new GsonBuilder().create().toJson(request));
        log.info("noRepeatSubmitIntercepter.afterCompletion.response:{}", new GsonBuilder().create().toJson(response));

    }
}
