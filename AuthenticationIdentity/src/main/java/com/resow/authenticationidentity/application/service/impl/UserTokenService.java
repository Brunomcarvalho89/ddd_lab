package com.resow.authenticationidentity.application.service.impl;

import com.resow.authenticationidentity.domain.model.authenticantion.UserToken;
import com.resow.authenticationidentity.domain.model.authenticantion.UserTokenData;
import java.util.UUID;
import com.resow.authenticationidentity.application.service.IUserTokenService;
import com.resow.authenticationidentity.domain.model.identity.User;
import com.resow.authenticationidentity.domain.model.identity.descriptor.UserDescriptor;
import com.resow.authenticationidentity.domain.model.identity.repository.UserTokenRepository;
import com.resow.authenticationidentity.infrastructure.token.UserTokenIssuer;
import com.resow.common.exception.InvalidTokenSecretException;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class UserTokenService implements IUserTokenService {

    private final UserTokenRepository userTokenRepository;
    private final UserTokenIssuer userTokenIssuer;

    public UserTokenService(UserTokenRepository userTokenRepository, UserTokenIssuer userTokenIssuer) {
        this.userTokenRepository = userTokenRepository;
        this.userTokenIssuer = userTokenIssuer;
    }

    @Override
    public UserToken get(User user, UserTokenData userTokenData) throws InvalidTokenSecretException {

        String tokenSecret = UUID.randomUUID().toString();

        String issuerToken = userTokenIssuer.create(userTokenData, tokenSecret);

        UserDescriptor userDescriptor = user.descriptor();

        UserToken userToken = new UserToken(
                userDescriptor.getUserUUID(),
                issuerToken,
                tokenSecret);

        userTokenRepository.removeByUserID(userDescriptor.getUserUUID());
        userTokenRepository.add(userToken);

        return userToken;
    }

}
