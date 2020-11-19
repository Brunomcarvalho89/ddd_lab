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
@Table(name = "phone")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    private String number;

    public Phone() {
    }

    public Phone(User user, String number) {
        this();
        this.user = user;
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

}
