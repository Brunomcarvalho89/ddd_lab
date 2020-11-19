package com.resow.authenticationidentity.domain.model.identity.descriptor;

import com.resow.common.domain.model.descriptor.Descriptor;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class CityDescriptor implements Descriptor {

    private Long cityID;
    private String descCity;

    private StateDescriptor stateDescriptor;

    public CityDescriptor(Long cityID, String descCity, StateDescriptor stateDescriptor) {
        this.cityID = cityID;
        this.descCity = descCity;
        this.stateDescriptor = stateDescriptor;
    }

    public Long getCityID() {
        return cityID;
    }

    public String getDescCity() {
        return descCity;
    }

    public StateDescriptor getStateDescriptor() {
        return stateDescriptor;
    }

    @Override
    public String toString() {
        return "CityDescriptor{"
                + "cityID=" + cityID + ", "
                + "descCity=" + descCity + ", "
                + "stateDescriptor=" + stateDescriptor + '}';
    }

}
