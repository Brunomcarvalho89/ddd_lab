package com.resow.authenticationidentity.application.service.impl;

import com.resow.authenticationidentity.application.command.AuthenticateByUsernameAndPasswordCommand;
import com.resow.authenticationidentity.domain.model.identity.User;
import com.resow.authenticationidentity.domain.model.identity.repository.UserRepository;
import com.resow.authenticationidentity.application.service.IUserAuthenticationByPasswordService;
import com.resow.authenticationidentity.domain.model.identity.exception.InvalidPasswordException;
import com.resow.authenticationidentity.domain.model.identity.exception.UserNotFoundException;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class UserAuthenticationByPasswordService implements IUserAuthenticationByPasswordService {

    private final UserRepository userRepository;

    public UserAuthenticationByPasswordService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void authenticateByUsernamePassword(AuthenticateByUsernameAndPasswordCommand authenticateByUsernameAndPasswordCommand) throws UserNotFoundException, InvalidPasswordException {
        User user = this.userRepository.findByNickName(authenticateByUsernameAndPasswordCommand.getNickname());
        user.authenticate(authenticateByUsernameAndPasswordCommand.getPassword());
    }

}
