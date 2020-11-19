package com.resow.authenticationidentity.application.service.impl;

import com.resow.authenticationidentity.application.service.IZipCodeValidator;
import com.resow.authenticationidentity.application.command.UserDeregisterCommand;
import com.resow.authenticationidentity.application.command.UserRegisterCommand;
import com.resow.authenticationidentity.application.event.UserCreated;
import com.resow.authenticationidentity.domain.model.identity.User;
import com.resow.authenticationidentity.domain.model.identity.UserUUID;
import com.resow.authenticationidentity.application.event.UserRemoved;
import com.resow.authenticationidentity.domain.model.identity.City;
import com.resow.authenticationidentity.domain.model.identity.Email;
import com.resow.authenticationidentity.domain.model.identity.EmailAddress;
import com.resow.authenticationidentity.domain.model.identity.exception.AddContactUserException;
import com.resow.authenticationidentity.domain.model.identity.exception.NameInvalidException;
import com.resow.authenticationidentity.domain.model.identity.exception.NicknameAlreadyExistsException;
import com.resow.authenticationidentity.domain.model.identity.exception.ZipCodeException;
import com.resow.authenticationidentity.domain.model.identity.repository.CityRepository;
import com.resow.authenticationidentity.domain.model.identity.repository.UserRepository;
import com.resow.authenticationidentity.domain.model.identity.service.IUserService;
import com.resow.common.event.EventPublisher;
import com.resow.common.exception.GenerateHashException;
import com.resow.authenticationidentity.application.service.IUserRegistrationService;
import com.resow.authenticationidentity.domain.model.identity.exception.CityException;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class UserRegistrationService implements IUserRegistrationService {

    private final IUserService userRegisterService;
//    private final IZipCodeValidator zipCodeValidator;

    private final UserRepository userRepository;
    private final CityRepository cityRepository;

    public UserRegistrationService(
            IUserService userRegisterService,
            IZipCodeValidator iZipCodeValidator,
            UserRepository userRepository,
            CityRepository cityRepository) {

        this.userRegisterService = userRegisterService;
//        this.zipCodeValidator = iZipCodeValidator;
        this.userRepository = userRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public void register(UserRegisterCommand userRegisterCommand) throws AddContactUserException, NameInvalidException, NicknameAlreadyExistsException, ZipCodeException, GenerateHashException, CityException {

        UserUUID userUUID = userRepository.nextID();

        if (userRepository.existsByNickName(userRegisterCommand.getNickName())) {
            throw new NicknameAlreadyExistsException("Nickname already exists.");
        }

        City city;
        try {
            city = cityRepository.findById(userRegisterCommand.getCity());
        } catch (Exception ex) {
            throw new CityException("City not found.");
        }

        String abbreviation = city.cityDescriptor()
                .getStateDescriptor()
                .getCountryDescriptor()
                .getAbbreviation();

//        try {
//            Implementacao feita para treinar consumo de api externa.
//            zipCodeValidator.valid(userRegisterCommand.getZipCode(), abbreviation);
//        } catch (ZipCodeException ex) {
            // Como esta versao é para estudos não vou lancar excecao por causa de cep.
//        }

        User user = userRegisterService.userFrom(userUUID,
                userRegisterCommand.getNickName(),
                userRegisterCommand.getPassword(),
                userRegisterCommand.getName(),
                userRegisterCommand.getAddressDescription(),
                userRegisterCommand.getAddressComplement(),
                userRegisterCommand.getNumber(),
                userRegisterCommand.getZipCode(),
                city);

        userRepository.save(user.login());
        userRepository.save(user.address());
        userRepository.save(user);

        try {
            Email emailAdded = user.addContact(new EmailAddress(userRegisterCommand.getEmail()));
            userRepository.save(emailAdded);
        } catch (Exception ex) {
            ex.printStackTrace();

            userRepository.remove(user);

            throw new AddContactUserException();
        }

        EventPublisher
                .instance()
                .publish(new UserCreated(user.userUUID()));
    }

    @Override
    public void deregister(UserDeregisterCommand userDesregistryCommand) {

        User user = userRepository.findByNickName(userDesregistryCommand.getNickName());

        userRepository.remove(user);

        EventPublisher
                .instance()
                .publish(new UserRemoved(user.userUUID()));
    }
}
