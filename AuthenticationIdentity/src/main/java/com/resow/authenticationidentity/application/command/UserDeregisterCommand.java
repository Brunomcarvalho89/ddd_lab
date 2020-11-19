package com.resow.authenticationidentity.application.command;

import com.resow.common.application.command.CommandBase;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class UserDeregisterCommand implements CommandBase {

    private String nickName;

    private UserDeregisterCommand() {
    }

    public UserDeregisterCommand(String nickName) {
        this();
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }
}
