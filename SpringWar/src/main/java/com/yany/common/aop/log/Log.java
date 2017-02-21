package com.yany.common.aop.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yanyong on 2017/2/21.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Log {
    String name() default "";

    /**
     * 方法调用之前的日志
     *
     * @return
     */
    boolean beforeLog() default true;

    /**
     * 方法调用之后的日志
     *
     * @return
     */
    boolean afterLog() default true;
}
