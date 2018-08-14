package com.gmail.jarmusik.kamil.DataRepoGithub.infrastructure.metrics.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.util.List;

import static java.lang.String.format;

@Aspect
class LoggingAspect {

    @Pointcut("@within(com.gmail.kamil.jarmusik.DataRepoGithub.infrastructure.metrics.logging.LogForBeanSpring)")
    void logAnnotation() {}

    @Around("logAnnotation()")
    Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("joinPoint: " + joinPoint);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();
        log(joinPoint, stopWatch, result);
        return result;
    }

    private void log(ProceedingJoinPoint joinPoint, StopWatch stopWatch, Object result) {
        String operationName = getOperationName(joinPoint);
        String timerString = createTimerString(stopWatch);
        String resultString = createResultString(result);
        String resultSizeString = createResultSizeString(result);
        if (!timerString.isEmpty() || !resultString.isEmpty()) {
            Logger logger = getLogger(joinPoint);
            logger.info("{}{} {} {}", operationName, timerString, resultSizeString, resultString);
        }
    }

    private Logger getLogger(ProceedingJoinPoint joinPoint) {
        return LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
    }

    private String createResultString(Object result) {
        return format("operation result: %s", result);
    }

    private String createResultSizeString(Object result) {
        if(result instanceof List)
            return format("[response size: %s]", ((List)result).size());
        return "[response size: 1]";
    }

    private String createTimerString(StopWatch stopWatch) {
        long millis = stopWatch.getTotalTimeMillis();
        return format(" [%d ms]", millis);
    }

    private String getOperationName(ProceedingJoinPoint joinPoint) {
        String classWithPackageName = joinPoint.getSignature().getDeclaringTypeName();
        String className = classWithPackageName.substring(classWithPackageName.lastIndexOf('.') + 1);
        return className + "." + joinPoint.getSignature().getName();
    }
}