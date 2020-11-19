package com.resow.authenticationidentity.domain.model.identity.event;

import java.util.Objects;
import com.resow.common.event.BaseEvent;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class FullNameChanged implements BaseEvent {

    private String userUUID;
    private String newFullName;
    private String oldFullName;

    public FullNameChanged(String newFullName, String oldFullName, String userUUID) {
        this.newFullName = newFullName;
        this.oldFullName = oldFullName;
        this.userUUID = userUUID;
    }

    public String getNewFullName() {
        return newFullName;
    }

    public String getOldFullName() {
        return oldFullName;
    }

    public String getUserUUID() {
        return userUUID;
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
        final FullNameChanged other = (FullNameChanged) obj;
        if (!Objects.equals(this.newFullName, other.newFullName)) {
            return false;
        }
        if (!Objects.equals(this.oldFullName, other.oldFullName)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.newFullName);
        hash = 17 * hash + Objects.hashCode(this.oldFullName);
        return hash;
    }

    @Override
    public String toString() {
        return "{newFullName: " + newFullName + ",oldFullname:" + oldFullName + "}";
    }

}
