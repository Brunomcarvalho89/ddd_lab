package com.resow.authenticationidentity.infrastructure.acl.zipcode.gateway;

import com.resow.authenticationidentity.domain.model.identity.exception.ZipCodeException;

/**
 *
 * @author home
 */
public interface ZipcodeGateway {

    Boolean isValid(String zipcode) throws ZipCodeException;

}
