package com.cos.photogramstart.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CMRespDto<T> {
    private int errorCode;
    private String message;
    private T data;
}
