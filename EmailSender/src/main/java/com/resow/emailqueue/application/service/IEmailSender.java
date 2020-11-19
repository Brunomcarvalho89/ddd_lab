package com.resow.emailqueue.application.service;

import com.resow.emailqueue.application.exception.EmailNotSendException;
import com.resow.emailqueue.domain.Email;
import com.resow.emailqueue.domain.EmailWithAttachment;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public interface IEmailSender {

    void sendSimpleMessage(Email email) throws EmailNotSendException;
    
    void sendMessageWithAttachment(EmailWithAttachment emailAttachment) throws EmailNotSendException;

}
