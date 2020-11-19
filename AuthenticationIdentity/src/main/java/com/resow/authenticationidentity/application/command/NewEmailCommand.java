package com.resow.authenticationidentity.application.command;

import com.resow.common.application.command.CommandBase;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class NewEmailCommand implements CommandBase {

    private String nickname;
    private String email;

    private NewEmailCommand() {
        super();
    }

    public NewEmailCommand(String nickname, String email) {
        this();
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
