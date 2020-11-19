package com.resow.authenticationidentity.application.service.impl;

import com.resow.authenticationidentity.application.command.ChangeEmailCommand;
import com.resow.authenticationidentity.application.command.ChangePhoneCommand;
import com.resow.authenticationidentity.application.command.NewEmailCommand;
import com.resow.authenticationidentity.application.command.NewPhoneCommand;
import com.resow.authenticationidentity.application.command.RemoveEmailCommand;
import com.resow.authenticationidentity.application.command.RemovePhonelCommand;
import com.resow.authenticationidentity.domain.model.identity.Email;
import com.resow.authenticationidentity.domain.model.identity.EmailAddress;
import com.resow.authenticationidentity.domain.model.identity.Phone;
import com.resow.authenticationidentity.domain.model.identity.PhoneNumber;
import com.resow.authenticationidentity.domain.model.identity.User;
import com.resow.authenticationidentity.domain.model.identity.UserUUID;
import com.resow.authenticationidentity.domain.model.identity.exception.UserNotFoundException;
import com.resow.authenticationidentity.domain.model.identity.repository.UserRepository;
import com.resow.authenticationidentity.application.service.IUserContactService;
import com.resow.authenticationidentity.domain.model.identity.descriptor.UserDescriptor;
import com.resow.authenticationidentity.domain.model.identity.event.ContactEmailAdded;
import com.resow.authenticationidentity.domain.model.identity.event.ContactEmailChanged;
import com.resow.authenticationidentity.domain.model.identity.event.ContactEmailRemoved;
import com.resow.authenticationidentity.domain.model.identity.event.ContactPhoneAdded;
import com.resow.authenticationidentity.domain.model.identity.event.ContactPhoneChanged;
import com.resow.authenticationidentity.domain.model.identity.event.ContactPhoneRemoved;
import com.resow.common.event.EventPublisher;
import java.util.Arrays;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class UserContactService implements IUserContactService {

    private final UserRepository userRepository;

    public UserContactService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void newEmail(NewEmailCommand newEmailCommand) {

        User user = this.user(newEmailCommand.getNickname());
        UserDescriptor descriptor = user.descriptor();

        EmailAddress emailAddress = new EmailAddress(newEmailCommand.getEmail());

        Email emailAdded = user.addContact(emailAddress);

        this.userRepository.save(emailAdded);

        EventPublisher
                .instance()
                .publish(
                        new ContactEmailAdded(
                                descriptor.getUserUUID(),
                                Arrays.asList(emailAddress)));
    }

    @Override
    public void newPhone(NewPhoneCommand newPhoneCommand) {

        User user = this.user(newPhoneCommand.getNickname());
        UserDescriptor descriptor = user.descriptor();

        Phone phoneAdded = user.addContact(new PhoneNumber(newPhoneCommand.getPhone()));

        this.userRepository.save(phoneAdded);

        EventPublisher
                .instance()
                .publish(
                        new ContactPhoneAdded(
                                descriptor.getUserUUID(),
                                Arrays.asList(new PhoneNumber(phoneAdded.getNumber()))));
    }

    @Override
    public void removeEmail(RemoveEmailCommand removeEmailCommand) {

        User user = this.user(removeEmailCommand.getNickname());
        UserDescriptor descriptor = user.descriptor();

        Email emailRemoved = user.removeContact(new EmailAddress(removeEmailCommand.getEmail()));

        this.userRepository.remove(emailRemoved);

        EventPublisher
                .instance()
                .publish(
                        new ContactEmailRemoved(
                                descriptor.getUserUUID(),
                                Arrays.asList(new EmailAddress(emailRemoved.getEmail()))));
    }

    @Override
    public void removePhone(RemovePhonelCommand removePhonelCommand) {

        User user = this.user(removePhonelCommand.getNickname());
        UserDescriptor descriptor = user.descriptor();

        Phone phoneRemoved = user.removeContact(new PhoneNumber(removePhonelCommand.getPhone()));

        this.userRepository.remove(phoneRemoved);

        EventPublisher
                .instance()
                .publish(
                        new ContactPhoneRemoved(
                                descriptor.getUserUUID(),
                                Arrays.asList(new PhoneNumber(phoneRemoved.getNumber()))));
    }

    @Override
    public void changeEmail(ChangeEmailCommand changeEmailCommand) {

        User user = this.user(changeEmailCommand.getNickname());
        UserDescriptor descriptor = user.descriptor();

        Email emailAdded = user.addContact(new EmailAddress(changeEmailCommand.getNewEmail()));
        this.userRepository.save(emailAdded);

        Email emailRemoved = user.removeContact(new EmailAddress(changeEmailCommand.getOldEmail()));
        this.userRepository.remove(emailRemoved);

        EventPublisher
                .instance()
                .publish(
                        new ContactEmailChanged(
                                descriptor.getUserUUID(),
                                new EmailAddress(emailAdded.getEmail()),
                                new EmailAddress(emailRemoved.getEmail())));
    }

    @Override
    public void changePhone(ChangePhoneCommand changePhoneCommand) {

        User user = this.user(changePhoneCommand.getNickname());
        UserDescriptor descriptor = user.descriptor();

        Phone phoneAdded = user.addContact(new PhoneNumber(changePhoneCommand.getNewPhone()));
        this.userRepository.save(phoneAdded);

        Phone phoneRemoved = user.removeContact(new PhoneNumber(changePhoneCommand.getOldPhone()));
        this.userRepository.remove(phoneRemoved);

        EventPublisher
                .instance()
                .publish(
                        new ContactPhoneChanged(
                                descriptor.getUserUUID(),
                                new PhoneNumber(phoneAdded.getNumber()),
                                new PhoneNumber(phoneRemoved.getNumber())));
    }

    public User user(UserUUID userUUID) {

        User user = this.userRepository.findByUUID(userUUID);

        if (user == null) {
            throw new UserNotFoundException("User not found.");
        }

        return user;
    }

    public User user(String nickname) {

        User user = this.userRepository.findByNickName(nickname);

        if (user == null) {
            throw new UserNotFoundException("User not found.");
        }

        return user;
    }
}
