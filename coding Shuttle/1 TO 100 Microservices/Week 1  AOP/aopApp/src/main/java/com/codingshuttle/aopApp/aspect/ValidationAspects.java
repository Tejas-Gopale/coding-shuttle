package com.codingshuttle.aopApp.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j
@Component
public class ValidationAspects {

	@Pointcut("execution(* com.codingshuttle.aopApp.aspect.servceImpl.*.*(..))")
	public void allServiceMethodsPointCut() {
		
	}
	
	@Around("allServiceMethodsPointCut()")
	public Object validateOrderId(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		Object args[] = proceedingJoinPoint.getArgs();
		
		Long orderId = (Long)args[0];
		
		if(orderId > 0) return proceedingJoinPoint.proceed();
		
		return "Cannot call with negative order Id";
	}
}
