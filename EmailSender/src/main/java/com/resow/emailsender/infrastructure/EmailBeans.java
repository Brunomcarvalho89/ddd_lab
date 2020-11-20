package com.resow.emailsender.infrastructure;

import com.resow.emailsender.domain.service.IEmailFactory;
import com.resow.emailsender.domain.service.impl.EmailFactory;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
@Configuration
public class EmailBeans {

    @Bean
    public IEmailFactory emailFactory() {
        return new EmailFactory();
    }

    @Value("${email.from}")
    private String from;

    @Bean
    public JavaMailSender getJavaMailSender() {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername(this.from);
        mailSender.setPassword("");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
