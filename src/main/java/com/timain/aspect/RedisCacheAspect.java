package com.timain.aspect;

import cn.hutool.crypto.digest.MD5;
import com.alibaba.fastjson.JSON;
import com.timain.annotation.RedisCache;
import com.timain.constants.SysConstants;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @Author: yyf
 * @Date: 2021/12/20 22:37
 * @Version: 1.0
 */
@Component
@Aspect
public class RedisCacheAspect {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 定义切点  使用了@RedisCache注解的方法
     */
    @Pointcut("@annotation(com.timain.annotation.RedisCache)")
    public void redisPoint() {}

    /**
     * 环绕通知
     * @param joinPoint
     * @return
     */
    @Around("redisPoint()")
    public Object around(ProceedingJoinPoint joinPoint) {
        LOGGER.info("====== 进入RedisCacheAspect环绕通知 ======");
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            RedisCache redisCache = signature.getMethod().getAnnotation(RedisCache.class);
            if (null == redisCache) {
                return joinPoint.proceed();
            }
            StringBuilder sb = new StringBuilder();
            sb.append(joinPoint.getTarget().getClass().getName());
            String str = SysConstants.KEY_PREFIX + sb;
            String key = DigestUtils.md5DigestAsHex(str.getBytes());
            // 读操作
            if (redisCache.read()) {
                Object object = redisTemplate.opsForValue().get(key);
                if (null != object) {
                    LOGGER.info("====== 从redis缓存中取到数据 ======");
                    return object;
                }
                LOGGER.info("====== redis缓存中不存在该key值，从数据库中查询，并保存到redis中 ======");
                object = joinPoint.proceed();
                if (null != object) {
                    if (redisCache.expired() > 0) {
                        redisTemplate.opsForValue().set(key, object, redisCache.expired(), TimeUnit.SECONDS);
                    } else {
                        redisTemplate.opsForValue().set(key, object);
                    }
                }
                return object;
            }
            // 写操作
            LOGGER.info("====== 操作数据，从redis中删除缓存 ======");
            redisTemplate.delete(key);
            return joinPoint.proceed();
        } catch (Throwable e) {
            LOGGER.error("====== RedisCache环绕通知执行异常：{} ======", e.getMessage());
        }
        return null;
    }
}
