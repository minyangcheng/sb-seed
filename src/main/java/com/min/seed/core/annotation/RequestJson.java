package com.min.seed.core.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author qujiawen
 * @version 1.0.0
 * @ClassName RequestJson.java
 * @Description TODO
 * @createTime 2021年11月24日 13:55:00
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestJson {
    @AliasFor("name")
    String value() default "";

    @AliasFor("value")
    String name() default "";

    boolean required() default false;

    String defaultValue() default "";
}
