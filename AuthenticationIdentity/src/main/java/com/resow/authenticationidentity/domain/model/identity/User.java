package com.resow.authenticationidentity.domain.model.identity;

import com.resow.authenticationidentity.domain.model.identity.descriptor.AddressDescriptor;
import com.resow.authenticationidentity.domain.model.identity.descriptor.UserDescriptor;
import com.resow.authenticationidentity.domain.model.identity.exception.AddressDescriptionException;
import com.resow.authenticationidentity.domain.model.identity.exception.CityException;
import com.resow.authenticationidentity.domain.model.identity.exception.InvalidPasswordException;
import com.resow.authenticationidentity.domain.model.identity.exception.NumberAddressException;
import com.resow.authenticationidentity.domain.model.identity.exception.ZipCodeException;
import com.resow.common.domain.model.descriptor.Describable;
import com.resow.common.exception.GenerateHashException;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
@Entity
@Table(name = "user")
public class User implements com.resow.common.domain.model.BaseEntity, Describable<UserDescriptor> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private UserUUID userUUID;

    @Embedded
    private Person person;

    @OneToOne
    @JoinColumn(name = "loginID", nullable = true)
    private Login login;

    @OneToOne
    @JoinColumn(name = "addressID")
    private Address address;

    public User() {
    }

    public User(UserUUID userUUID, Login login, Person person, Address address) {
        this();
        this.userUUID = userUUID;
        this.login = login;
        this.address = address;
        this.person = person;
    }

    public void changeFullName(FullName fullName) {
        this.person.changeFullName(fullName);
    }

    public void changeDateOfBirth(DateOfBirth dateOfBirth) {
        this.person.changeDateOfBirth(dateOfBirth);
    }

    public Email addContact(EmailAddress emailAddress) {
        return person.addContact(emailAddress, this);
    }

    public Phone addContact(PhoneNumber phoneNumber) {
        return person.addContact(phoneNumber, this);
    }

    public Email removeContact(EmailAddress emailAddress) {
        return person.removeContact(emailAddress);
    }

    public Phone removeContact(PhoneNumber phoneNumber) {
        return person.removeContact(phoneNumber);
    }

    public void changeNickname(String username) {
        this.login.changeNickname(username);
    }

    public void changePassword(String password) throws GenerateHashException {
        this.login.changePassword(password);
    }

    public void changeAddressDescription(AddressDescription addressDescription) throws AddressDescriptionException {
        this.address.changeAddressDescription(addressDescription);
    }

    public void changeAddressComplement(AddressComplement addressComplement) {
        this.address.changeAddressComplement(addressComplement);
    }

    public void changeNumberAddress(Integer number) throws NumberAddressException {
        if (Objects.isNull(number)) {
            throw new NumberAddressException();
        }

        this.address.changeNumberAddress(number);
    }

    public void changeZipCode(ZipCode zipCode) throws ZipCodeException {
        if (Objects.isNull(zipCode)) {
            throw new ZipCodeException();
        }

        this.address.changeZipCode(zipCode);
    }

    public void changeCity(City city) throws CityException {
        if (Objects.isNull(city)) {
            throw new CityException("City must not be null.");
        }

        this.address.changeCity(city);
    }

    public void authenticate(String password) throws InvalidPasswordException {
        this.login().hasPasswordEquals(password);
    }

    public Login login() {
        return login;
    }

    public Person person() {
        return person;
    }

    public Address address() {
        return address;
    }

    public UserUUID userUUID() {
        return userUUID;
    }

    public UserDescriptor descriptor() {

        Set<String> emails = person().contact()
                .emails()
                .stream()
                .map((Email email) -> email.getEmail())
                .collect(Collectors.toSet());

        Set<String> phones = person().contact()
                .phones()
                .stream()
                .map((Phone phone) -> phone.getNumber())
                .collect(Collectors.toSet());

        AddressDescriptor addressDescriptor = this.address().addressDescriptor();

        return new UserDescriptor(userUUID().toString(),
                person.fullName().toString(),
                login.nickname(),
                this.person.dateOfBirth().getDateOfBirth().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                emails,
                phones,
                addressDescriptor
        );
    }
}
