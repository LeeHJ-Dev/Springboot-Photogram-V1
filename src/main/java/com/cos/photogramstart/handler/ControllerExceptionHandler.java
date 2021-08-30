package com.cos.photogramstart.handler;

import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.util.Script;
import com.cos.photogramstart.web.dto.CMRespDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {
/*
    @ExceptionHandler(value = {CustomValidationException.class})
    public CMRespDto<?> validationException(CustomValidationException e){
        System.out.println("ControllerExceptionHandler.validationException");
        return new CMRespDto<Map<String,String>>(-1,e.getMessage(), e.getErrorMap());
    }
*/

    @ExceptionHandler(value = {CustomValidationException.class})
    public String validationException(CustomValidationException e){
        System.out.println("ControllerExceptionHandler.validationException");
        return Script.back(e.getErrorMap().toString());
    }

    @ExceptionHandler(value = {CustomValidationApiException.class})
    public ResponseEntity<CMRespDto<?>> validationException(CustomValidationApiException e){
        System.out.println("ControllerExceptionHandler.validationException");
        return new ResponseEntity<>(new CMRespDto<>(-1,e.getMessage(),e.getErrorMap()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {CustomApiException.class})
    public ResponseEntity<CMRespDto<?>> apiException(CustomApiException e){
        System.out.println("ControllerExceptionHandler.apiException");
        return new ResponseEntity<>(new CMRespDto<>(-1,e.getMessage(),null),HttpStatus.BAD_REQUEST);
    }

}