package com.resow.authenticationidentity.application.command;

import com.resow.common.application.command.CommandBase;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class NewPhoneCommand implements CommandBase {

    private String nickname;
    private String phone;

    public NewPhoneCommand(String nickname, String phone) {
        this.nickname = nickname;
        this.phone = phone;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPhone() {
        return phone;
    }

}
