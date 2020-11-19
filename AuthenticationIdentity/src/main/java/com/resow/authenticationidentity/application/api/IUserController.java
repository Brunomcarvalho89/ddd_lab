package com.resow.authenticationidentity.application.api;

import com.resow.authenticationidentity.application.command.UserDeregisterCommand;
import com.resow.authenticationidentity.application.command.UserRegisterCommand;
import com.resow.authenticationidentity.domain.model.identity.exception.AddContactUserException;
import com.resow.authenticationidentity.domain.model.identity.exception.CityException;
import com.resow.authenticationidentity.domain.model.identity.exception.NameInvalidException;
import com.resow.authenticationidentity.domain.model.identity.exception.NicknameAlreadyExistsException;
import com.resow.authenticationidentity.domain.model.identity.exception.ZipCodeException;
import com.resow.common.exception.GenerateHashException;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public interface IUserController {

    Object register(UserRegisterCommand userRegisterCommand) throws NameInvalidException, AddContactUserException, GenerateHashException, NicknameAlreadyExistsException, ZipCodeException, CityException;

    Object deregister(UserDeregisterCommand userDeregisterCommand);

    Object get(String nickname);

    Object getAll();
}
