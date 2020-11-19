package com.resow.authenticationidentity.application.command;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class AuthenticateByTokenCommand {

    private String token;

    public AuthenticateByTokenCommand(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

}
