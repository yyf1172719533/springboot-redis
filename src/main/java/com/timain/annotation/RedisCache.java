package com.timain.annotation;

import java.lang.annotation.*;

/**
 * redis 缓存注解
 * @author yyf1172719533
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisCache {

    /**
     * 键
     */
    String key() default "";

    /**
     * 过期时间
     */
    long expired() default -1;

    /**
     * 是否为查询操作
     * 如果为写入数据库操作，该值为false
     */
    boolean read() default true;
}
