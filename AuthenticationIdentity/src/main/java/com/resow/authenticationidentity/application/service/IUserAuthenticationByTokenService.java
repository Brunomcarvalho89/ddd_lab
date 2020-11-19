package com.resow.authenticationidentity.application.service;

import com.resow.authenticationidentity.application.command.AuthenticateByTokenCommand;
import com.resow.authenticationidentity.domain.model.identity.exception.UserNotFoundException;
import com.resow.common.exception.InvalidTokenSecretException;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public interface IUserAuthenticationByTokenService {

    Boolean authenticateByToken(AuthenticateByTokenCommand authenticateByTokenCommand) throws InvalidTokenSecretException, UserNotFoundException;
}
