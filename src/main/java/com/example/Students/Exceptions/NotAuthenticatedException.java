package com.example.Students.Exceptions;

public class NotAuthenticatedException extends RuntimeException{
    public NotAuthenticatedException()
    {
        super();
    }

    public NotAuthenticatedException(String message)
    {
        super(message);
    }
}
