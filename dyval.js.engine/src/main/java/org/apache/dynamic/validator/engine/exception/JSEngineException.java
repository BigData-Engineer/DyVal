package org.apache.dynamic.validator.engine.exception;

public class JSEngineException extends Exception {

    private static final long serialVersionUID = 461511691326868580L;
    
    public JSEngineException(String message) {
        super(message);
    }

    public JSEngineException(Throwable throwable) {
        super(throwable);
    }

    public JSEngineException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
