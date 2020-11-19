package com.resow.authenticationidentity.domain.model.identity;

import com.resow.authenticationidentity.domain.model.identity.descriptor.CityDescriptor;
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
@Table(name = "city")
public class City implements com.resow.common.domain.model.BaseEntity {

    @Id
    private Long id;

    private String name;

    @OneToOne
    @JoinColumn(name = "stateID")
    private State state;

    private City() {
        super();
    }

    public City(Long id, String name, State state) {
        this();
        this.id = id;
        this.name = name;
        this.state = state;
    }

    public Long id() {
        return id;
    }

    public CityDescriptor cityDescriptor() {
        return new CityDescriptor(id, name, state.stateDescriptor());
    }

}
