package com.yany.common.filter.annotation;

import java.lang.annotation.*;

/**
 * Created by yanyong on 2017/2/20.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface YanYUrl {
    String value() default "";
}
