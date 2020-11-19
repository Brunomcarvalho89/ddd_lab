package com.resow.authenticationidentity.domain.model.identity;

import com.resow.authenticationidentity.domain.model.identity.descriptor.StateDescriptor;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
@Entity
@Table(name = "state")
public class State implements com.resow.common.domain.model.BaseEntity {

    @Id
    private Long id;
    private String name;
    private String abbreviation;

    @OneToOne
    @JoinColumn(name = "countryID")
    private Country country;

    private State() {
        super();
    }

    public State(Long id, String name, String abbreviation, Country country) {
        this();
        this.id = id;
        this.name = name;
        this.abbreviation = abbreviation;
        this.country = country;
    }

    public Long id() {
        return id;
    }

    public StateDescriptor stateDescriptor() {
        return new StateDescriptor(id, name,abbreviation, country.countryDescriptor());
    }

}
