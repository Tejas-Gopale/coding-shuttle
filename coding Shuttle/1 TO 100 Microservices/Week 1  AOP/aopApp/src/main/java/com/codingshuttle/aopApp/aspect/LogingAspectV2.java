package com.codingshuttle.aopApp.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Aspect
public class LogingAspectV2 {

	@Before("allServiceMethodPointCuts()")
	public void beforeServiceMethodCalls(JoinPoint joinPoint) {
		log.info("Before Advice Method call, {} ",joinPoint.getSignature());
	}
	
	//@After("allServiceMethodPointCuts()") // 
	@AfterReturning(value ="allServiceMethodPointCuts()" , returning= "retrunObject")
	public void afterServiceMethodCalls(JoinPoint joinPoint,Object retrunObject) {
		log.info("After Advice Method call, {} ",joinPoint.getSignature());
		log.info("After Advice Method call, {}",retrunObject );
	}
	
	@AfterThrowing("allServiceMethodPointCuts()")
	public void afterServiceMethodCallsThrows(JoinPoint joinPoint) {
		log.info("After Advice Method call, {} ",joinPoint.getSignature());
		log.info("After Advice Method call, {}" );
	}
	
	@Around("allServiceMethodPointCuts()")
	public Object longExcecuationTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		Long startTime = System.currentTimeMillis();
	 Object retrunValue =	proceedingJoinPoint.proceed();
		Long endTime = System.currentTimeMillis();
	Long dif = endTime - startTime;
	
	log.info("Time taken for {}  is  {}", proceedingJoinPoint.getSignature(), dif);
	return retrunValue;
	}
	
	@Pointcut("execution(* com.codingshuttle.aopApp.serviceImpl.*.*(..))")
	public void allServiceMethodPointCuts() {
		
	}
}
