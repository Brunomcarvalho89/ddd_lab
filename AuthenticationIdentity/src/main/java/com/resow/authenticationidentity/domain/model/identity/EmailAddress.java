package com.resow.authenticationidentity.domain.model.identity;

import java.util.Objects;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class EmailAddress {

    private String emailAddress;

    public EmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String value() {
        return emailAddress;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.emailAddress);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EmailAddress other = (EmailAddress) obj;
        if (!Objects.equals(this.emailAddress, other.emailAddress)) {
            return false;
        }
        return true;
    }

}
