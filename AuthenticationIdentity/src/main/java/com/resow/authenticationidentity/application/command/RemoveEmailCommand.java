package com.resow.authenticationidentity.application.command;

import com.resow.common.application.command.CommandBase;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class RemoveEmailCommand implements CommandBase {

    private String nickname;
    private String email;

    public RemoveEmailCommand(String nickname, String email) {
        this.nickname = nickname;
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

}
