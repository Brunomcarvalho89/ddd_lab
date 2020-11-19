package com.resow.authenticationidentity.application.service;

import com.resow.authenticationidentity.application.command.UserDeregisterCommand;
import com.resow.authenticationidentity.application.command.UserRegisterCommand;
import com.resow.authenticationidentity.domain.model.identity.exception.AddContactUserException;
import com.resow.authenticationidentity.domain.model.identity.exception.CityException;
import com.resow.authenticationidentity.domain.model.identity.exception.NameInvalidException;
import com.resow.authenticationidentity.domain.model.identity.exception.NicknameAlreadyExistsException;
import com.resow.authenticationidentity.domain.model.identity.exception.ZipCodeException;
import com.resow.common.exception.GenerateHashException;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public interface IUserRegistrationService {

    public void register(UserRegisterCommand userRegistryCommand) throws AddContactUserException, NameInvalidException, NicknameAlreadyExistsException, ZipCodeException, GenerateHashException,CityException;

    public void deregister(UserDeregisterCommand userDesregistryCommand);
}
