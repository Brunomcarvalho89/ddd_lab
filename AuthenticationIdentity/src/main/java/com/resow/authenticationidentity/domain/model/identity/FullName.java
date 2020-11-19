package com.resow.authenticationidentity.domain.model.identity;

import com.resow.authenticationidentity.domain.model.identity.exception.NameInvalidException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.Embeddable;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
@Embeddable
public class FullName {

    private String firstName;
    private String lastName;

    private FullName() {
    }

    private FullName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static FullNameBuilder builder() {
        return new FullNameBuilder();
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + Objects.hashCode(this.firstName);
        hash = 43 * hash + Objects.hashCode(this.lastName);
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
        final FullName other = (FullName) obj;
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        return true;
    }

    public static class FullNameBuilder {

        private String firstName = "";
        private String lastName = "";

        private FullNameBuilder() {
        }

        public FullNameBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public FullNameBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        /**
         *
         * @param name - Full name to build the user name.
         * @return
         *
         * @exception NameInvalidException - If the given parameter does not
         * correspond to a name and a last name.
         */
        public FullNameBuilder withFullName(String name) throws NameInvalidException {

            Pattern patternNames = Pattern.compile("[a-zA-Z0-9]+(?=\\s)");

            Matcher matcher = patternNames.matcher(name);

            if (matcher.find()) {

                String group = matcher.group(0);

                this.firstName = group;

                this.lastName = name.substring(this.firstName.length() + 1, name.length());

                return this;
            }

            throw new NameInvalidException("Name invalid.");
        }

        public FullName build() {
            FullName fullName = new FullName(this.firstName, this.lastName);
            return fullName;
        }

    }

}
