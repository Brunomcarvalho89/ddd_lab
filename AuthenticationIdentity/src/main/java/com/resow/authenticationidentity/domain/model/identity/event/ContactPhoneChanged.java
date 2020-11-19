package com.resow.authenticationidentity.domain.model.identity.event;

import com.resow.authenticationidentity.domain.model.identity.PhoneNumber;
import com.resow.common.event.BaseEvent;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class ContactPhoneChanged implements BaseEvent {

    private String userUUID;
    private PhoneNumber newPhone;
    private PhoneNumber oldPhone;

    public ContactPhoneChanged(String userUUID, PhoneNumber newPhone, PhoneNumber oldEmail) {
        this.userUUID = userUUID;
        this.newPhone = newPhone;
        this.oldPhone = oldPhone;
    }

    public String getUserUUID() {
        return userUUID;
    }

    public PhoneNumber getNewPhone() {
        return newPhone;
    }

    public PhoneNumber getOldPhone() {
        return oldPhone;
    }

}
