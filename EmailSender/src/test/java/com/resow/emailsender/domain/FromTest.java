package com.resow.emailsender.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class FromTest {

    @Test
    public void testConstructor() {

        String fromStr = "from@teste.com";

        From from = new From(fromStr);

        Assertions.assertEquals(fromStr, from.getFrom());
    }

}
