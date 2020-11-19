package com.resow.authenticationidentity.application.command;

import com.resow.common.application.command.CommandBase;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class ChangeNumberAddressCommand implements CommandBase {

    private String nickname;
    private Integer newNumberAddress;

    private ChangeNumberAddressCommand() {
    }

    public ChangeNumberAddressCommand(String nickname, Integer newNumberAddress) {
        this.nickname = nickname;
        this.newNumberAddress = newNumberAddress;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getNewNumberAddress() {
        return newNumberAddress;
    }

    public void setNewNumberAddress(Integer newNumberAddress) {
        this.newNumberAddress = newNumberAddress;
    }

}
