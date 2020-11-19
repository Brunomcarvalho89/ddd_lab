package com.resow.authenticationidentity.application.command;

import com.resow.common.application.command.CommandBase;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class ChangeCityCommand implements CommandBase {

    private String nickname;
    private Long city;

    public ChangeCityCommand(String nickname, Long city) {
        this.nickname = nickname;
        this.city = city;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getCity() {
        return city;
    }

    public void setCity(Long city) {
        this.city = city;
    }

}
