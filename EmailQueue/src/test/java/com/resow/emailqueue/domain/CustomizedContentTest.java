package com.resow.emailqueue.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class CustomizedContentTest {

    @Test
    public void testConstructor() {

        String content = "content";

        CustomizedContent customizedContent = new CustomizedContent(content);

        Assertions.assertEquals(content, customizedContent.getContent());
    }

}
