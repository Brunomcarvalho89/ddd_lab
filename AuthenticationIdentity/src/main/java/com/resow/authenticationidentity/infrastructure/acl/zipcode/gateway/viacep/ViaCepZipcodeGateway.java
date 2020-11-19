package com.resow.authenticationidentity.infrastructure.acl.zipcode.gateway.viacep;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.resow.authenticationidentity.domain.model.identity.exception.ZipCodeException;
import com.resow.authenticationidentity.infrastructure.acl.zipcode.gateway.ZipcodeGateway;
import com.resow.authenticationidentity.infrastructure.acl.zipcode.gateway.ZipcodeConnection;
import java.util.Optional;

/**
 *
 * @author home
 */
public class ViaCepZipcodeGateway implements ZipcodeGateway {

    private final ZipcodeConnection zipcodeConnection;

    public ViaCepZipcodeGateway(ZipcodeConnection zipcodeConnection) {
        this.zipcodeConnection = zipcodeConnection;
    }

    @Override
    public Boolean isValid(String zipcode) throws ZipCodeException {

        try {

            Optional<String> oAddress = zipcodeConnection.getAddress(zipcode);

            if (oAddress.isPresent()) {

                String address = oAddress.get();

                ViaCepAddress viaCepAddress = new ObjectMapper().readValue(address, ViaCepAddress.class);

                return !viaCepAddress.getLocalidade().isEmpty();
            }
        } catch (Exception ex) {
            throw new ZipCodeException(ex.getMessage());
        }

        return false;
    }

}
