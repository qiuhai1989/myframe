package com.haiqiu.aop;

import com.haiqiu.constant.DataSources;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/4/2.
 */
@Aspect    // for aop
public class DataSourceInterceptor {

    @Pointcut("execution(* com.haiqiu.nature.rest.*.*(..))")
    public void dataSourceSlave(){};

    @Before("dataSourceSlave()")
    public void before(JoinPoint jp) {
        DataSourceTypeManager.set(DataSources.SLAVE);
    }

}
