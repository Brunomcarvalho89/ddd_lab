package com.resow.emailsender.application.exception;

import com.resow.emailsender.application.exception.EmailNotSendException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class EmailNotSendExceptionTest {

    @Test
    public void testConstructor() {
        String message = "message";
        EmailNotSendException emailNotSendException = new EmailNotSendException(message);
        Assertions.assertEquals(message, emailNotSendException.getMessage());
    }

}
