package com.resow.authenticationidentity.application.service;

import com.resow.authenticationidentity.application.command.AuthenticateByUsernameAndPasswordCommand;
import com.resow.authenticationidentity.domain.model.identity.exception.InvalidPasswordException;
import com.resow.authenticationidentity.domain.model.identity.exception.UserNotFoundException;
import com.resow.common.exception.InvalidTokenSecretException;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public interface IUserAuthenticationByPasswordAndGenerateTokenService {

    public String authenticateAndIssueTokenTo(AuthenticateByUsernameAndPasswordCommand authenticateByUsernameAndPasswordCommand) throws InvalidTokenSecretException, InvalidPasswordException, UserNotFoundException;
}
