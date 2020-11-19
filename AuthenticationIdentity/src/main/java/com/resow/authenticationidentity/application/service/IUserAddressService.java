package com.resow.authenticationidentity.application.service;

import com.resow.authenticationidentity.application.command.ChangeCityCommand;
import com.resow.authenticationidentity.application.command.ChangeAddressComplementCommand;
import com.resow.authenticationidentity.application.command.ChangeZipCodeCommand;
import com.resow.authenticationidentity.application.command.ChangeAddressDescriptionCommand;
import com.resow.authenticationidentity.application.command.ChangeNumberAddressCommand;
import com.resow.authenticationidentity.domain.model.identity.exception.AddressDescriptionException;
import com.resow.authenticationidentity.domain.model.identity.exception.CityException;
import com.resow.authenticationidentity.domain.model.identity.exception.NumberAddressException;
import com.resow.authenticationidentity.domain.model.identity.exception.ZipCodeException;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public interface IUserAddressService {

    public void changeAddressDescription(ChangeAddressDescriptionCommand changeAddressDescription) throws AddressDescriptionException;

    public void changeAddressComplement(ChangeAddressComplementCommand changeAddressComplement);

    public void changeNumberAddress(ChangeNumberAddressCommand numberAddressCommand) throws NumberAddressException;

    public void changeZipCode(ChangeZipCodeCommand changeZipCode) throws ZipCodeException;

    public void changeCity(ChangeCityCommand changeCity) throws CityException;
}
