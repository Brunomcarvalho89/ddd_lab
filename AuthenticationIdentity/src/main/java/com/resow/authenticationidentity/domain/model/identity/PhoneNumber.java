package com.resow.authenticationidentity.domain.model.identity;

import java.util.Objects;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class PhoneNumber {

    private String number;

    public PhoneNumber(String number) {
        this.number = number;
    }

    public String value() {
        return number;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.number);
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
        final PhoneNumber other = (PhoneNumber) obj;
        if (!Objects.equals(this.number, other.number)) {
            return false;
        }
        return true;
    }

}
