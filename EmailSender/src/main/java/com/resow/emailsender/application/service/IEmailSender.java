package com.resow.emailsender.application.service;

import com.resow.emailsender.application.exception.EmailNotSendException;
import com.resow.emailsender.domain.Email;
import com.resow.emailsender.domain.EmailWithAttachment;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public interface IEmailSender {

    void sendSimpleMessage(Email email) throws EmailNotSendException;
    
    void sendMessageWithAttachment(EmailWithAttachment emailAttachment) throws EmailNotSendException;

}
