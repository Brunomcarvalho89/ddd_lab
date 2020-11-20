package com.resow.emailsender.domain.describer;

import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class EmailWithAttachmentDescriptorTest {

    @Test
    public void testConstructor() {

        String toStr = "to";
        String fromStr = "from";
        String subjectStr = "subject";
        String contentStr = "content";

        EmailWithAttachmentDescriptor descriptor = new EmailWithAttachmentDescriptor(toStr, fromStr, subjectStr, contentStr, Arrays.asList("caminho"));

        Assertions.assertEquals(toStr, descriptor.getTo());
        Assertions.assertEquals(fromStr, descriptor.getFrom());
        Assertions.assertEquals(subjectStr, descriptor.getSubject());
        Assertions.assertEquals(contentStr, descriptor.getContent());
        Assertions.assertEquals(1, descriptor.getListPathAttachment().size());
    }

    @Test
    public void testSettersAndGetters() {

        String toStr = "to";
        String fromStr = "from";
        String subjectStr = "subject";
        String contentStr = "content";

        EmailWithAttachmentDescriptor descriptor = new EmailWithAttachmentDescriptor("", "", "", "", Arrays.asList(""));

        descriptor.setTo(toStr);
        descriptor.setFrom(fromStr);
        descriptor.setSubject(subjectStr);
        descriptor.setContent(contentStr);
        descriptor.setListPathAttachment(Arrays.asList("caminho"));

        Assertions.assertEquals(toStr, descriptor.getTo());
        Assertions.assertEquals(fromStr, descriptor.getFrom());
        Assertions.assertEquals(subjectStr, descriptor.getSubject());
        Assertions.assertEquals(contentStr, descriptor.getContent());
        Assertions.assertEquals(1, descriptor.getListPathAttachment().size());
    }
}
