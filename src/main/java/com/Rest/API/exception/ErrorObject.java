package com.Rest.API.exception;

public class ErrorObject {
    private int statusCode;
    private String message;
    private long timestamped;

    public ErrorObject() {
    }

    public ErrorObject(int statusCode, String message, long timestamped) {
        this.statusCode = statusCode;
        this.message = message;
        this.timestamped = timestamped;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamped() {
        return timestamped;
    }

    public void setTimestamped(long timestamped) {
        this.timestamped = timestamped;
    }

    @Override
    public String toString() {
        return "ErrorObject{" +
                "statusCode=" + statusCode +
                ", message='" + message + '\'' +
                ", timestamped=" + timestamped +
                '}';
    }
}
