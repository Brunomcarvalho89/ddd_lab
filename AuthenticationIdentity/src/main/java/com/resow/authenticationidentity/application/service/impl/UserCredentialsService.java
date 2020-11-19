package com.resow.authenticationidentity.application.service.impl;

import com.resow.authenticationidentity.application.command.ChangeDateOfBirthCommand;
import com.resow.authenticationidentity.application.command.ChangeFullnameCommand;
import com.resow.authenticationidentity.application.command.ChangeNicknameCommand;
import com.resow.authenticationidentity.application.command.ChangePasswordCommand;
import com.resow.authenticationidentity.application.service.IUserCredentialsService;
import com.resow.authenticationidentity.domain.model.identity.DateOfBirth;
import com.resow.authenticationidentity.domain.model.identity.FullName;
import com.resow.authenticationidentity.domain.model.identity.User;
import com.resow.authenticationidentity.domain.model.identity.descriptor.UserDescriptor;
import com.resow.authenticationidentity.domain.model.identity.event.DateOfBirthChanged;
import com.resow.authenticationidentity.domain.model.identity.event.FullNameChanged;
import com.resow.authenticationidentity.domain.model.identity.event.NicknameChanged;
import com.resow.authenticationidentity.domain.model.identity.event.PasswordChanged;
import com.resow.authenticationidentity.domain.model.identity.exception.NameInvalidException;
import com.resow.authenticationidentity.domain.model.identity.exception.UserNotFoundException;
import com.resow.authenticationidentity.domain.model.identity.repository.UserRepository;
import com.resow.common.event.EventPublisher;
import com.resow.common.exception.GenerateHashException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class UserCredentialsService implements IUserCredentialsService {

    private UserRepository userRepository;

    public UserCredentialsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void changeFullName(ChangeFullnameCommand changeFullnameCommand) throws NameInvalidException {

        User user = this.user(changeFullnameCommand.getNickname());
        UserDescriptor descriptor = user.descriptor();

        user.changeFullName(
                FullName.builder()
                        .withFullName(changeFullnameCommand.getNewfullname())
                        .build());

        this.userRepository.update(user);

        EventPublisher.instance()
                .publish(
                        new FullNameChanged(
                                changeFullnameCommand.getNewfullname(),
                                descriptor.getFullName(),
                                descriptor.getUserUUID()));
    }

    @Override
    public void changeDateOfBirth(ChangeDateOfBirthCommand changeDateOfBirth) {

        User user = this.user(changeDateOfBirth.getNickname());
        UserDescriptor descriptor = user.descriptor();

        LocalDate dateOfBirth = LocalDate.parse(changeDateOfBirth.getDateOfBirth(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        user.changeDateOfBirth(new DateOfBirth(dateOfBirth));

        EventPublisher.instance()
                .publish(
                        new DateOfBirthChanged(
                                descriptor.getUserUUID(),
                                changeDateOfBirth.getDateOfBirth(),
                                descriptor.getDateOfBirth()));
    }

    @Override
    public void changeNickname(ChangeNicknameCommand changeNicknameCommand) {

        User user = this.user(changeNicknameCommand.getOldNickname());
        UserDescriptor descriptor = user.descriptor();

        user.changeNickname(changeNicknameCommand.getNewNickname());

        this.userRepository.update(user.login());

        EventPublisher
                .instance()
                .publish(
                        new NicknameChanged(
                                descriptor.getUserUUID(),
                                changeNicknameCommand.getNewNickname(),
                                changeNicknameCommand.getOldNickname()));
    }

    @Override
    public void changePassword(ChangePasswordCommand changePassowordCommand) throws GenerateHashException {

        User user = this.user(changePassowordCommand.getNickname());
        UserDescriptor descriptor = user.descriptor();

        user.changePassword(changePassowordCommand.getNewPassoword());

        this.userRepository.update(user.login());

        EventPublisher
                .instance()
                .publish(new PasswordChanged(descriptor.getUserUUID()));
    }

    public User user(String nickname) {

        User user = this.userRepository.findByNickName(nickname);

        if (user == null) {
            throw new UserNotFoundException("User not found.");
        }

        return user;
    }

}
