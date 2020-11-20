package com.resow.emailsender.domain;

import com.resow.emailsender.domain.describer.EmailWithAttachmentDescriptor;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class EmailWithAttachment extends Email {

    private List<Attachment> attachments;

    public EmailWithAttachment(To to, From from, Subject subject, Content content, List<Attachment> attachment) {
        super(to, from, subject, content);

        this.attachments = attachment;
    }

    @Override
    public EmailWithAttachmentDescriptor descriptor() {

        List<String> pathAttachments = this.attachments
                .stream()
                .map((Attachment t) -> t.pathAttachment())
                .collect(Collectors.toList());

        return new EmailWithAttachmentDescriptor(
                this.to.getTo(),
                this.from.getFrom(),
                this.subject.getSubject(),
                this.content.getContent(),
                pathAttachments);
    }

}
