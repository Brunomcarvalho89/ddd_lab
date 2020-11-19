package com.resow.authenticationidentity.domain.model.identity.event;

import com.resow.common.event.BaseEvent;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class NumberAddressChanged implements BaseEvent {

    private String userUUID;
    private Integer newNumber;
    private Integer oldNumber;

    public NumberAddressChanged(String userUUID, Integer newNumber, Integer oldNumber) {
        this.userUUID = userUUID;
        this.newNumber = newNumber;
        this.oldNumber = oldNumber;
    }

    public String getUserUUID() {
        return userUUID;
    }

    public Integer getNewNumber() {
        return newNumber;
    }

    public Integer getOldNumber() {
        return oldNumber;
    }
}
