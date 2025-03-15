package org.dows.log.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Actlog {
    String action() default "";

    /**
     * 是否启用
     *
     * @return
     */
    boolean enable() default true;

    //LogType type() default LogType.SELECT;

    Class<?>[] tables() default {};
}
