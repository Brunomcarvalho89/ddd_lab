package com.resow.authenticationidentity.application.command;

/**
 *
 * @author home
 */
public class ChangeDateOfBirthCommand {

    private String nickname;
    private String dateOfBirth;

    public ChangeDateOfBirthCommand() {
    }

    public ChangeDateOfBirthCommand(String nickname, String dateOfBirth) {
        this.nickname = nickname;
        this.dateOfBirth = dateOfBirth;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

}
