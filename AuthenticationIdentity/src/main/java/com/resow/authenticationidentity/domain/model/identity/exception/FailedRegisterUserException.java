package com.resow.authenticationidentity.domain.model.identity.exception;

/**
 *
 * @author home
 */
public class FailedRegisterUserException extends Exception {

    public FailedRegisterUserException(String message) {
        super(message);
    }

    public FailedRegisterUserException() {
        this("");
    }

}
