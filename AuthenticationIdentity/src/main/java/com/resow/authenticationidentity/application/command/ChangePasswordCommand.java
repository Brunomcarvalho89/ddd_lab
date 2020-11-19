package com.resow.authenticationidentity.application.command;

/**
 *
 * @author home
 */
public class ChangePasswordCommand {

    private String nickname;
    private String newPassoword;

    public ChangePasswordCommand() {
    }

    public ChangePasswordCommand(String nickname, String newPassoword) {
        this.nickname = nickname;
        this.newPassoword = newPassoword;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNewPassoword() {
        return newPassoword;
    }

    public void setNewPassoword(String newPassoword) {
        this.newPassoword = newPassoword;
    }

}
