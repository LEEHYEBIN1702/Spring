package com.company.yedam.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AfterAdvice {
	Logger logger = LoggerFactory.getLogger(AfterAdvice.class);
	@Pointcut("execution(* com.company.yedam..*Impl.*(..))")
	public void allpointcut() {}

	@AfterReturning(pointcut = "LogAdvice.allpointcut()",
			        returning = "returnObj" )

	public void afterLog(JoinPoint jp, Object returnObj) {
		String name = jp.getSignature().getName();
		// 리턴
		logger.info("[공통 로그] after \n" + name + (returnObj != null ? returnObj : "no return"));
	}
}
