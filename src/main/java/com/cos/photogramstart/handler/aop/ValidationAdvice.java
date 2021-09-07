package com.cos.photogramstart.handler.aop;

import com.cos.photogramstart.handler.ex.CustomValidationException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

//@Component
//@Aspect
public class ValidationAdvice {
/*
    @Around(value = "execution(* com.cos.photogramstart.web.api.*Controller.*(..))")
    public Object apiAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("ValidationAdvice.apiAdvice API==========================");

        for (Object arg : proceedingJoinPoint.getArgs()) {
            if(arg instanceof BindingResult){
                BindingResult bindingResult = (BindingResult) arg;
                if(bindingResult.hasErrors()){
                    Map<String,String> errorMap = new HashMap<>();
                    for(FieldError fieldError: bindingResult.getFieldErrors()){
                        errorMap.put(fieldError.getField(),fieldError.getDefaultMessage());
                    }
                    throw new CustomValidationException("유효성 검사 실패함",errorMap);
                }
            }
        }
        return proceedingJoinPoint.proceed();
    }

    @Around(value = "execution(* com.cos.photogramstart.web.*Controller.*(..))")
    public Object advice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("ValidationAdvice.advice ==========================");

        for (Object arg : proceedingJoinPoint.getArgs()) {
            if(arg instanceof BindingResult){
                BindingResult bindingResult = (BindingResult) arg;
                if(bindingResult.hasErrors()){
                    Map<String,String> errorMap = new HashMap<>();
                    for(FieldError fieldError: bindingResult.getFieldErrors()){
                        errorMap.put(fieldError.getField(),fieldError.getDefaultMessage());
                    }
                    throw new CustomValidationException("유효성 검사 실패함",errorMap);
                }
            }
        }
        return proceedingJoinPoint.proceed();
    }

 */
}