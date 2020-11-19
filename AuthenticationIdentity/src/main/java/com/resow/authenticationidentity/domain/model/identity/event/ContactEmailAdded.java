package com.resow.authenticationidentity.domain.model.identity.event;

import com.resow.authenticationidentity.domain.model.identity.EmailAddress;
import java.util.List;
import com.resow.common.event.BaseEvent;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class ContactEmailAdded implements BaseEvent {

    private String userUUID;
    private List<EmailAddress> emailListAdded;

    public ContactEmailAdded(String userUUID, List<EmailAddress> asList) {
        this.userUUID = userUUID;
        this.emailListAdded = asList;

    }

    public String getUserUUID() {
        return userUUID;
    }

    public int getEmailListSize() {
        return emailListAdded.size();
    }

}
