package com.resow.emailqueue.domain.describer;

import java.util.List;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class EmailWithAttachmentDescriptor extends EmailDescriptor {

    private List<String> listPathAttachment;

    public EmailWithAttachmentDescriptor(String to, String from, String subject, String content, List<String> listPathAttachment) {
        super(to, from, subject, content);
        this.listPathAttachment = listPathAttachment;
    }

    public List<String> getListPathAttachment() {
        return listPathAttachment;
    }

    public void setListPathAttachment(List<String> listPathAttachment) {
        this.listPathAttachment = listPathAttachment;
    }

}
