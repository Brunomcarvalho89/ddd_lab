package com.resow.authenticationidentity.application.command;

/**
 *
 * @author home
 */
public class ChangeFullnameCommand {

    private String nickname;
    private String newfullname;
    private String oldfullname;

    public ChangeFullnameCommand() {
    }

    public ChangeFullnameCommand(String nickname, String newfullname, String oldfullname) {
        this.nickname = nickname;
        this.newfullname = newfullname;
        this.oldfullname = oldfullname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNewfullname() {
        return newfullname;
    }

    public void setNewfullname(String newfullname) {
        this.newfullname = newfullname;
    }

    public String getOldfullname() {
        return oldfullname;
    }

    public void setOldfullname(String oldfullname) {
        this.oldfullname = oldfullname;
    }

}
