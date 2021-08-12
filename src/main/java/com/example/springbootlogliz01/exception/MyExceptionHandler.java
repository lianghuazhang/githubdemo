package com.example.springbootlogliz01.exception;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler()
    public String handleException(){
        try {
            System.out.println("");
            int a =1;

        } finally {

        }
        return null;
    }

}
