package com.resow.authenticationidentity.domain.model.authenticantion;

import com.resow.common.application.token.TokenData;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class UserTokenData extends TokenData {

    private String nickname;
    private String hashValid;

    public UserTokenData(String issuer, String subject, String[] audience, long expiration, String nickname, String hashValid) {
        super(issuer, subject, audience, expiration);
        this.nickname = nickname;
        this.hashValid = hashValid;
    }

    public String getNickname() {
        return nickname;
    }

    public String getHashValid() {
        return hashValid;
    }
}
