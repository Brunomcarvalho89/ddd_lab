package com.resow.authenticationidentity.domain.model.identity.event;

import com.resow.common.event.BaseEvent;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class NicknameChanged implements BaseEvent {

    private String userUUID;
    private String newNickname;
    private String oldNickname;

    public NicknameChanged(String userUUID, String newNickname, String oldNickname) {
        this.userUUID = userUUID;
        this.newNickname = newNickname;
        this.oldNickname = oldNickname;
    }

    public String getUserUUID() {
        return userUUID;
    }

    public String getNewNickname() {
        return newNickname;
    }

    public String getOldNickname() {
        return oldNickname;
    }

}
