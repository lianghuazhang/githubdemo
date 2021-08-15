package com.example.springbootlogliz01.aop;

import com.example.springbootlogliz01.pojo.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class UserAopTest {

    private static final Logger log = LoggerFactory.getLogger(UserAopTest.class);


    @Pointcut("execution(* com.example.springbootlogliz01.service.impl.*.*(..))")
    public void webLog(){}


    /**
     * execution(* com.atguigu.spring5.aopanno.User.add(..))
     */

    /**
     * 前置通知
     */
    @Before("webLog()")
    public void before(){
        log.info("before get into insert");
    }

    /**
     * 最终通知
     * 相当于final
     */
    @After("webLog()")
    public void after(){
        log.info("after get into insert");
    }

    /**
     * 后置通知
     * 跟after区别在于是否异常返回
     */
    @AfterReturning("webLog()")
    public void returning(){
        log.info("returning get into insert");
    }

    /**
     * 异常通知
     */
    @AfterThrowing("webLog()")
    public void error(){
        log.info("error get into insert");

    }

    /**
     * 环绕通知
     * @param processjoin
     * @throws Throwable
     */
//    @Around(value="execution(* com.example.springbootlogliz01.service.impl.UserviceImpl.*(..))")
    @Around("webLog()")
    public void Around(ProceedingJoinPoint processjoin) throws Throwable {
        log.info("arround get into insert before process");
        processjoin.proceed();
        log.info("arround get into insert after process");

    }

}
