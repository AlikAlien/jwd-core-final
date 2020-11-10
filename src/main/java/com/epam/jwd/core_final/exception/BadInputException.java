package com.epam.jwd.core_final.exception;

public class BadInputException extends Exception{
    public BadInputException(String msg) {
        super(msg);
    }

    public BadInputException() {
        super();
    }

@Override
    public String getMessage() {
        String s = "BAD INPUT, TRY AGAIN..";
        return s;
    }
}
