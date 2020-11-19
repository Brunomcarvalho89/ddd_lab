package com.resow.authenticationidentity.domain.model.identity.event;

import com.resow.authenticationidentity.domain.model.identity.EmailAddress;
import com.resow.common.event.BaseEvent;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class ContactEmailChanged implements BaseEvent {

    private String userUUID;
    private EmailAddress newEmail;
    private EmailAddress oldEmail;

    public ContactEmailChanged(String userUUID, EmailAddress newEmail, EmailAddress oldEmail) {
        this.userUUID = userUUID;
        this.newEmail = newEmail;
        this.oldEmail = oldEmail;
    }

    public String getUserUUID() {
        return userUUID;
    }

    public EmailAddress getNewEmail() {
        return newEmail;
    }

    public EmailAddress getOldEmail() {
        return oldEmail;
    }

}
