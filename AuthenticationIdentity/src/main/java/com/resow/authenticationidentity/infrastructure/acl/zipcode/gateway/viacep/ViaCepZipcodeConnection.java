package com.resow.authenticationidentity.infrastructure.acl.zipcode.gateway.viacep;

import com.resow.authenticationidentity.domain.model.identity.exception.ZipCodeException;
import com.resow.authenticationidentity.infrastructure.acl.zipcode.gateway.ZipcodeConnection;
import java.net.URI;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author home
 */
public class ViaCepZipcodeConnection implements ZipcodeConnection {

    @Override
    public Optional<String> getAddress(String cep) throws ZipCodeException{
        try {
            URI URI = new URI(String.format("https://viacep.com.br/ws/%s/json", cep));

            RestTemplate restTemplate = new RestTemplate();

            RequestEntity request = RequestEntity
                    .get(URI)
                    .accept(MediaType.APPLICATION_JSON)
                    .build();

            ResponseEntity<String> response = restTemplate.exchange(request, String.class);

            if (response.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
                throw new ZipCodeException("Invalid format for the provided zip code.");
            }
            
            String responseValue = response.getBody();
            if (responseValue.contains("erro")) {
                throw new ZipCodeException("No data for the zip code.");
            }
            
            return Optional.of(response.getBody());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }

}
