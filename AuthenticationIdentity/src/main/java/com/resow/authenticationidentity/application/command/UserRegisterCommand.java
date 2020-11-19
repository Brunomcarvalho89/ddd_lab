package com.resow.authenticationidentity.application.command;

import com.resow.common.application.command.CommandBase;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class UserRegisterCommand implements CommandBase {

    private String nickName;
    private String password;
    private String name;
    private String email;
    private String addressDescription;
    private String addressComplement;
    private Integer number;
    private String zipCode;
    private Long city;

    private UserRegisterCommand() {
    }

    public UserRegisterCommand(String nickName,
            String password,
            String name,
            String email,
            String addressDescription,
            String addressComplement,
            Integer number,
            String zipcode,
            Long city) {
        this();
        this.nickName = nickName;
        this.password = password;
        this.name = name;
        this.email = email;
        this.addressDescription = addressDescription;
        this.addressComplement = addressComplement;
        this.number = number;
        this.zipCode = zipcode;
        this.city = city;
    }

    public String getNickName() {
        return nickName;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddressDescription() {
        return addressDescription;
    }

    public String getAddressComplement() {
        return addressComplement;
    }

    public Integer getNumber() {
        return number;
    }

    public String getZipCode() {
        return zipCode;
    }

    public Long getCity() {
        return city;
    }
}
