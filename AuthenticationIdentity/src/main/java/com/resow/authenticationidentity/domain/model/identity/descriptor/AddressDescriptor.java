package com.resow.authenticationidentity.domain.model.identity.descriptor;

import com.resow.common.domain.model.descriptor.Descriptor;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class AddressDescriptor implements Descriptor {

    private String addressDescription;
    private String addressComplement;
    private Integer number;
    private String zipCode;
    private CityDescriptor city;

    public AddressDescriptor(String addressDescription, String addressComplement, Integer number, String zipCode, CityDescriptor city) {
        this.addressDescription = addressDescription;
        this.addressComplement = addressComplement;
        this.number = number;
        this.zipCode = zipCode;
        this.city = city;
    }

    public String getAddressDescription() {
        return addressDescription;
    }

    public String getAddressComplement() {
        return addressComplement;
    }

    public String getZipCode() {
        return zipCode;
    }

    public CityDescriptor getCity() {
        return city;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

}
