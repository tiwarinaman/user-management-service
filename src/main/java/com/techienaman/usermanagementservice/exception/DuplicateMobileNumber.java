package com.techienaman.usermanagementservice.exception;

public class DuplicateMobileNumber extends RuntimeException {

    public DuplicateMobileNumber(String message) {
        super(message);
    }
}
