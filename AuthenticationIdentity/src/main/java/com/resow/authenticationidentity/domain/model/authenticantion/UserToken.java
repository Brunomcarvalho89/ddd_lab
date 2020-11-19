package com.resow.authenticationidentity.domain.model.authenticantion;

import com.resow.authenticationidentity.domain.model.authenticantion.descriptor.UserTokenDescriptor;
import com.resow.common.domain.model.descriptor.Describable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
@Entity
@Table(name = "userToken")
public class UserToken implements Describable<UserTokenDescriptor> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userID;
    private String token;
    private String tokenSecret;

    private UserToken() {
    }

    public UserToken(String userID, String token, String tokenSecret) {
        this.userID = userID;
        this.token = token;
        this.tokenSecret = tokenSecret;
    }

    @Override
    public UserTokenDescriptor descriptor() {
        return new UserTokenDescriptor(userID, token, tokenSecret);
    }
}
