package com.resow.authenticationidentity.application.service;

import com.resow.authenticationidentity.application.command.AuthenticateByUsernameAndPasswordCommand;
import com.resow.authenticationidentity.domain.model.identity.exception.InvalidPasswordException;
import com.resow.authenticationidentity.domain.model.identity.exception.UserNotFoundException;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public interface IUserAuthenticationByPasswordService {

    void authenticateByUsernamePassword(AuthenticateByUsernameAndPasswordCommand authenticateByUsernameAndPasswordCommand) throws UserNotFoundException, InvalidPasswordException;
}
