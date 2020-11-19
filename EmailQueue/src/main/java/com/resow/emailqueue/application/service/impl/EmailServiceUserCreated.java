package com.resow.emailqueue.application.service.impl;

import com.resow.emailqueue.application.dto.UserDTO;
import com.resow.emailqueue.application.exception.EmailNotSendException;
import com.resow.emailqueue.application.service.EmailService;
import com.resow.emailqueue.domain.Email;
import com.resow.emailqueue.domain.service.IEmailFactory;
import com.resow.emailqueue.application.service.IEmailSender;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class EmailServiceUserCreated implements EmailService {

    private final IEmailSender emailSender;
    private final IEmailFactory emailFactory;
    private final String from;

    public EmailServiceUserCreated(IEmailSender emailSender, IEmailFactory emailFactory, String from) {
        this.emailSender = emailSender;
        this.emailFactory = emailFactory;
        this.from = from;
    }

    public void send(UserDTO userDTO) throws EmailNotSendException {

        String emailAddress = userDTO.getEmail().stream().findFirst().get();

        Email email = this.emailFactory
                .simple(
                        emailAddress,
                        this.from,
                        "User created",
                        "Hello, user " + userDTO.getNickname() + " successfully created.");

        emailSender.sendSimpleMessage(email);
    }

}
