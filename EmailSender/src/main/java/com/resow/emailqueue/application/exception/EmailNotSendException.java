package com.resow.emailqueue.application.exception;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class EmailNotSendException extends Exception {

    private final String message;

    public EmailNotSendException(String message) {
        this.message = message;
    }

}
