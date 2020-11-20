package com.resow.emailsender.domain;

import com.resow.common.domain.model.descriptor.Describable;
import com.resow.emailsender.domain.describer.EmailDescriptor;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class Email implements Describable<EmailDescriptor> {

    protected To to;
    protected From from;
    protected Subject subject;
    protected Content content;

    public Email(To to, From from, Subject subject, Content content) {
        this.to = to;
        this.from = from;
        this.subject = subject;
        this.content = content;
    }

    @Override
    public EmailDescriptor descriptor() {
        return new EmailDescriptor(
                this.to.getTo(),
                this.from.getFrom(),
                this.subject.getSubject(),
                this.content.getContent());
    }

}
