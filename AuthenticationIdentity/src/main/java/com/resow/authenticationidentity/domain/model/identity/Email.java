package com.resow.authenticationidentity.domain.model.identity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
@Entity
@Table(name = "email")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    private String email;

    private Boolean primaryEmail;

    protected Email() {
        super();
    }

    protected Email(User user, String email, Boolean primaryEmail) {
        this();
        this.user = user;
        this.email = email;
        this.primaryEmail = primaryEmail;
    }

    protected Email(User user, String email) {
        this(user, email, Boolean.FALSE);
    }

    public String getEmail() {
        return email;
    }

    public Boolean isPrimaryEmail() {
        return primaryEmail;
    }

}
