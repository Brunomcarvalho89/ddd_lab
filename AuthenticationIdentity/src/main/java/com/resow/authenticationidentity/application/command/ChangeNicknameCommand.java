package com.resow.authenticationidentity.application.command;

/**
 *
 * @author home
 */
public class ChangeNicknameCommand {

    private String oldNickname;
    private String newNickname;

    public ChangeNicknameCommand() {
    }

    public ChangeNicknameCommand(String oldNickname, String newNickname) {
        this.oldNickname = oldNickname;
        this.newNickname = newNickname;
    }

    public String getOldNickname() {
        return oldNickname;
    }

    public void setOldNickname(String oldNickname) {
        this.oldNickname = oldNickname;
    }

    public String getNewNickname() {
        return newNickname;
    }

    public void setNewNickname(String newNickname) {
        this.newNickname = newNickname;
    }

}
