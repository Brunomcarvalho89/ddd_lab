package com.resow.emailqueue.domain;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class Subject {

    private String subject;

    public Subject(String subject) {
        this.subject = subject;
    }

    protected String getSubject() {
        return subject;
    }

}
