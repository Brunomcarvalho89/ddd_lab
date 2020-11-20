package com.resow.emailsender.application.dto;

import com.resow.common.application.dto.AbstractHateoasBaseDTO;
import java.util.Set;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class UserDTO extends AbstractHateoasBaseDTO {

    private String fullName;
    private String nickname;
    private Set<String> email;
    private Set<String> phones;
    private String addressDescription;
    private String addressComplement;
    private long number;
    private String zipCode;
    private long cityID;

    public UserDTO() {
        super();
    }

    public UserDTO(
            String fullName,
            String nickname,
            Set<String> email,
            Set<String> phones,
            String addressDescription,
            String addressComplement,
            long number,
            String zipCode,
            long cityID
    ) {
        this();
        this.fullName = fullName;
        this.nickname = nickname;
        this.email = email;
        this.phones = phones;
        this.addressDescription = addressDescription;
        this.addressComplement = addressComplement;
        this.number = number;
        this.zipCode = zipCode;
        this.cityID = cityID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Set<String> getEmail() {
        return email;
    }

    public void setEmail(Set<String> email) {
        this.email = email;
    }

    public Set<String> getPhones() {
        return phones;
    }

    public void setPhones(Set<String> phones) {
        this.phones = phones;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAddressDescription() {
        return addressDescription;
    }

    public void setAddressDescription(String addressDescription) {
        this.addressDescription = addressDescription;
    }

    public String getAddressComplement() {
        return addressComplement;
    }

    public void setAddressComplement(String addressComplement) {
        this.addressComplement = addressComplement;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public long getCityID() {
        return cityID;
    }

    public void setCityID(long cityID) {
        this.cityID = cityID;
    }

    @Override
    public String toString() {
        return "UserDTO {"
                + "fullName=" + fullName
                + ", nickname=" + nickname
                + ", email=" + email
                + ", phones=" + phones
                + ", addressDescription=" + addressDescription
                + ", addressComplement=" + addressComplement
                + ", number=" + number
                + ", zipCode=" + zipCode
                + ", cityID=" + cityID + '}';
    }

}
