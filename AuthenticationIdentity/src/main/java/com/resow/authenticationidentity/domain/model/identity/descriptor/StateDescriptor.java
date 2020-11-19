package com.resow.authenticationidentity.domain.model.identity.descriptor;

import com.resow.common.domain.model.descriptor.Descriptor;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class StateDescriptor implements Descriptor {

    private Long stateID;
    private String descState;
    private String abbreviation;

    private CountryDescriptor countryDescriptor;

    public StateDescriptor(Long stateID, String descState, String abbreviation, CountryDescriptor countryDescriptor) {
        this.stateID = stateID;
        this.descState = descState;
        this.abbreviation = abbreviation;
        this.countryDescriptor = countryDescriptor;
    }

    public Long getStateID() {
        return stateID;
    }

    public String getDescState() {
        return descState;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public CountryDescriptor getCountryDescriptor() {
        return countryDescriptor;
    }

    @Override
    public String toString() {
        return "StateDescriptor{" + "stateID=" + stateID + ", descState=" + descState + ", abbreviation=" + abbreviation + ", countryDescriptor=" + countryDescriptor + '}';
    }

}
