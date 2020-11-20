package com.resow.emailsender.domain.service;

import com.resow.emailsender.domain.Email;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public interface IEmailFactory {

    Email simple(String sendTo, String fromEmail, String subjectEmail, String content);
    
    Email withAttachment(String sendTo, String fromEmail, String subjectEmail, String content, String... pathAttachment);
}
