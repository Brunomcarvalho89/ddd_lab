package com.resow.authenticationidentity.domain.model.identity;

import com.resow.authenticationidentity.domain.BeanFacades;
import com.resow.common.exception.GenerateHashException;
import java.util.Objects;
import javax.persistence.Embeddable;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
@Embeddable
public class Password {

    private String password;

    public static Password of(String password) throws GenerateHashException {
        return new Password(Password.protect(password));
    }

    private Password() {
    }

    private Password(String password) {
        this.password = password;
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
        final Password other = (Password) obj;
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }

    public Boolean isValidPassword(String password) {

        return BeanFacades.instance()
                .getHashFunction()
                .verify(this.password, password);
    }

    private static String protect(String password) throws GenerateHashException {

        try {

            return BeanFacades
                    .instance()
                    .getHashFunction()
                    .hash(password);

        } catch (Exception ex) {
            throw new GenerateHashException();
        }
    }
}
