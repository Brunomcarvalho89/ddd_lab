package com.resow.authenticationidentity.application.command;

import com.resow.common.application.command.CommandBase;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class ChangePhoneCommand implements CommandBase {

    private String nickname;
    private String oldPhone;
    private String newPhone;

    public ChangePhoneCommand(String nickname, String newPhone, String oldPhone) {
        this.nickname = nickname;
        this.newPhone = newPhone;
        this.oldPhone = oldPhone;
    }

    public String getNickname() {
        return nickname;
    }

    public String getNewPhone() {
        return newPhone;
    }

    public String getOldPhone() {
        return oldPhone;
    }

}
