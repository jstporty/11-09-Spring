package kr.kwangan2.aop.advice;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect
@Log4j
@Component
public class LogAdvice {

	/*
	 * AspectJ Expression Language (aspectJ EL) => Advice가 적용될 joinPoint에 대한 설정 :
	 * all, ..:0개 이상(패키지에 사용할때는 하위패키지 모두) 
	 * []:생략가능 
	 * ||: or 
	 * &&: and 
	 * (): 인자없음 
	 * (*):인자 2개
	 * (..): 인자 0개 이상
	 */

	@Before("execution(* kr.kwangan2.aop.service.SampleService*.*(..))")
	public void logBefor() {

		log.info("===============");
	}

	@Before("execution(* kr.kwangan2.aop.service.SampleService*.doAdd(String,String))&&args(str1,str2)")
	public void logParam(String str1, String str2) {
		log.info("str1===============>" + str1);
		log.info("str2===============>" + str2);
	}

	@AfterThrowing(pointcut = "execution(* kr.kwangan2.aop.service.SampleService*.*(..))", throwing = "exception")
	public void logException(Exception exception) {
		log.info("발생한 예외====>" + exception);
	}

	@Around("execution(* kr.kwangan2.aop.service.SampleService*.*(..))")
	public Object logTime(ProceedingJoinPoint pjp) {
		long start = System.currentTimeMillis();

		log.info("타겟====>" + pjp.getTarget());
		log.info("인자(parameter)====>" + Arrays.toString(pjp.getArgs()));

		Object result = null;

		try {
			result = pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();

		log.info("메서드의 수행 소요시간======>" + (end - start));

		return result;
	}
}// class
