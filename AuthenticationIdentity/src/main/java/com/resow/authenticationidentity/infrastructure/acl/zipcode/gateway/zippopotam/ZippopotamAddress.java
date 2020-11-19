package com.resow.authenticationidentity.infrastructure.acl.zipcode.gateway.zippopotam;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 *
 * @author home
 */
public class ZippopotamAddress {

    @JsonProperty("post code")
    private String postCode;

    private String country;

    @JsonProperty("country abbreviation")
    private String countryAbbreviation;

    public List<Place> places;

    public ZippopotamAddress() {
    }

    public ZippopotamAddress(String postCode, String country, String countryAbbreviation, List<Place> places) {
        this.postCode = postCode;
        this.country = country;
        this.countryAbbreviation = countryAbbreviation;
        this.places = places;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryAbbreviation() {
        return countryAbbreviation;
    }

    public void setCountryAbbreviation(String countryAbbreviation) {
        this.countryAbbreviation = countryAbbreviation;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    public static class Place {

        @JsonProperty("place name")
        private String placeName;

        private String longitude;

        private String state;

        @JsonProperty("state abbreviation")
        private String stateAbbrebiation;

        private String latitude;

        public Place() {
        }

        public Place(String placeName, String longitude, String state, String stateAbbrebiation, String latitude) {
            this.placeName = placeName;
            this.longitude = longitude;
            this.state = state;
            this.stateAbbrebiation = stateAbbrebiation;
            this.latitude = latitude;
        }

        public String getPlaceName() {
            return placeName;
        }

        public void setPlaceName(String placeName) {
            this.placeName = placeName;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getStateAbbrebiation() {
            return stateAbbrebiation;
        }

        public void setStateAbbrebiation(String stateAbbrebiation) {
            this.stateAbbrebiation = stateAbbrebiation;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

    }

    @Override
    public String toString() {
        return "ZippopotamAddress{"
                + "postCode=" + postCode
                + ", country=" + country
                + ", countryAbbreviation="
                + countryAbbreviation
                + ", places=" + places + '}';
    }

}
