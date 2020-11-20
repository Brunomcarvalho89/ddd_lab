package com.resow.emailqueue.application.exception;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class EmailNotSendException extends Exception {

    public EmailNotSendException(String message) {
        super(message);
    }

}
