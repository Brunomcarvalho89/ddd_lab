package com.resow.emailqueue.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class SimpleContentTest {

    @Test
    public void testConstructor() {

        String content = "content";

        SimpleContent simpleContent = new SimpleContent(content);

        Assertions.assertEquals(content, simpleContent.getContent());
    }

}
