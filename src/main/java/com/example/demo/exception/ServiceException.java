package com.example.demo.exception;

import com.example.demo.enums.ErrorType;
import com.example.demo.utils.ReturnResult;
import lombok.Data;

@Data
public class ServiceException extends RuntimeException {
    private Integer code;
    private String message;

    public ServiceException(ErrorType errorType) {
        super(errorType.getMessage());
        this.code = code;
    }

    public ServiceException(Integer code,String message) {
        super(message);
        this.code = code;
    }

}
