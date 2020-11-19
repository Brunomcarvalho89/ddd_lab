package com.resow.emailqueue.domain;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class SimpleContent extends Content {

    private String content;

    public SimpleContent(String content) {
        this.content = content;
    }

    @Override
    protected String getContent() {
        return this.content;
    }

}
