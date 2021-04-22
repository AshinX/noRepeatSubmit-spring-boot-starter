package com.fengyue95.noRepeatSubmitspringbootstarter.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fengyue95.noRepeatSubmitspringbootstarter.annotation.NoRepeatSubmit;
import com.fengyue95.noRepeatSubmitspringbootstarter.keygenerator.service.LockKeyGenerator;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fengyue95.noRepeatSubmitspringbootstarter.util.IpAddressUtil;
import com.google.gson.GsonBuilder;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author fengyue
 * @date 2021/4/22
 */
@Component(value = "noRepeatSubmitIntercepter")
@Slf4j
public class NoRepeatSubmitIntercepter implements HandlerInterceptor {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            NoRepeatSubmit noRepeatSubmitAnnotation = handlerMethod.getMethod().getAnnotation(NoRepeatSubmit.class);
            //如果没有注解 放行
            if (noRepeatSubmitAnnotation == null) {
                return true;
            }
            LockKeyGenerator keyGenerator = null;
            try {
                String generator = noRepeatSubmitAnnotation.generator();
                keyGenerator = (LockKeyGenerator) applicationContext.getBean(generator);
            } catch (BeansException e) {
                log.error("get LockKeyGenerator error:{}", e);
                return true;
            }
            if (keyGenerator == null) {
                log.error("not found keyGenerator={} bean in spring project", noRepeatSubmitAnnotation.generator());
                return true;
            }
            int mode = noRepeatSubmitAnnotation.mode();
            String prefix = noRepeatSubmitAnnotation.lockPrefix();
            String ipAddress = IpAddressUtil.getIpAddress(request);
            String servletPath = request.getServletPath();

            String key = null;
            if (0 == mode) {
                key = prefix + keyGenerator.generate(ipAddress);
            } else if (1 == mode) {
                key = prefix + keyGenerator.generate(ipAddress + servletPath);
            } else {
                key = prefix + keyGenerator.generate(ipAddress);
            }
            RLock lock = redissonClient.getLock(key);

            if (lock.isLocked()) {
                //资源已被锁住（即存在重复请求）
                return false;
            }

            boolean lockResult = lock.tryLock(noRepeatSubmitAnnotation.waitTime(), noRepeatSubmitAnnotation.lockTime(), noRepeatSubmitAnnotation.lockTimeUnit());
            if (!lockResult) {
                //未获取到锁
                return false;
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {

    }
}
