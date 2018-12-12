package com.panda.plugins;

import java.lang.annotation.*;

/**
 * 插件注解
 *
 * @author panda
 * @date 2017/12/01
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Plugin {
    String value() default "";
}
