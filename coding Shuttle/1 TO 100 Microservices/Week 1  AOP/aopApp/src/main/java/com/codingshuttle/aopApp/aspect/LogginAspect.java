package com.codingshuttle.aopApp.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

//@Aspect
@Component
@Slf4j
public class LogginAspect {

	//@Before("execution(* orderPackage(..))")// can be used for any method anywhere 
	//@Before("execution(* com.codingshuttle.aopApp.serviceImpl.ShipmentServiceImpl.orderPackage(..))")// used only for the specific class and methods 	
	@Before("execution(* com.codingshuttle.aopApp.serviceImpl.*.*(..))")// 
	public void beforOrderPackage(JoinPoint joinPoint) {
		log.info("Before order Package  Called form Loging Aspect, {}",joinPoint.getKind());
		log.info("Before order Package  Called form Loging Aspect, {}",joinPoint.getSignature());

	}
	
	//@Before("within(com.codingshuttle.aopApp.serviceImpl.*)") // for package and run for every method
	@Before("within(com.codingshuttle.aopApp.serviceImpl..*)")// for package and there subpackage too if we use .. 
	public void beforeServiceImplCalls() {
		log.info("Service Impl Calls");
	}
	
	@Before("@annotation(com.codingshuttle.aopApp.aspect.MyLoggingAnnotation)")// anotation based logging
	public void beforeTransactionalAnnotationCall() {
		log.info("Before Transactional Annotation call");
	}
}
