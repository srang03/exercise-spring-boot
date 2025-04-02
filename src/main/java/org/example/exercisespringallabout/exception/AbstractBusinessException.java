package org.example.exercisespringallabout.exception;

public abstract class AbstractBusinessException extends  RuntimeException {
    public abstract String getErrorCode();
    public AbstractBusinessException(String message) {
        super(message);
    }
}
