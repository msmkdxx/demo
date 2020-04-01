package com.example.demo.exception;

import com.example.demo.enums.IErrorCode;
import lombok.Data;

import java.io.Serializable;

@Data
public class UsersException extends RuntimeException implements Serializable {

    private IErrorCode iErrorCode;
    private String errorCode;
    private String errorMessage;

    public UsersException(IErrorCode iErrorCode) {
        super(iErrorCode.getErrorMessage());
        this.errorCode = iErrorCode.getErrorCode();
    }

    public UsersException(String code,String errorMessage) {
        super(errorMessage);
        this.errorCode = code;
    }

}
