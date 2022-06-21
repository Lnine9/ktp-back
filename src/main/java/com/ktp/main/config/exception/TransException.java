package com.ktp.main.config.exception;

import lombok.Getter;
import lombok.Setter;

public class TransException extends RuntimeException{

    @Getter
    @Setter
    private TransExceptionCode exceptionCode;

    public TransException(){
        this.exceptionCode = TransExceptionCode.ERROR;
    }

    public TransException(TransExceptionCode transExceptionCode){
        this.exceptionCode = transExceptionCode;
    }

}

