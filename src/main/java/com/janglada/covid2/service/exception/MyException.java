package com.janglada.covid2.service.exception;

public class MyException extends Throwable {
    public MyException() {
        super();
    }

    public MyException(String s) {
        super(s);
    }

    public MyException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
