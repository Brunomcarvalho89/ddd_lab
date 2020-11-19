package com.resow.authenticationidentity.application.service;

import com.resow.authenticationidentity.domain.model.identity.exception.ZipCodeException;

/**
 *
 * @author home
 */
public interface IZipCodeValidator {

    public boolean valid(String zipCode, String abbreviationCountry) throws ZipCodeException;

}
