package com.resow.authenticationidentity.application.service;

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
public interface IUserCredentialsService {
    
    public void changeFullName(ChangeFullnameCommand changeFullnameCommand) throws NameInvalidException;

    public void changeDateOfBirth(ChangeDateOfBirthCommand changeDateOfBirth);
    
    void changeNickname(ChangeNicknameCommand changeNicknameCommand);

    void changePassword(ChangePasswordCommand changePassowordCommand) throws GenerateHashException;

}
