package com.resow.authenticationidentity.infrastructure.acl.zipcode.gateway.zippopotam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.resow.authenticationidentity.domain.model.identity.exception.ZipCodeException;
import com.resow.authenticationidentity.infrastructure.acl.zipcode.gateway.ZipcodeGateway;
import com.resow.authenticationidentity.infrastructure.acl.zipcode.gateway.ZipcodeConnection;
import java.util.Optional;

/**
 *
 * @author home
 */
public class ZippopotamZipCodeGateway implements ZipcodeGateway {

    private final ZipcodeConnection zipcodeConnection;

    public ZippopotamZipCodeGateway(ZipcodeConnection zipcodeConnection) {
        this.zipcodeConnection = zipcodeConnection;
    }

    @Override
    public Boolean isValid(String zipcode) throws ZipCodeException {

        try {

            Optional<String> oAddress = zipcodeConnection.getAddress(zipcode);

            if (oAddress.isPresent()) {

                String address = oAddress.get();

                ZippopotamAddress zippopotamAddress = new ObjectMapper().readValue(address, ZippopotamAddress.class);

                return !zippopotamAddress.getPlaces().isEmpty();
            }
        } catch (Exception ex) {
            throw new ZipCodeException(ex.getMessage());
        }

        return false;
    }

}
