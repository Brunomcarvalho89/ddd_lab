package com.resow.authenticationidentity.infrastructure.acl.zipcode.gateway;

import com.resow.authenticationidentity.application.service.IZipCodeValidator;
import com.resow.authenticationidentity.domain.model.identity.exception.ZipCodeException;

/**
 *
 * @author home
 */
public class ZipCodeValidator implements IZipCodeValidator {

    public ZipCodeValidator() {
    }

    @Override
    public boolean valid(String zipCode, String abbreviationCountry) throws ZipCodeException {
        return new ZipCodeGatewayDelegate(abbreviationCountry).isValid(zipCode);
    }

}
