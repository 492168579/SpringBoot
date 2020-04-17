package com.bjbt.monitoring.kafka.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.bjbt.monitoring.kafka.util.JsonUtils;
import com.bjbt.monitoring.kafka.util.TimeUtil;


@Aspect
@Component
public class OpenLogtorAspect {
	private static Logger logger = LoggerFactory.getLogger(OpenLogtorAspect.class);

	@Around("execution(* com.rms.kafka.controller.*.*(..)) && @annotation(operLog)")
	public Object doAroundMethod(ProceedingJoinPoint pjd, OpenLog operLog) throws Throwable {
		long startTime = System.currentTimeMillis();// 开始时间
		logger.info("{}:接口开始调用,调用时间:{}", operLog.operationName(), TimeUtil.getCurrentTime());
		Object[] params = pjd.getArgs();// 获取请求参数
		logger.info("请求参数为:");
		for (Object param : params) {
			logger.info(JsonUtils.object2Json(param));
		}
		Object result = pjd.proceed();
		logger.info("返回参数为:{}", JsonUtils.object2Json(result));
		long endTime = System.currentTimeMillis();// 结束时间
		long curTime = (endTime - startTime) / 1000;
		logger.info("{}:接口调用结束,耗时{}ms!", operLog.operationName(), curTime);
		return result;
	}

}