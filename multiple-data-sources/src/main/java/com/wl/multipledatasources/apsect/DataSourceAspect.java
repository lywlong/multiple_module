package com.wl.multipledatasources.apsect;

import com.wl.multipledatasources.config.DataSourceHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author WangLong
 * @Description:
 * @date 2022/4/20 10:16
 */
@Component
@Aspect
public class DataSourceAspect {

    @Pointcut("execution(public * com.wl.multipledatasources.service.*.*(..))")
    private void dataSourceAspect(){}


    @Before(value = "dataSourceAspect()")
    public void doBeforeAction(JoinPoint joinPoint){
        //获取方法名字
        String methodName = joinPoint.getSignature().getName();
        if (methodName.startsWith("find")
                ||methodName.startsWith("get")
                ||methodName.startsWith("query")
                ||methodName.startsWith("select")
                ||methodName.startsWith("obtain")) {

            DataSourceHolder.set(1);//从库读操作

        }else{
            DataSourceHolder.get();//主库写操作
        }
    }

}
