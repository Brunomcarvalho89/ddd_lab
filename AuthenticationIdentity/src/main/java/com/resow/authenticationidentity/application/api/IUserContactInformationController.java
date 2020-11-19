package com.resow.authenticationidentity.application.api;

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
public interface IUserContactInformationController {

    Object newEmail(NewEmailCommand newEmailCommand);

    Object newPhone(NewPhoneCommand newPhonelCommand);

    Object removeEmail(RemoveEmailCommand removeEmailCommand);

    Object removePhone(RemovePhonelCommand removePhoneCommand);

    Object changeEmail(ChangeEmailCommand removeEmailCommand);

    Object changePhone(ChangePhoneCommand changePhoneCommand);
}
