package com.resow.authenticationidentity.domain.model.identity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
@Embeddable
public class AddressComplement {

    @Column(name = "descriptionAditionalAddress")
    private String addressComplement;

    private AddressComplement() {
        super();
    }

    public AddressComplement(String addressComplement) {
        this();
        this.addressComplement = addressComplement;
    }

    protected String addressComplement() {
        return addressComplement;
    }

}
