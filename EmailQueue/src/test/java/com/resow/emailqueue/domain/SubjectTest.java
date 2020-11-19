package com.resow.emailqueue.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class SubjectTest {

    @Test
    public void testConstructor() {

        String subject = "subject";

        Subject simpleContent = new Subject(subject);

        Assertions.assertEquals(subject, simpleContent.getSubject());
    }

}
