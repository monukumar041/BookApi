package com.Rest.API.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler
    public ResponseEntity<ErrorObject>handleResourceNotFoundException(ResourceNotFoundException ex)
    {
        ErrorObject eObject=new ErrorObject();
        eObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        eObject.setMessage(ex.getMessage());
        eObject.setTimestamped(System.currentTimeMillis());
        return new ResponseEntity<ErrorObject>(eObject,HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler
    public ResponseEntity<ErrorObject>handleNoDataException(NoDataFoundException nd)
    {
        ErrorObject eObject=new ErrorObject();
        eObject.setStatusCode(HttpStatus.NO_CONTENT.value());
        eObject.setMessage(nd.getMessage());
        eObject.setTimestamped(System.currentTimeMillis());
        return new ResponseEntity<ErrorObject>(eObject,HttpStatus.OK);

    }
}
