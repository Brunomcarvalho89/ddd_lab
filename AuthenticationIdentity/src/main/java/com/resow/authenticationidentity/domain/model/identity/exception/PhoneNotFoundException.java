package com.resow.authenticationidentity.domain.model.identity.exception;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class PhoneNotFoundException extends RuntimeException {

    public PhoneNotFoundException(String message) {
        super(message);
    }

    public PhoneNotFoundException() {
    }

}
