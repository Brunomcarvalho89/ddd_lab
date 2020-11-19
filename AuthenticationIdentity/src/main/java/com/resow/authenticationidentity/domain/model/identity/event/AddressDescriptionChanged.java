package com.resow.authenticationidentity.domain.model.identity.event;

import com.resow.common.event.BaseEvent;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class AddressDescriptionChanged implements BaseEvent {

    private String userUUID;
    private String newAddressDescription;
    private String oldAddressDescription;

    public AddressDescriptionChanged(String userUUID, String newAddressDescription, String oldAddressDescription) {
        this.userUUID = userUUID;
        this.newAddressDescription = newAddressDescription;
        this.oldAddressDescription = oldAddressDescription;
    }

    public String getUserUUID() {
        return userUUID;
    }

    public String getNewAddressDescription() {
        return newAddressDescription;
    }

    public String getOldAddressDescription() {
        return oldAddressDescription;
    }

}
