package com.vmware.bookmanagement.advice;

import java.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Aspect for logging execution of controller, service , repository Spring components.
 */
@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Pointcut that matches all repositories, services and Web REST endpoints.
     */
    @Pointcut("within(@org.springframework.stereotype.Repository *)" +
            " || within(@org.springframework.stereotype.Service *)" +
            " || within(@org.springframework.web.bind.annotation.RestController *)")
    public void beanPointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    /**
     * Pointcut that matches bean in application package.
     */
    @Pointcut("within(com.vmware.bookmanagement.repository..*)" +
            " || within(com.vmware.bookmanagement.service..*)" +
            " || within(com.vmware.bookmanagement.controller..*)")
    public void apiPackagePointcut() {
    }

    @AfterThrowing(pointcut = "apiPackagePointcut() && beanPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        logger.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL");
    }

    /**
     * Advice that logs when a method is entered and exited.
     *
     * @param joinPoint join point for advice
     * @return result
     * @throws Throwable throws IllegalArgumentException
     */
    @Around("beanPointcut() && apiPackagePointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        if (logger.isDebugEnabled()) {
            logger.debug("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        }
        try {
            Object result = joinPoint.proceed();
            if (logger.isDebugEnabled()) {
                logger.debug("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
                        joinPoint.getSignature().getName(), result);
            }
            return result;
        } catch (IllegalArgumentException e) {
            logger.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
                    joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
            throw e;
        }
    }
}
