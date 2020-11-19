package com.resow.authenticationidentity.domain.model.identity.exception;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class EmailNotFoundException extends RuntimeException {

    public EmailNotFoundException(String message) {
        super(message);
    }

    public EmailNotFoundException() {
    }

}
