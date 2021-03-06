package com.resow.authenticationidentity.application.event;

import com.resow.authenticationidentity.domain.model.identity.UserUUID;
import com.resow.common.event.BaseEvent;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class UserRemoved implements BaseEvent {

    private UserUUID userUUID;

    public UserRemoved(UserUUID userUUID) {
        this.userUUID = userUUID;
    }

    public UserUUID userUUID() {
        return userUUID;
    }

}
