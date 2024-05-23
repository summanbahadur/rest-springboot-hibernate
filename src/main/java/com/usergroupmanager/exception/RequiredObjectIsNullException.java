package com.usergroupmanager.exception;
public class RequiredObjectIsNullException extends RuntimeException {

    public RequiredObjectIsNullException(String ex) {
        super(ex);
    }
    public RequiredObjectIsNullException() {
        super("It is not allowed to persist a null object!");
    }
}
