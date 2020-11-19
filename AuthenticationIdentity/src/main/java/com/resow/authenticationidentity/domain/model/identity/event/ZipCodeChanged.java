package com.resow.authenticationidentity.domain.model.identity.event;

import com.resow.common.event.BaseEvent;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class ZipCodeChanged implements BaseEvent {

    private String userUUID;
    private String newZipCode;
    private String oldZipCode;

    public ZipCodeChanged(String userUUID, String newZipCode, String oldZipCode) {
        this.userUUID = userUUID;
        this.newZipCode = newZipCode;
        this.oldZipCode = oldZipCode;
    }

    public String getUserUUID() {
        return userUUID;
    }

    public String getNewZipCode() {
        return newZipCode;
    }

    public String getOldZipCode() {
        return oldZipCode;
    }
}
