package com.example.timerAOPstarter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimerAspect {

  @Pointcut("@annotation(com.example.timerAOPstarter.Timer)")
  public void methodsForTimeLogging() {
  }

  @Around("methodsForTimeLogging()")
  public Object methodTimer(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    long start = System.currentTimeMillis();
    Object result = proceedingJoinPoint.proceed();
    long timeToProcess = System.currentTimeMillis() - start;
    String className = proceedingJoinPoint.getTarget().getClass().getSimpleName();
    String methodName = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod().getName();
    System.out.printf("%s - %s #%d \n",className,methodName,timeToProcess);
    return result;
  }
}
