package com.wisely.ch8_4.config.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 *
 *  调用方法的参数日志
 *
 * Created by jun.zhao on 2017/8/31.
 */
@Aspect
@Component
public class LogAspect {

    private static Logger logger = LoggerFactory.getLogger(LogAspect.class);

    /**
     * com.alibaba.druid.pool.DruidDataSource - {dataSource-1} inited
     * com.alibaba.druid.pool.DruidDataSource - {dataSource-2} inited
     * 上面这种是连接池的创建日志
     */

    @Around("execution(* com.wisely.ch8_4.service.impl.*.*(..))")
    public Object methodAop(ProceedingJoinPoint pjp)
            throws Throwable {

        long from = System.currentTimeMillis();
        String targetMethodName = pjp.getSignature().getName();
        Object[] args = pjp.getArgs();
        if (targetMethodName == null) {
            throw new IllegalArgumentException("aop拦截方法，方法名位空");
        }
        if (args == null || args.length < 1) {
            throw new IllegalArgumentException("aop拦截方法，至少包含一个切片参数");
        }

        logger.info("Invoked {} start, request:{}", targetMethodName, Arrays.toString(args));

        Object result = null;
        try {
            result = pjp.proceed();
        } catch (Throwable t) {
            throw t;
        } finally {
            long to = System.currentTimeMillis();
            logger.info("Invoked {} end, request {}, response {}, cost={}", targetMethodName,
                    Arrays.toString(args), result, to - from);
        }
        return result;
    }
}
