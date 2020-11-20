package com.resow.emailsender.domain;

import com.resow.emailsender.domain.Attachment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class AttachmentTest {

    @Test
    public void testConstructor() {

        String pathAttachment = "pathAttachment";

        Attachment attachment = new Attachment(pathAttachment);

        Assertions.assertEquals(pathAttachment, attachment.pathAttachment());
    }
}
