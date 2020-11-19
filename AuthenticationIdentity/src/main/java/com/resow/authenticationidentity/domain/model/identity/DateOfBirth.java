package com.resow.authenticationidentity.domain.model.identity;

import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Embeddable;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
@Embeddable
public class DateOfBirth {

    private LocalDate dateOfBirth;

    public DateOfBirth(LocalDate localDate) {
        this.dateOfBirth = localDate;
    }

    private DateOfBirth() {
    }

    protected LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.dateOfBirth);
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
        final DateOfBirth other = (DateOfBirth) obj;
        if (!Objects.equals(this.dateOfBirth, other.dateOfBirth)) {
            return false;
        }
        return true;
    }

}
