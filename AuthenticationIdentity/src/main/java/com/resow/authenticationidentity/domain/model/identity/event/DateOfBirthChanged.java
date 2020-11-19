package com.resow.authenticationidentity.domain.model.identity.event;

import java.util.Objects;
import com.resow.common.event.BaseEvent;
import java.time.LocalDate;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class DateOfBirthChanged implements BaseEvent {

    private String userUUID;
    private String newDateOfBirth;
    private String oldDateOfBirth;

    public DateOfBirthChanged(String userUUID, String newDateOfBirth, String oldDateOfBirth) {
        this.userUUID = userUUID;
        this.newDateOfBirth = newDateOfBirth;
        this.oldDateOfBirth = oldDateOfBirth;
    }

    public String getUserUUID() {
        return userUUID;
    }

    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }

    public String getNewDateOfBirth() {
        return newDateOfBirth;
    }

    public void setNewDateOfBirth(String newDateOfBirth) {
        this.newDateOfBirth = newDateOfBirth;
    }

    public String getOldDateOfBirth() {
        return oldDateOfBirth;
    }

    public void setOldDateOfBirth(String oldDateOfBirth) {
        this.oldDateOfBirth = oldDateOfBirth;
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
        final DateOfBirthChanged other = (DateOfBirthChanged) obj;
        if (!Objects.equals(this.newDateOfBirth, other.newDateOfBirth)) {
            return false;
        }
        if (!Objects.equals(this.oldDateOfBirth, other.oldDateOfBirth)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.newDateOfBirth);
        hash = 17 * hash + Objects.hashCode(this.oldDateOfBirth);
        return hash;
    }

    @Override
    public String toString() {
        return "{newFullName: " + newDateOfBirth + ",oldFullname:" + oldDateOfBirth + "}";
    }

}
