package com.resow.authenticationidentity.domain.model.identity.event;

import com.resow.common.event.BaseEvent;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class AddressComplementChanged implements BaseEvent {

    private String userUUID;
    private String newAddressComplement;
    private String oldAddressComplement;

    public AddressComplementChanged(String userUUID, String newAddressComplement, String oldAddressComplement) {
        this.userUUID = userUUID;
        this.newAddressComplement = newAddressComplement;
        this.oldAddressComplement = oldAddressComplement;
    }

    public String getUserUUID() {
        return userUUID;
    }

    public String getNewAddressComplement() {
        return newAddressComplement;
    }

    public String getOldAddressComplement() {
        return oldAddressComplement;
    }
}
