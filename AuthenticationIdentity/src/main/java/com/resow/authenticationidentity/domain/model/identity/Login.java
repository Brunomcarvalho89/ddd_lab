package com.resow.authenticationidentity.domain.model.identity;

import com.resow.authenticationidentity.domain.model.identity.exception.InvalidPasswordException;
import com.resow.common.exception.GenerateHashException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
@Entity
@Table(name = "login")
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickName;

    @Embedded
    private Password password;

    private Login() {
        super();
    }

    private Login(String nickName, String password) throws GenerateHashException {
        this();
        this.nickName = nickName;
        this.password = Password.of(password);
    }

    protected void changeNickname(String nickname) {
        this.nickName = nickname;
    }

    protected void changePassword(String password) throws GenerateHashException {
        this.password = Password.of(password);
    }

    public void hasPasswordEquals(String password) throws InvalidPasswordException {
        if (!this.password.isValidPassword(password)) {
            throw new InvalidPasswordException("Invalid password");
        }
    }

    public static LoginCredentialsBuilder builder() {
        return new LoginCredentialsBuilder();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.nickName);
        hash = 67 * hash + Objects.hashCode(this.password);
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
        final Login other = (Login) obj;
        if (!Objects.equals(this.nickName, other.nickName)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }

    public String nickname() {
        return nickName;
    }

    public static class LoginCredentialsBuilder {

        private final Pattern patternPassword = Pattern.compile("(A-Z0-9)");//TODO: Criar pattern de senha

        private String nickName = "";
        private String password = "";

        private LoginCredentialsBuilder() {
        }

        public LoginCredentialsBuilder withNickName(String nickName) {
            this.nickName = nickName;
            return this;
        }

        public LoginCredentialsBuilder fromEmail(String email) {

            String emailName = "";

            Pattern pattern = Pattern.compile("[a-zA-Z0-9]*(?=@)");//TODO: Ajustar para ver porque esta colocando caracter esquisito

            Matcher matcher = pattern.matcher(email);

            if (matcher.find()) {
                emailName = matcher.group(0);
            }

            return this.withNickName(emailName);
        }

        public LoginCredentialsBuilder withPassword(String password) {

            this.password = password;

            return this;
        }

        public Login build() throws GenerateHashException {

            Login login = new Login(this.nickName, this.password);

            return login;
        }

    }

}
