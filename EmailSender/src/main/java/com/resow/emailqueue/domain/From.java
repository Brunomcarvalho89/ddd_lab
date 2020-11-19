package com.resow.emailqueue.domain;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class From {

    private String from;

    public From(String from) {
        this.from = from;
    }

    protected String getFrom() {
        return from;
    }

}
