package com.resow.emailsender.domain;

import com.resow.emailsender.domain.describer.EmailWithAttachmentDescriptor;
import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class EmailWithAttachmentTest {

    @Test
    public void testConstructor() {

        String toStr = "to";
        String fromStr = "from";
        String subjectStr = "subject";
        String contentStr = "content";

        To to = new To(toStr);
        From from = new From(fromStr);
        Subject subject = new Subject(subjectStr);
        SimpleContent simpleContent = new SimpleContent(contentStr);

        EmailWithAttachment email = new EmailWithAttachment(to, from, subject, simpleContent, new ArrayList<Attachment>() {
            {
                add(new Attachment("caminho"));
            }
        });

        EmailWithAttachmentDescriptor descriptor = email.descriptor();

        Assertions.assertEquals(toStr, descriptor.getTo());
        Assertions.assertEquals(fromStr, descriptor.getFrom());
        Assertions.assertEquals(subjectStr, descriptor.getSubject());
        Assertions.assertEquals(contentStr, descriptor.getContent());
        Assertions.assertEquals(1, descriptor.getListPathAttachment().size());
    }

}
