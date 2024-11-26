package com.oocl.springbootemployee.service;

public class EmployeeNotEnoughSalaryException extends Throwable{
    public EmployeeNotEnoughSalaryException(String message) {
        super(message);
    }
}
