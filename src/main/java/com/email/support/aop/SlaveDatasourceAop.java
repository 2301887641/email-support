package com.email.support.aop;

import com.email.support.config.DynamicDataSourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * slave数据源aop
 * @author suiguozhen
 * @date 19/07/06
 */
@Aspect
@Component
public class SlaveDatasourceAop implements Ordered {
    private static final Logger LOGGER = LoggerFactory.getLogger(SlaveDatasourceAop.class);

    @Pointcut("@annotation(com.email.support.annotation.SlaveDatasource)")
    public void slaveDatasource(){}

    @Around("slaveDatasource()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            DynamicDataSourceContextHolder.setContextHolder(DynamicDataSourceContextHolder.DatasourseType.SLAVE);
            LOGGER.info("========== change to slave ==========");
            return joinPoint.proceed();
        }finally {
            DynamicDataSourceContextHolder.removeDataSourceType();
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
