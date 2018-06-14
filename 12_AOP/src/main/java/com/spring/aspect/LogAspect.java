package com.spring.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class LogAspect {
	
	@Before(value = "execution(* com.spring.service.AopService.before(..))")
	public void logBefore(JoinPoint jp) {
		// JoinPoint에는 실행 지점에 대한 정보가 있다.
		System.out.println(jp.getSignature().getName() + " 실행 전");  // JoinPoint(대상 메서드)명
		System.out.println("인자값 : " + Arrays.toString(jp.getArgs()));  // JoinPoint 인자값
	}
	
	@After(value = "execution(* com.spring.service.AopService.after(..))")
	public void logAfter(JoinPoint jp) {
		System.out.println(jp.getSignature().getName() + " 실행 후");
	}
	
	@AfterReturning(value = "execution(* com.spring.service.AopService.afterReturning(..))", 
			returning = "result")
	public void logAfterReturning(JoinPoint jp, Object result) {
		System.out.println(jp.getSignature().getName() + " 실행 후");
		System.out.println("반환값 : " + result);
	}
	
	@AfterThrowing(value = "execution(* com.spring.service.AopService.afterThrowing(..))",
			throwing = "e")
	public void logAfterThrowing(JoinPoint jp, Throwable e) {
		System.out.println(jp.getSignature().getName() + " 실행 중 예외 발생 후");
		System.out.println("예외 : " + e.getClass());
	}
	
	@Around(value = "execution(* com.spring.service.AopService.around(..))")
	public void logAround(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println(pjp.getSignature().getName() + " 실행 전");
		pjp.proceed();  // JoinPoint 실행
		System.out.println(pjp.getSignature().getName() + " 실행 후");
	}
	
}
