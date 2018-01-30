package org.apache.dynamic.validator.commons.exception;

public class CommonsException extends Exception {

    private static final long serialVersionUID = 3695674381774311248L;

    public CommonsException(String message) {
        super(message);
    }

    public CommonsException(Throwable throwable) {
        super(throwable);
    }

    public CommonsException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
