package com.fengyue95.noRepeatSubmitspringbootstarter.annotation;

import com.fengyue95.noRepeatSubmitspringbootstarter.keygenerator.DefaultLockKeyGenerator;
import com.fengyue95.noRepeatSubmitspringbootstarter.keygenerator.service.LockKeyGenerator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * @author fengyue
 * @date 2021/4/22
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface NoRepeatSubmit {

    /**
     * 缓存前缀，用于区分不同的业务
     *
     * @return
     */
    String lockPrefix() default "";


    /**
     * 锁等待时间
     *
     * @return
     */
    int waitTime() default 3;

    /**
     * 设置请求锁定时间（默认10秒）
     *
     * @return
     */
    int lockTime() default 10;

    /**
     * 缓存锁定时间单位（默认秒）
     *
     * @return
     */
    TimeUnit lockTimeUnit() default TimeUnit.SECONDS;


    /**
     * 拦截模式
     * 0 仅限 ip 拦截
     * 1 仅限 ip+url 拦截
     * 2 仅限 ip+url+requestParam.toString 拦截
     *
     * @return
     */
    int mode() default 0;


    /**
     * 生成key方式
     *
     * @return
     */
    String generator() default "com.fengyue95.noRepeatSubmitspringbootstarter.keygenerator.DefaultLockKeyGenerator";


}
