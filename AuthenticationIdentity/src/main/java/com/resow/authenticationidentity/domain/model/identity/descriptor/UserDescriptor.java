package com.resow.authenticationidentity.domain.model.identity.descriptor;

import com.resow.common.domain.model.descriptor.Descriptor;
import java.util.Set;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class UserDescriptor implements Descriptor {

    private String userUUID;
    private String fullName;
    private String nickname;
    private String dateOfBirth;
    private Set<String> email;
    private Set<String> phones;
    private AddressDescriptor addressDescriptor;

    public UserDescriptor(String userUUID, String fullName, String nickname, String dateOfBirth, Set<String> email, Set<String> phones, AddressDescriptor addressDescriptor) {
        this.userUUID = userUUID;
        this.fullName = fullName;
        this.nickname = nickname;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phones = phones;
        this.addressDescriptor = addressDescriptor;
    }

    public String getUserUUID() {
        return userUUID;
    }

    public String getFullName() {
        return fullName;
    }

    public String getNickname() {
        return nickname;
    }

    public Set<String> getEmails() {
        return email;
    }

    public Set<String> getPhones() {
        return phones;
    }

    public AddressDescriptor getAddressDescriptor() {
        return addressDescriptor;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public String toString() {
        return "UserDescriptor{"
                + "userUUID=" + userUUID + ", "
                + "fullName=" + fullName + ", "
                + "nickname=" + nickname + ", "
                + "email=" + email + ", "
                + "phones=" + phones + ", "
                + "addressDescriptor=" + addressDescriptor + '}';
    }

}
