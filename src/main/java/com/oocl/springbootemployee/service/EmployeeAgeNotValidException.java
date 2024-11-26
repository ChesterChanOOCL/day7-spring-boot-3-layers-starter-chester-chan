package com.oocl.springbootemployee.service;

public class EmployeeAgeNotValidException extends Throwable{
    public EmployeeAgeNotValidException(String message) {
        super(message);
    }
}
