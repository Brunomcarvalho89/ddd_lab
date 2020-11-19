package com.resow.authenticationidentity.application.service.impl;

import com.resow.authenticationidentity.application.command.AuthenticateByTokenCommand;
import com.resow.authenticationidentity.domain.model.identity.repository.UserRepository;
import com.resow.authenticationidentity.domain.model.authenticantion.UserTokenData;
import com.resow.authenticationidentity.domain.BeanFacades;
import com.resow.authenticationidentity.domain.model.authenticantion.UserToken;
import com.resow.authenticationidentity.domain.model.identity.User;
import com.resow.authenticationidentity.domain.model.identity.descriptor.UserDescriptor;
import com.resow.authenticationidentity.domain.model.identity.repository.UserTokenRepository;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;
import java.util.Optional;
import com.resow.authenticationidentity.application.service.IUserAuthenticationByTokenService;
import com.resow.authenticationidentity.application.service.TokenIssuer;
import com.resow.authenticationidentity.domain.model.identity.exception.UserNotFoundException;
import com.resow.common.exception.InvalidTokenSecretException;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class UserAuthenticationByTokenService implements IUserAuthenticationByTokenService {

    private final UserRepository userRepository;
    private final UserTokenRepository userTokenRepository;
    private final TokenIssuer userTokenIssuer;

    public UserAuthenticationByTokenService(UserRepository userRepository, UserTokenRepository userTokenRepository, TokenIssuer userTokenIssuer) {
        this.userRepository = userRepository;
        this.userTokenRepository = userTokenRepository;
        this.userTokenIssuer = userTokenIssuer;
    }

    @Override
    public Boolean authenticateByToken(AuthenticateByTokenCommand authenticateByTokenCommand) throws InvalidTokenSecretException, UserNotFoundException {

        Optional<UserTokenData> oUserTokenData = userTokenIssuer.decode(authenticateByTokenCommand.getToken());

        if (oUserTokenData.isPresent()) {

            UserTokenData userTokenData = oUserTokenData.get();

            User user = userRepository.findByNickName(userTokenData.getNickname());
            if (Objects.isNull(user)) {
                return Boolean.FALSE;
            }

            UserDescriptor userDescriptor = user.descriptor();

            UserToken userToken = userTokenRepository.findByUserID(userDescriptor.getUserUUID());

            if (!userTokenIssuer.verify(authenticateByTokenCommand.getToken(), userToken.descriptor().getTokenSecret())) {
                return Boolean.FALSE;
            }

            LocalDateTime expirationDate = Instant.ofEpochMilli(userTokenData.getExpiration()).atZone(ZoneId.systemDefault()).toLocalDateTime();
            String hashValid = userTokenData.getHashValid();

            if (expirationDate.isBefore(LocalDateTime.now())) {
                return Boolean.FALSE;
            }

            return BeanFacades.instance()
                    .getHashFunction()
                    .verify(hashValid, userDescriptor.getUserUUID());
        }

        return Boolean.FALSE;
    }

}
