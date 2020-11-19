package com.resow.authenticationidentity.application.service;

import com.resow.authenticationidentity.domain.model.authenticantion.UserToken;
import com.resow.authenticationidentity.domain.model.authenticantion.UserTokenData;
import com.resow.authenticationidentity.domain.model.identity.User;
import com.resow.common.exception.InvalidTokenSecretException;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public interface IUserTokenService {

    public UserToken get(User user, UserTokenData userTokenData) throws InvalidTokenSecretException;

}
