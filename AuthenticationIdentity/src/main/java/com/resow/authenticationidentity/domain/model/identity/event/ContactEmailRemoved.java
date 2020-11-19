package com.resow.authenticationidentity.domain.model.identity.event;

import com.resow.authenticationidentity.domain.model.identity.EmailAddress;
import java.util.List;
import com.resow.common.event.BaseEvent;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class ContactEmailRemoved implements BaseEvent {

    private String userUUID;
    private List<EmailAddress> emailListRemoved;

    public ContactEmailRemoved(String userUUID, List<EmailAddress> emailListRemoved) {
        this.userUUID = userUUID;
        this.emailListRemoved = emailListRemoved;
    }

    public String getUserUUID() {
        return userUUID;
    }

    public Boolean contains(EmailAddress emailAddress) {
        return emailListRemoved.contains(emailAddress);
    }

}
