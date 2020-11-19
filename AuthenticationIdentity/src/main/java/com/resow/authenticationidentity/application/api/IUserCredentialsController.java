package com.resow.authenticationidentity.application.api;

import com.resow.authenticationidentity.application.command.ChangeDateOfBirthCommand;
import com.resow.authenticationidentity.application.command.ChangeFullnameCommand;
import com.resow.authenticationidentity.application.command.ChangeNicknameCommand;
import com.resow.authenticationidentity.application.command.ChangePasswordCommand;
import com.resow.authenticationidentity.domain.model.identity.exception.NameInvalidException;
import com.resow.common.exception.GenerateHashException;

/**
 *
 * @author home
 */
public interface IUserCredentialsController {

    public Object changeFullname(ChangeFullnameCommand changeFullnameCommand) throws NameInvalidException;

    public Object changeDateOfBirth(ChangeDateOfBirthCommand changeDateOfBirth) throws GenerateHashException;

    public Object changeNickname(ChangeNicknameCommand changeNicknameCommand);

    public Object changePassword(ChangePasswordCommand changePassowordCommand) throws GenerateHashException;
}
