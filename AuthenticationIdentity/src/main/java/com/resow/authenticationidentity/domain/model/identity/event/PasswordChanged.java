package com.resow.authenticationidentity.domain.model.identity.event;

import com.resow.common.event.BaseEvent;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class PasswordChanged implements BaseEvent {

    private String userUUID;

    public PasswordChanged(String userUUID) {
        this.userUUID = userUUID;
    }

    public String getUserUUID() {
        return userUUID;
    }

}
