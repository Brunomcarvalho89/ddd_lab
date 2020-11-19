package com.resow.authenticationidentity.application.service.impl;

import com.resow.authenticationidentity.application.command.ChangeAddressComplementCommand;
import com.resow.authenticationidentity.application.command.ChangeAddressDescriptionCommand;
import com.resow.authenticationidentity.application.command.ChangeCityCommand;
import com.resow.authenticationidentity.application.command.ChangeNumberAddressCommand;
import com.resow.authenticationidentity.application.command.ChangeZipCodeCommand;
import com.resow.authenticationidentity.domain.model.identity.AddressComplement;
import com.resow.authenticationidentity.domain.model.identity.AddressDescription;
import com.resow.authenticationidentity.domain.model.identity.City;
import com.resow.authenticationidentity.domain.model.identity.User;
import com.resow.authenticationidentity.domain.model.identity.ZipCode;
import com.resow.authenticationidentity.domain.model.identity.exception.AddressDescriptionException;
import com.resow.authenticationidentity.domain.model.identity.exception.CityException;
import com.resow.authenticationidentity.domain.model.identity.exception.NumberAddressException;
import com.resow.authenticationidentity.domain.model.identity.exception.UserNotFoundException;
import com.resow.authenticationidentity.domain.model.identity.exception.ZipCodeException;
import com.resow.authenticationidentity.domain.model.identity.repository.CityRepository;
import com.resow.authenticationidentity.domain.model.identity.repository.UserRepository;
import com.resow.authenticationidentity.application.service.IUserAddressService;
import com.resow.authenticationidentity.domain.model.identity.descriptor.UserDescriptor;
import com.resow.authenticationidentity.domain.model.identity.event.AddressComplementChanged;
import com.resow.authenticationidentity.domain.model.identity.event.AddressDescriptionChanged;
import com.resow.authenticationidentity.domain.model.identity.event.CityChanged;
import com.resow.authenticationidentity.domain.model.identity.event.NumberAddressChanged;
import com.resow.authenticationidentity.domain.model.identity.event.ZipCodeChanged;
import com.resow.common.event.EventPublisher;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class UserAddressService implements IUserAddressService {

    private UserRepository userRepository;
    private final CityRepository cityRepository;

    public UserAddressService(
            UserRepository userRepository,
            CityRepository cityRepository) {

        this.userRepository = userRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public void changeAddressDescription(ChangeAddressDescriptionCommand changeAddressDescription) throws AddressDescriptionException {

        User user = this.user(changeAddressDescription.getNickname());
        UserDescriptor descriptor = user.descriptor();

        user.changeAddressDescription(new AddressDescription(changeAddressDescription.getAddressDescription()));

        this.userRepository.update(user.address());

        EventPublisher
                .instance()
                .publish(
                        new AddressDescriptionChanged(
                                descriptor.getUserUUID(),
                                user.descriptor().getAddressDescriptor().getAddressDescription(),
                                descriptor.getAddressDescriptor().getAddressDescription()));
    }

    @Override
    public void changeAddressComplement(ChangeAddressComplementCommand changeAddressComplement) {

        User user = this.user(changeAddressComplement.getNickname());
        UserDescriptor descriptor = user.descriptor();

        user.changeAddressComplement(new AddressComplement(changeAddressComplement.getAddressComplement()));

        this.userRepository.update(user.address());

        EventPublisher
                .instance()
                .publish(
                        new AddressComplementChanged(
                                descriptor.getUserUUID(),
                                user.descriptor().getAddressDescriptor().getAddressComplement(),
                                descriptor.getAddressDescriptor().getAddressComplement()));
    }

    @Override
    public void changeNumberAddress(ChangeNumberAddressCommand numberAddressCommand) throws NumberAddressException {
        User user = this.user(numberAddressCommand.getNickname());
        UserDescriptor descriptor = user.descriptor();

        user.changeNumberAddress(numberAddressCommand.getNewNumberAddress());

        this.userRepository.update(user.address());

        EventPublisher
                .instance()
                .publish(
                        new NumberAddressChanged(
                                descriptor.getUserUUID(),
                                user.descriptor().getAddressDescriptor().getNumber(),
                                descriptor.getAddressDescriptor().getNumber()));
    }

    @Override
    public void changeZipCode(ChangeZipCodeCommand changeZipCode) throws ZipCodeException {
        User user = this.user(changeZipCode.getNickname());
        UserDescriptor descriptor = user.descriptor();

        user.changeZipCode(new ZipCode(changeZipCode.getZipcode()));

        this.userRepository.update(user.address());

        EventPublisher
                .instance()
                .publish(
                        new ZipCodeChanged(
                                descriptor.getUserUUID(),
                                user.descriptor().getAddressDescriptor().getZipCode(),
                                descriptor.getAddressDescriptor().getZipCode()));
    }

    @Override
    public void changeCity(ChangeCityCommand changeCity) throws CityException {
        User user = this.user(changeCity.getNickname());
        UserDescriptor descriptor = user.descriptor();

        City city = this.cityRepository.findById(changeCity.getCity());

        user.changeCity(city);

        this.userRepository.update(user.address());

        EventPublisher
                .instance()
                .publish(
                        new CityChanged(
                                descriptor.getUserUUID(),
                                user.descriptor().getAddressDescriptor().getCity().getCityID(),
                                descriptor.getAddressDescriptor().getCity().getCityID()));
    }

    public User user(String nickname) {

        User user = this.userRepository.findByNickName(nickname);

        if (user == null) {
            throw new UserNotFoundException("User not found.");
        }

        return user;
    }

}
