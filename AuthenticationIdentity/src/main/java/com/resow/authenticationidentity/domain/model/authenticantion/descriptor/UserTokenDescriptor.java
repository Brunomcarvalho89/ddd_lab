package com.resow.authenticationidentity.domain.model.authenticantion.descriptor;

import com.resow.common.domain.model.descriptor.Descriptor;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class UserTokenDescriptor implements Descriptor {

    private String userID;
    private String token;
    private String tokenSecret;

    public UserTokenDescriptor(String userID, String token, String tokenSecret) {
        this.userID = userID;
        this.token = token;
        this.tokenSecret = tokenSecret;
    }

    public String getUserID() {
        return userID;
    }

    public String getToken() {
        return token;
    }

    public String getTokenSecret() {
        return tokenSecret;
    }

    @Override
    public String toString() {
        return "UserTokenDescriptor{" + "userID=" + userID + ", token=" + token + ", tokenSecret=" + tokenSecret + '}';
    }

}
