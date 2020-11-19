package com.resow.authenticationidentity.domain.model.identity.event;

import com.resow.authenticationidentity.domain.model.identity.PhoneNumber;
import java.util.List;
import com.resow.common.event.BaseEvent;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class ContactPhoneRemoved implements BaseEvent {

    private String userUUID;
    private List<PhoneNumber> phoneListRemoved;

    public ContactPhoneRemoved(String userUUID, List<PhoneNumber> phoneListRemoved) {
        this.userUUID = userUUID;
        this.phoneListRemoved = phoneListRemoved;
    }

    public String getUserUUID() {
        return userUUID;
    }

    public int getPhoneListSize() {
        return phoneListRemoved.size();
    }

    public Boolean contains(PhoneNumber phoneNumber) {
        return phoneListRemoved.contains(phoneNumber);
    }
}
