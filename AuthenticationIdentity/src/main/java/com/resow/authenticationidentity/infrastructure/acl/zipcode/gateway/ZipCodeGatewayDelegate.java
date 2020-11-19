package com.resow.authenticationidentity.infrastructure.acl.zipcode.gateway;

import com.resow.authenticationidentity.domain.model.identity.exception.ZipCodeException;
import com.resow.authenticationidentity.infrastructure.acl.zipcode.gateway.viacep.ViaCepZipcodeConnection;
import com.resow.authenticationidentity.infrastructure.acl.zipcode.gateway.viacep.ViaCepZipcodeGateway;
import com.resow.authenticationidentity.infrastructure.acl.zipcode.gateway.zippopotam.ZippopotamZipCodeConnection;
import com.resow.authenticationidentity.infrastructure.acl.zipcode.gateway.zippopotam.ZippopotamZipCodeGateway;

/**
 *
 * @author home
 */
public class ZipCodeGatewayDelegate implements ZipcodeGateway {

    private ZipcodeGateway zipcodeGateway;

    public ZipCodeGatewayDelegate(String abbreviation) {

        switch (abbreviation) {
            case "br":
                this.zipcodeGateway = new ViaCepZipcodeGateway(new ViaCepZipcodeConnection());
                break;
            default:
                this.zipcodeGateway = new ZippopotamZipCodeGateway(new ZippopotamZipCodeConnection(abbreviation));
                break;
        }
    }

    @Override
    public Boolean isValid(String zipcode) throws ZipCodeException {
        return this.zipcodeGateway.isValid(zipcode);
    }

}
