package com.resow.authenticationidentity.application.service.impl;

import com.resow.authenticationidentity.application.command.AuthenticateByUsernameAndPasswordCommand;
import com.resow.authenticationidentity.application.service.IUserAuthenticationByPasswordAndGenerateTokenService;
import com.resow.authenticationidentity.domain.model.authenticantion.UserToken;
import com.resow.authenticationidentity.domain.model.authenticantion.UserTokenData;
import com.resow.authenticationidentity.domain.model.identity.User;
import com.resow.authenticationidentity.domain.model.identity.descriptor.UserDescriptor;
import com.resow.authenticationidentity.domain.model.identity.exception.UserNotFoundException;
import com.resow.authenticationidentity.domain.model.identity.repository.UserRepository;
import com.resow.common.exception.InvalidTokenSecretException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import com.resow.authenticationidentity.application.service.IUserTokenService;
import com.resow.authenticationidentity.domain.BeanFacades;
import com.resow.authenticationidentity.application.service.IUserAuthenticationByPasswordService;
import com.resow.authenticationidentity.domain.model.identity.exception.InvalidPasswordException;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class UserAuthenticationByPasswordAndGenerateTokenService implements IUserAuthenticationByPasswordAndGenerateTokenService {

    private IUserAuthenticationByPasswordService authenticationUserByPasswordService;
    private UserRepository userRepository;
    private IUserTokenService tokenService;

    public UserAuthenticationByPasswordAndGenerateTokenService(
            IUserAuthenticationByPasswordService iAuthenticationUserByPasswordService,
            UserRepository userRepository,
            IUserTokenService tokenService) {

        this.authenticationUserByPasswordService = iAuthenticationUserByPasswordService;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @Override
    public String authenticateAndIssueTokenTo(AuthenticateByUsernameAndPasswordCommand authenticateByUsernameAndPasswordCommand) throws InvalidTokenSecretException, InvalidPasswordException, UserNotFoundException {

        this.authenticationUserByPasswordService.authenticateByUsernamePassword(authenticateByUsernameAndPasswordCommand);

        User user = this.userRepository.findByNickName(authenticateByUsernameAndPasswordCommand.getNickname());
        UserDescriptor userDescriptor = user.descriptor();

        String hashValid = BeanFacades.instance()
                .getHashFunction()
                .hash(userDescriptor.getUserUUID());

        UserTokenData userTokenData = new UserTokenData(
                "www.resow.com.br/adm-user",
                "token_authentication",
                new String[0],
                LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
                userDescriptor.getNickname(),
                hashValid);

        UserToken userToken = this.tokenService.get(user, userTokenData);

        return userToken.descriptor().getToken();

    }

}
