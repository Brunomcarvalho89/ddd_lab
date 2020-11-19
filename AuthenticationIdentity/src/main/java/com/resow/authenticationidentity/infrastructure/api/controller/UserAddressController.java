package com.resow.authenticationidentity.infrastructure.api.controller;

import com.resow.authenticationidentity.application.api.IUserAddressController;
import com.resow.authenticationidentity.application.command.ChangeAddressComplementCommand;
import com.resow.authenticationidentity.application.command.ChangeAddressDescriptionCommand;
import com.resow.authenticationidentity.application.command.ChangeCityCommand;
import com.resow.authenticationidentity.application.command.ChangeNumberAddressCommand;
import com.resow.authenticationidentity.application.command.ChangeZipCodeCommand;
import com.resow.authenticationidentity.application.service.impl.UserAddressService;
import com.resow.authenticationidentity.domain.model.identity.exception.CityException;
import com.resow.authenticationidentity.domain.model.identity.exception.NumberAddressException;
import com.resow.authenticationidentity.domain.model.identity.exception.ZipCodeException;
import com.resow.authenticationidentity.infrastructure.repository.hibernate.CityRepository;
import com.resow.authenticationidentity.infrastructure.repository.hibernate.UserRepository;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.resow.authenticationidentity.application.service.IUserAddressService;
import com.resow.authenticationidentity.domain.model.identity.exception.AddressDescriptionException;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
@RestController
@RequestMapping("/user/address")
public class UserAddressController implements IUserAddressController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CityRepository cityRepository;

    private IUserAddressService addressUserService;

    @PostConstruct
    private void init() {
        this.addressUserService = new UserAddressService(userRepository, cityRepository);
    }

    @Override
    @PatchMapping(path = "/addressDescription", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity changeAddressDescription(@RequestBody ChangeAddressDescriptionCommand changeAddressDescription) throws AddressDescriptionException {
        this.addressUserService.changeAddressDescription(changeAddressDescription);
        return ResponseEntity.ok().build();
    }

    @Override
    @PatchMapping(path = "/addressComplement", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity changeAddressComplement(@RequestBody ChangeAddressComplementCommand changeAddressComplement) {
        this.addressUserService.changeAddressComplement(changeAddressComplement);
        return ResponseEntity.ok().build();
    }

    @Override
    @PatchMapping(path = "/zipcode", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity changeZipCode(@RequestBody ChangeZipCodeCommand changeZipCodeCommand) throws ZipCodeException {
        this.addressUserService.changeZipCode(changeZipCodeCommand);
        return ResponseEntity.ok().build();
    }

    @Override
    @PatchMapping(path = "/city", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity changeCity(@RequestBody ChangeCityCommand changeCityCommand) throws CityException {
        this.addressUserService.changeCity(changeCityCommand);
        return ResponseEntity.ok().build();
    }

    @Override
    @PatchMapping(path = "/number", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object changeNumberAddress(@RequestBody ChangeNumberAddressCommand changeNumberAddressCommand) throws NumberAddressException {
        this.addressUserService.changeNumberAddress(changeNumberAddressCommand);
        return ResponseEntity.ok().build();
    }
}
