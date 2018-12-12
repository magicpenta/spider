package com.panda.annotation;

import java.lang.annotation.*;

/**
 * 字段中文名称注解
 *
 * @author panda
 * @date 2018/11/25
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ChineseName {
    String value() default "";
}
