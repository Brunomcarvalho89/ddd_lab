package com.resow.authenticationidentity.application.service;

import com.resow.authenticationidentity.application.command.ChangeEmailCommand;
import com.resow.authenticationidentity.application.command.ChangePhoneCommand;
import com.resow.authenticationidentity.application.command.NewEmailCommand;
import com.resow.authenticationidentity.application.command.NewPhoneCommand;
import com.resow.authenticationidentity.application.command.RemoveEmailCommand;
import com.resow.authenticationidentity.application.command.RemovePhonelCommand;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public interface IUserContactService {

    void changeEmail(ChangeEmailCommand changeEmailCommand);

    void changePhone(ChangePhoneCommand changePhoneCommand);

    void newEmail(NewEmailCommand newEmailCommand);

    void newPhone(NewPhoneCommand newPhoneCommand);

    void removeEmail(RemoveEmailCommand removeEmailCommand);

    void removePhone(RemovePhonelCommand removePhonelCommand);

}
