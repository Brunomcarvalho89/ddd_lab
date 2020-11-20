package com.resow.emailsender.domain;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class Attachment {

    private String pathAttachment;

    public Attachment(String pathAttachment) {
        this.pathAttachment = pathAttachment;
    }

    protected String pathAttachment() {
        return this.pathAttachment;
    }
}
