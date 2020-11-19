package com.resow.authenticationidentity.application.command;

import com.resow.common.application.command.CommandBase;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class ChangeAddressComplementCommand implements CommandBase {

    private String nickname;
    private String AddressComplement;

    public ChangeAddressComplementCommand(String nickname, String AddressComplementCommand) {
        this.nickname = nickname;
        this.AddressComplement = AddressComplementCommand;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAddressComplement() {
        return AddressComplement;
    }

    public void setAddressComplement(String AddressComplement) {
        this.AddressComplement = AddressComplement;
    }

}
