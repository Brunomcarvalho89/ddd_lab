package com.resow.authenticationidentity.domain.model.identity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
@Embeddable
public class AddressDescription {

    @Column(name = "descriptionAddress")
    private String addressDescription;

    private AddressDescription() {
        super();
    }

    public AddressDescription(String addressDescription) {
        this();
        this.addressDescription = addressDescription;
    }

    public String addressDescription() {
        return addressDescription;
    }

}
