package com.resow.authenticationidentity.application.api;

import com.resow.authenticationidentity.application.command.ChangeAddressComplementCommand;
import com.resow.authenticationidentity.application.command.ChangeAddressDescriptionCommand;
import com.resow.authenticationidentity.application.command.ChangeCityCommand;
import com.resow.authenticationidentity.application.command.ChangeNumberAddressCommand;
import com.resow.authenticationidentity.application.command.ChangeZipCodeCommand;
import com.resow.authenticationidentity.domain.model.identity.exception.AddressDescriptionException;
import com.resow.authenticationidentity.domain.model.identity.exception.CityException;
import com.resow.authenticationidentity.domain.model.identity.exception.NumberAddressException;
import com.resow.authenticationidentity.domain.model.identity.exception.ZipCodeException;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public interface IUserAddressController {

    Object changeAddressDescription(ChangeAddressDescriptionCommand changeAddressDescription) throws AddressDescriptionException;

    Object changeAddressComplement(ChangeAddressComplementCommand changeAddressComplement);

    Object changeCity(ChangeCityCommand changeCityCommand) throws CityException;

    Object changeNumberAddress(ChangeNumberAddressCommand changeNumberAddressCommand) throws NumberAddressException;

    Object changeZipCode(ChangeZipCodeCommand changeZipCodeCommand) throws ZipCodeException;
}
