package com.resow.emailqueue.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class ToTest {

    @Test
    public void testConstructor() {

        String toStr = "to@teste.com";

        To simpleContent = new To(toStr);

        Assertions.assertEquals(toStr, simpleContent.getTo());
    }
}
