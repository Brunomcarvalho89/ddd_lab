package com.resow.authenticationidentity.domain.model.identity;

import com.resow.authenticationidentity.domain.model.identity.exception.EmailNotFoundException;
import com.resow.authenticationidentity.domain.model.identity.exception.PhoneNotFoundException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
@Embeddable
public class ContactInformation {

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Email> emails;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Phone> phones;

    public ContactInformation() {
        emails = new HashSet<>();
        phones = new HashSet<>();
    }

    public Email add(EmailAddress emailAddress, User user) {

        Email email = new Email(user, emailAddress.value(), !this.hasPrimaryEmail());

        emails.add(email);

        return email;
    }

    public Phone add(PhoneNumber phoneNumber, User user) {

        Phone phone = new Phone(user, phoneNumber.value());

        phones.add(phone);

        return phone;
    }

    public Email remove(EmailAddress emailAdress) {

        Email email = emails.stream()
                .filter(emailFilter -> Objects.equals(emailFilter.getEmail(), emailAdress.value()))
                .findFirst()
                .orElse(null);

        if (email == null) {
            throw new EmailNotFoundException();
        }

        this.emails.remove(email);

        return email;
    }

    public Phone remove(PhoneNumber phoneNumber) {
        Phone phone = phones.stream()
                .filter(phoneFilter -> Objects.equals(phoneFilter.getNumber(), phoneNumber.value()))
                .findFirst()
                .orElse(null);

        if (phone == null) {
            throw new PhoneNotFoundException();
        }

        this.phones.remove(phone);

        return phone;
    }

    public Set<Email> emails() {
        return emails;
    }

    public Set<Phone> phones() {
        return phones;
    }

    private Boolean hasPrimaryEmail() {
        return this.emails.stream().anyMatch(item -> item.isPrimaryEmail());
    }

}
