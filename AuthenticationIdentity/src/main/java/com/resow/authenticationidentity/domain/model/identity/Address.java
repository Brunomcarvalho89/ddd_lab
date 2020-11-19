package com.resow.authenticationidentity.domain.model.identity;

import com.resow.authenticationidentity.domain.model.identity.descriptor.AddressDescriptor;
import com.resow.authenticationidentity.domain.model.identity.exception.AddressDescriptionException;
import com.resow.authenticationidentity.domain.model.identity.exception.CityException;
import com.resow.authenticationidentity.domain.model.identity.exception.NumberAddressException;
import com.resow.authenticationidentity.domain.model.identity.exception.ZipCodeException;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private AddressDescription addressDescription;

    @Embedded
    private AddressComplement addressComplement;

    @Column(name = "number")
    private Integer numberAddress;

    @Embedded
    private ZipCode zipCode;

    @OneToOne
    @JoinColumn(name = "cityID")
    private City city;

    private Address(AddressDescription addressDescription, AddressComplement addressComplement, Integer numberAddress, ZipCode zipCode, City city) {
        super();
        this.addressDescription = addressDescription;
        this.addressComplement = addressComplement;
        this.numberAddress = numberAddress;
        this.zipCode = zipCode;
        this.city = city;
    }

    private Address() {
    }

    protected void changeAddressDescription(AddressDescription addressDescription) throws AddressDescriptionException {

        if (Objects.isNull(addressDescription)) {
            throw new AddressDescriptionException();
        }

        this.addressDescription = addressDescription;
    }

    protected void changeAddressComplement(AddressComplement addressComplement) {
        this.addressComplement = addressComplement;
    }

    protected void changeNumberAddress(Integer number) throws NumberAddressException {
        this.numberAddress = number;
    }

    protected void changeZipCode(ZipCode zipCode) throws ZipCodeException {
        this.zipCode = zipCode;
    }

    protected void changeCity(City city) throws CityException {
        this.city = city;
    }

    public AddressDescriptor addressDescriptor() {
        return new AddressDescriptor(
                addressDescription.addressDescription(),
                addressComplement.addressComplement(),
                numberAddress,
                zipCode.code(),
                city.cityDescriptor());
    }

    public static AddressBuilder builder() {
        return new AddressBuilder();
    }

    public static class AddressBuilder {

        private String addressDescription;
        private String addressComplement;
        private Integer number;
        private String zipCode;
        private City city;

        public AddressBuilder() {
        }

        public AddressBuilder withAddressDescription(String addressDescription) {
            this.addressDescription = addressDescription == null ? "" : addressDescription;
            return this;
        }

        public AddressBuilder withAddressComplement(String addressComplement) {
            this.addressComplement = addressComplement == null ? "" : addressComplement;
            return this;
        }

        public AddressBuilder withNumber(Integer number) {
            this.number = number == null ? 0 : number;
            return this;
        }

        public AddressBuilder withZipCode(String zipcode) {
            this.zipCode = zipcode == null ? "" : zipcode;
            return this;
        }

        public AddressBuilder withCity(City city) {
            this.city = city;
            return this;
        }

        public Address build() {
            return new Address(
                    new AddressDescription(this.addressDescription),
                    new AddressComplement(this.addressComplement),
                    number,
                    new ZipCode(this.zipCode),
                    this.city);
        }
    }
}
