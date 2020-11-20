package com.resow.emailsender.domain.service;

import com.resow.emailsender.domain.Email;
import com.resow.emailsender.domain.EmailWithAttachment;
import com.resow.emailsender.domain.describer.EmailDescriptor;
import com.resow.emailsender.domain.describer.EmailWithAttachmentDescriptor;
import com.resow.emailsender.domain.service.impl.EmailFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class EmailFactoryTest {

    @Test
    public void testSimple() {

        String toStr = "to";
        String fromStr = "from";
        String subjectStr = "subject";
        String contentStr = "content";

        EmailFactory emailFactory = new EmailFactory();
        Email email = emailFactory.simple(toStr, fromStr, subjectStr, contentStr);

        EmailDescriptor descriptor = email.descriptor();

        Assertions.assertEquals(toStr, descriptor.getTo());
        Assertions.assertEquals(fromStr, descriptor.getFrom());
        Assertions.assertEquals(subjectStr, descriptor.getSubject());
        Assertions.assertEquals(contentStr, descriptor.getContent());

    }

    @Test
    public void testWithAttachment() {

        String toStr = "to";
        String fromStr = "from";
        String subjectStr = "subject";
        String contentStr = "content";
        String path = "path";

        EmailFactory emailFactory = new EmailFactory();
        EmailWithAttachment email = emailFactory.withAttachment(toStr, fromStr, subjectStr, contentStr, path);

        EmailWithAttachmentDescriptor descriptor = email.descriptor();

        Assertions.assertEquals(toStr, descriptor.getTo());
        Assertions.assertEquals(fromStr, descriptor.getFrom());
        Assertions.assertEquals(subjectStr, descriptor.getSubject());
        Assertions.assertEquals(contentStr, descriptor.getContent());
        Assertions.assertEquals(1, descriptor.getListPathAttachment().size());
    }
}
