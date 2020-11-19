package com.resow.authenticationidentity.domain.model.identity;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
@Embeddable
public class Person {

    @Embedded
    private FullName fullName;

    @Embedded
    private DateOfBirth dateOfBirth;

    @Embedded
    private ContactInformation contact;

    public Person() {
        this.contact = new ContactInformation();
    }

    public Person(FullName fullName, DateOfBirth dateOfBirth) {
        this();
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
    }

    protected void changeFullName(FullName fullName) {
        this.fullName = fullName;
    }

    protected void changeDateOfBirth(DateOfBirth dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    protected Email addContact(EmailAddress emailAddress, User user) {
        return contact().add(emailAddress, user);
    }

    protected Phone addContact(PhoneNumber phoneNumber, User user) {
        return contact().add(phoneNumber, user);
    }

    protected Email removeContact(EmailAddress emailAddress) {
        return contact().remove(emailAddress);
    }

    protected Phone removeContact(PhoneNumber phoneNumber) {
        return contact().remove(phoneNumber);
    }

    protected ContactInformation contact() {
        return contact;
    }

    protected FullName fullName() {
        return fullName;
    }

    protected DateOfBirth dateOfBirth() {
        return dateOfBirth;
    }

}
