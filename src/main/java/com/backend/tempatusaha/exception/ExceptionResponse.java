package com.backend.tempatusaha.exception;

public class ExceptionResponse extends RuntimeException {
    public ExceptionResponse() {
        super();
    }

    public ExceptionResponse(String message) {
        super(message);
    }
}
