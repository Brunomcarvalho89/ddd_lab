package com.resow.authenticationidentity.infrastructure.acl.zipcode.gateway;

import com.resow.authenticationidentity.domain.model.identity.exception.ZipCodeException;
import java.util.Optional;

/**
 *
 * @author home
 */
public interface ZipcodeConnection {
    
    public Optional<String> getAddress(String cep) throws ZipCodeException;
}
