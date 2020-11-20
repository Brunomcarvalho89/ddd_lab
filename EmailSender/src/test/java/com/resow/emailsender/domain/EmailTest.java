package com.resow.emailsender.domain;

import com.resow.emailsender.domain.describer.EmailDescriptor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class EmailTest {

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

        Email email = new Email(to, from, subject, simpleContent);

        EmailDescriptor descriptor = email.descriptor();

        Assertions.assertEquals(toStr, descriptor.getTo());
        Assertions.assertEquals(fromStr, descriptor.getFrom());
        Assertions.assertEquals(subjectStr, descriptor.getSubject());
        Assertions.assertEquals(contentStr, descriptor.getContent());
    }

}
