package com.resow.authenticationidentity.application.command;

import com.resow.common.application.command.CommandBase;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class ChangeEmailCommand implements CommandBase {

    private String nickname;
    private String oldEmail;
    private String newEmail;

    public ChangeEmailCommand(String nickname, String email, String oldEmail) {
        this.nickname = nickname;
        this.newEmail = email;
        this.oldEmail = oldEmail;
    }

    public String getNickname() {
        return nickname;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public String getOldEmail() {
        return oldEmail;
    }

}
