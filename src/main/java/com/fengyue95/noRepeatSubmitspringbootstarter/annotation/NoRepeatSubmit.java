package com.fengyue95.noRepeatSubmitspringbootstarter.annotation;

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
}
