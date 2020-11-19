package com.resow.authenticationidentity.domain.model.identity;

import javax.persistence.Embeddable;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
@Embeddable
public class UserUUID {

    private String userUUID;

    public UserUUID() {
    }

    public UserUUID(String userUUID) {
        this.userUUID = userUUID;
    }

    @Override
    public String toString() {
        return userUUID;
    }

}
