package com.example.springbootlogliz01.exception;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler {

    public String handleException(){
        try {
            System.out.println("");

            int a =1;
            int b =1;
            int c = 3;
            int d = 5;
        } finally {

        }
        return null;
    }

}
