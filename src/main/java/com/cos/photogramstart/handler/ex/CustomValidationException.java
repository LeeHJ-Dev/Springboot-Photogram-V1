package com.cos.photogramstart.handler.ex;

import java.util.Map;

public class CustomValidationException extends RuntimeException{
    //객체를 구분할 때
    private static final long serialVersionUID = 1L;
    private Map<String,String> errorMap;

    //생성자
    public CustomValidationException(String message, Map<String,String> errorMsg){
        super(message);
        this.errorMap = errorMsg;
    }
    //Error Map return
    public Map<String,String> getErrorMap(){
        return this.errorMap;
    }
}
