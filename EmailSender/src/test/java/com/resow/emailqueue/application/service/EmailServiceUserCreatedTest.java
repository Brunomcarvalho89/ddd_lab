package com.resow.emailqueue.application.service;

import com.resow.emailqueue.application.dto.UserDTO;
import com.resow.emailqueue.application.exception.EmailNotSendException;
import com.resow.emailqueue.application.service.impl.EmailServiceUserCreated;
import com.resow.emailqueue.domain.Email;
import com.resow.emailqueue.domain.EmailWithAttachment;
import com.resow.emailqueue.domain.service.impl.EmailFactory;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class EmailServiceUserCreatedTest {

    @Test
    public void testSend() {

        String fullname = "fullname";
        String nickname = "nickname";

        Set<String> email = new HashSet<>();
        email.add("teste@teste.com");

        Set<String> phones = new HashSet<>();
        phones.add("992256545");

        String addressDescription = "address description";
        String addressComplement = "address complement";
        long number = 1;
        String zipCode = "26545002";
        long city = 2;

        String from = "email@teste.com";

        UserDTO userDTO = new UserDTO(fullname, nickname, email, phones, addressDescription, addressComplement, number, zipCode, city);

        EmailServiceUserCreated emailServiceUserCreated = new EmailServiceUserCreated(new EmailSender(), new EmailFactory(), from);

        Assertions.assertDoesNotThrow(() -> emailServiceUserCreated.send(userDTO));
    }

    public class EmailSender implements IEmailSender {

        @Override
        public void sendSimpleMessage(Email email) throws EmailNotSendException {
        }

        @Override
        public void sendMessageWithAttachment(EmailWithAttachment emailWithAttachment) throws EmailNotSendException {
        }
    }
}
