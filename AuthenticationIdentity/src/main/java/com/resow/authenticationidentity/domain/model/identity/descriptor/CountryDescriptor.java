package com.resow.authenticationidentity.domain.model.identity.descriptor;

import com.resow.common.domain.model.descriptor.Descriptor;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class CountryDescriptor implements Descriptor {

    private Long countryID;
    private String descCountry;
    private String abbreviation;

    public CountryDescriptor(Long countryID, String descCountry, String abbreviation) {
        this.countryID = countryID;
        this.descCountry = descCountry;
        this.abbreviation = abbreviation;
    }

    public Long getCountryID() {
        return countryID;
    }

    public String getDescCountry() {
        return descCountry;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    @Override
    public String toString() {
        return "CountryDescriptor{" + "countryID=" + countryID + ", descCountry=" + descCountry + ", abbreviation=" + abbreviation + '}';
    }

}
