package com.Rest.API.exception;

public class NoDataFoundException extends  RuntimeException{
    public NoDataFoundException(String message)
    {
        super(message);
    }
}
