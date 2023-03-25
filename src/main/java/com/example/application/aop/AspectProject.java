package com.example.application.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class AspectProject {

    @Pointcut("execution(public * com.example.application.http.controller.*RestController.findAll())")
    public void findByAllRestControllerMethods() {
    }

    @Pointcut("execution(public * com.example.application.service.*Service.findById(*))")
    public void findByIdServiceMethods() {
    }

    @Before(value = "findByAllRestControllerMethods() " +
                    "&& target(controller)",
            argNames = "controller")
    public void addBeforeLogging(Object controller) {
        log.info("FindAll method is invoked in class {}", controller);
    }


    @Around(value = "findByIdServiceMethods() " +
                    "&& target(service) " +
                    "&& args(id)",
            argNames = "joinPoint,service,id")
    public Object addAroundLogging(ProceedingJoinPoint joinPoint, Object service, Object id) {
        log.info("Before - findById method is invoked in class {}, with id {}", service, id);
        try {
            Object proceed = joinPoint.proceed();
            log.info("After returning - findById method is invoked in class {}, with id {}", service, id);
            return proceed;
        } catch (Throwable e) {
            log.info("After throwing - findById method is invoked in class {}, with exception {}", service, e.getClass());
            throw new RuntimeException(e);
        } finally {
            log.info("After - findById method is invoked in class {}, with id {}", service, id);
        }
    }
}
