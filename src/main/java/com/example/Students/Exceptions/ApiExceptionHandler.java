package com.example.Students.Exceptions;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.xml.bind.ValidationException;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.naming.AuthenticationException;
import javax.naming.OperationNotSupportedException;

@Order(Ordered.HIGHEST_PRECEDENCE + 99)
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {


    private ResponseEntity<Object> buildResponseEntity(ApiException apiException){
        return new ResponseEntity<>(apiException.getMessage(), apiException.getStatus());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex){
        ApiException apiError = new ApiException(HttpStatus.NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        apiError.setCode(apiError.getStatus().value());

        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(OperationNotSupportedException.class)
    protected ResponseEntity<Object> operationNotSupportedException(OperationNotSupportedException ex){

        ApiException apiError = new ApiException(HttpStatus.BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        apiError.setCode(apiError.getStatus().value());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(EntityExistsException.class)
    protected ResponseEntity<Object> handleEntityExistsException(EntityExistsException ex){
        ApiException apiError = new ApiException(HttpStatus.CONFLICT);
        apiError.setMessage(ex.getMessage());
        apiError.setCode(apiError.getStatus().value());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(AuthenticationException.class)
    protected ResponseEntity<Object> handleNotAuthenticatedException(AuthenticationException ex){
        ApiException apiError = new ApiException(HttpStatus.UNAUTHORIZED);
        apiError.setMessage(ex.getMessage());
        apiError.setCode(apiError.getStatus().value());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ValidationException.class)
    protected ResponseEntity<Object> handleValidationException(ValidationException ex){
        ApiException apiError = new ApiException(HttpStatus.BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        apiError.setCode(apiError.getStatus().value());
        return buildResponseEntity(apiError);
    }

    
}
