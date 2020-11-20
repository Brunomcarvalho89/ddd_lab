package com.resow.emailsender.infrastructure.service;

import com.resow.emailsender.application.exception.EmailNotSendException;
import com.resow.emailsender.application.service.IEmailSender;
import com.resow.emailsender.domain.Email;
import com.resow.emailsender.domain.EmailWithAttachment;
import com.resow.emailsender.domain.describer.EmailDescriptor;
import com.resow.emailsender.domain.describer.EmailWithAttachmentDescriptor;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
@Service
public class EmailSender implements IEmailSender {

    private static final Logger LOG = Logger.getLogger(EmailSender.class.getName());

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendSimpleMessage(Email email) throws EmailNotSendException {
        try {

            EmailDescriptor emailDescriptor = email.descriptor();

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailDescriptor.getFrom());
            message.setTo(emailDescriptor.getTo());
            message.setSubject(emailDescriptor.getSubject());
            message.setText(emailDescriptor.getContent());

            emailSender.send(message);

        } catch (Exception ex) {
            EmailNotSendException emailNotSendException = new EmailNotSendException("Email cannot be sent[ " + ex.getMessage() + " ]");
            throw emailNotSendException;
        }
    }

    @Override
    public void sendMessageWithAttachment(EmailWithAttachment emailWithAttachment) throws EmailNotSendException {

        try {
            MimeMessage message = emailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            EmailWithAttachmentDescriptor descriptor = emailWithAttachment.descriptor();

            helper.setFrom(descriptor.getFrom());
            helper.setTo(descriptor.getTo());
            helper.setSubject(descriptor.getSubject());
            helper.setText(descriptor.getContent());

            descriptor.getListPathAttachment().forEach(pathAttachment -> {
                FileSystemResource file = new FileSystemResource(new File(pathAttachment));
                try {
                    helper.addAttachment("Invoice", file);
                } catch (MessagingException ex) {
                    LOG.log(Level.WARNING, "EMAIL_QUEUE: The file could not be attached.");
                }
            });

            emailSender.send(message);
        } catch (MessagingException ex) {
            EmailNotSendException emailNotSendException = new EmailNotSendException("Email cannot be sent[ " + ex.getMessage() + " ]");
            throw emailNotSendException;
        }
    }
}
