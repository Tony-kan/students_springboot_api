package com.example.Students.Exceptions;

public class OperationNotAllowedException extends RuntimeException {
    public OperationNotAllowedException() {
    }

    public OperationNotAllowedException(String message) {
        super(message);
    }

    public OperationNotAllowedException(String message, Throwable cause) {
        super(message, cause);
    }

    public OperationNotAllowedException(Throwable cause) {
        super(cause);
    }
}
