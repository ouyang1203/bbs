package com.pccw.cloud.zuul.config;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
/**
 * 定义日志切面
 * */
@Aspect
@Component
public class LogAop {
	private Logger log_ = LoggerFactory.getLogger(LogAop.class);
	
	@Pointcut("execution(public * com.pccw.cloud..*.*(..))")
	public void webLogPointCut() {}
	
	@Before("webLogPointCut()")  
	public void deBefore(JoinPoint joinPoint) throws Throwable {  
		ServletRequestAttributes attributes = (ServletRequestAttributes) 
				RequestContextHolder.getRequestAttributes(); 
		if(attributes!=null) {
			HttpServletRequest request = attributes.getRequest();  
			log_.info("URL is {}",request.getRequestURL().toString());
			log_.info("HTTP_METHOD  is {}",request.getMethod());
			log_.info("IP is {}",request.getRemoteAddr());
			log_.info("CLASS_METHOD  is {}",joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
			log_.info("ARGS  is {}",Arrays.toString(joinPoint.getArgs()));
		}
	}
	
	@AfterReturning(returning = "ret", pointcut = "webLogPointCut()")  
	public void doAfterReturning(Object ret) throws Throwable {  
		log_.info("return is {}",ret);
	}
	//后置异常通知  
	@AfterThrowing("webLogPointCut()")  
	public void throwss(JoinPoint jp){  
		log_.info("method exception");
	}
	//后置最终通知,final增强，不管是抛出异常或者正常退出都会执行  
	@After("webLogPointCut()")  
	public void after(JoinPoint jp){  
		log_.info("method success");
	}
	//环绕通知,环绕增强，相当于MethodInterceptor  
	@Around("webLogPointCut()")  
	public Object arround(ProceedingJoinPoint pjp) {  
		log_.info("method Around start.....");
		try {
			Object o =  pjp.proceed();  
			log_.info("method Around end result is {}",o);
			return o;
		} catch (Throwable e) {
			e.printStackTrace();
			return null;  
		}
	}
	
}
