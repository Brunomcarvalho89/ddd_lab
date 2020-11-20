package com.resow.emailsender.domain;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class CustomizedContent extends Content {

    private String content;

    public CustomizedContent(String content) {
        this.content = content;
    }

    @Override
    protected String getContent() {
        return this.content;
    }

}
