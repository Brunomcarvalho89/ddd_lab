package com.resow.authenticationidentity.application.api;

import com.resow.authenticationidentity.application.command.AuthenticateByUsernameAndPasswordCommand;
import com.resow.authenticationidentity.domain.model.identity.exception.InvalidPasswordException;
import com.resow.authenticationidentity.domain.model.identity.exception.UserNotFoundException;
import com.resow.common.exception.InvalidTokenSecretException;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public interface IUserAuthenticationController {

    Object authenticate(AuthenticateByUsernameAndPasswordCommand authenticateCommand) throws InvalidTokenSecretException, UserNotFoundException, InvalidPasswordException;

}
