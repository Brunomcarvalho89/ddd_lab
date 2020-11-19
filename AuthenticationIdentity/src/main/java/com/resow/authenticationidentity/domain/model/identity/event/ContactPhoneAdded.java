package com.resow.authenticationidentity.domain.model.identity.event;

import com.resow.authenticationidentity.domain.model.identity.PhoneNumber;
import java.util.List;
import com.resow.common.event.BaseEvent;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class ContactPhoneAdded implements BaseEvent {

    private String userUUID;
    private List<PhoneNumber> phoneListAdded;

    public ContactPhoneAdded(String userUUID, List<PhoneNumber> phoneListAdded) {
        this.userUUID = userUUID;
        this.phoneListAdded = phoneListAdded;
    }

    public String getUserUUID() {
        return userUUID;
    }

    public int getPhoneListSize() {
        return phoneListAdded.size();
    }
}
