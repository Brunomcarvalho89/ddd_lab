package com.resow.authenticationidentity.domain.model.identity;

import com.resow.authenticationidentity.domain.model.identity.descriptor.CountryDescriptor;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
@Entity
@Table(name = "country")
public class Country implements com.resow.common.domain.model.BaseEntity {

    @Id
    private Long id;
    private String name;
    private String abbreviation;

    private Country() {
        super();
    }

    public Country(Long id, String name, String abbreviation) {
        this();
        this.id = id;
        this.name = name;
        this.abbreviation = abbreviation;
    }

    public Long id() {
        return id;
    }

    public CountryDescriptor countryDescriptor() {
        return new CountryDescriptor(id, name, abbreviation);
    }

}
