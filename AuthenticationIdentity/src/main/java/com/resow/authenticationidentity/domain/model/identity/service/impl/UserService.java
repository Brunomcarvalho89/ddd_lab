package com.resow.authenticationidentity.domain.model.identity.service.impl;

import com.resow.authenticationidentity.domain.model.identity.Address;
import com.resow.authenticationidentity.domain.model.identity.City;
import com.resow.authenticationidentity.domain.model.identity.DateOfBirth;
import com.resow.authenticationidentity.domain.model.identity.FullName;
import com.resow.authenticationidentity.domain.model.identity.Login;
import com.resow.authenticationidentity.domain.model.identity.Person;
import com.resow.authenticationidentity.domain.model.identity.User;
import com.resow.authenticationidentity.domain.model.identity.UserUUID;
import com.resow.authenticationidentity.domain.model.identity.exception.NameInvalidException;
import com.resow.authenticationidentity.domain.model.identity.service.IUserService;
import com.resow.common.exception.GenerateHashException;
import java.time.LocalDate;
import java.time.Month;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class UserService implements IUserService {

    @Override
    public User userFrom(UserUUID userUUID, String nickName, String password, String name, String addressDescription, String addressComplement, Integer number, String zipCode, City city) throws GenerateHashException, NameInvalidException {

        Login login = Login.builder()
                .withNickName(nickName)
                .withPassword(password)
                .build();

        Person person = new Person(
                FullName.builder()
                        .withFullName(name)
                        .build(),
                new DateOfBirth(LocalDate.of(1989, Month.MARCH, 03)));

        Address address = Address.builder()
                .withAddressDescription(addressDescription)
                .withAddressComplement(addressComplement)
                .withNumber(number)
                .withZipCode(zipCode)
                .withCity(city)
                .build();

        User user = new User(userUUID, login, person, address);
        
        return user;
    }
}
