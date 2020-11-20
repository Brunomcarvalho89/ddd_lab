package com.resow.emailsender.domain.service.impl;

import com.resow.emailsender.domain.Attachment;
import com.resow.emailsender.domain.Email;
import com.resow.emailsender.domain.EmailWithAttachment;
import com.resow.emailsender.domain.From;
import com.resow.emailsender.domain.SimpleContent;
import com.resow.emailsender.domain.Subject;
import com.resow.emailsender.domain.To;
import com.resow.emailsender.domain.service.IEmailFactory;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class EmailFactory implements IEmailFactory {

    @Override
    public Email simple(String sendTo, String fromEmail, String subjectEmail, String content) {

        To to = new To(sendTo);
        From from = new From(fromEmail);
        Subject subject = new Subject(subjectEmail);

        SimpleContent simpleContent = new SimpleContent(content);

        Email email = new Email(to, from, subject, simpleContent);

        return email;

    }

    @Override
    public EmailWithAttachment withAttachment(String sendTo, String fromEmail, String subjectEmail, String content, String... pathAttachment) {

        To to = new To(sendTo);
        From from = new From(fromEmail);
        Subject subject = new Subject(subjectEmail);

        SimpleContent simpleContent = new SimpleContent(content);

        List<Attachment> attachments = Stream.of(pathAttachment).map((String t) -> new Attachment(t)).collect(Collectors.toList());

        EmailWithAttachment email = new EmailWithAttachment(to, from, subject, simpleContent, attachments);

        return email;

    }

}
