package com.resow.emailqueue.domain.describer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class EmailDescriptorTest {

    @Test
    public void testConstructor() {

        String toStr = "to";
        String fromStr = "from";
        String subjectStr = "subject";
        String contentStr = "content";

        EmailDescriptor descriptor = new EmailDescriptor(toStr, fromStr, subjectStr, contentStr);

        Assertions.assertEquals(toStr, descriptor.getTo());
        Assertions.assertEquals(fromStr, descriptor.getFrom());
        Assertions.assertEquals(subjectStr, descriptor.getSubject());
        Assertions.assertEquals(contentStr, descriptor.getContent());
    }

    @Test
    public void testSettersAndGetters() {

        String toStr = "to";
        String fromStr = "from";
        String subjectStr = "subject";
        String contentStr = "content";

        EmailDescriptor descriptor = new EmailDescriptor("", "", "", "");

        descriptor.setTo(toStr);
        descriptor.setFrom(fromStr);
        descriptor.setSubject(subjectStr);
        descriptor.setContent(contentStr);

        Assertions.assertEquals(toStr, descriptor.getTo());
        Assertions.assertEquals(fromStr, descriptor.getFrom());
        Assertions.assertEquals(subjectStr, descriptor.getSubject());
        Assertions.assertEquals(contentStr, descriptor.getContent());
    }

}
