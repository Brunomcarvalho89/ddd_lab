package com.resow.authenticationidentity.domain.model.identity.exception;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }

}
