package com.resow.authenticationidentity.domain.model.identity.service;

import com.resow.authenticationidentity.domain.model.identity.City;
import com.resow.authenticationidentity.domain.model.identity.User;
import com.resow.authenticationidentity.domain.model.identity.UserUUID;
import com.resow.authenticationidentity.domain.model.identity.exception.NameInvalidException;
import com.resow.common.exception.GenerateHashException;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public interface IUserService {

    public User userFrom(UserUUID userUUID, String nickName, String password, String name, String addressDescription, String addressComplement, Integer number, String zipCode, City city) throws GenerateHashException, NameInvalidException;
}
