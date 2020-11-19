package com.resow.authenticationidentity.application.dto.assembler.impl;

import com.resow.authenticationidentity.application.dto.assembler.IAssemblerUserDTO;
import com.resow.authenticationidentity.application.dto.UserDTO;
import com.resow.authenticationidentity.domain.model.identity.descriptor.UserDescriptor;
import org.springframework.stereotype.Component;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
@Component
public class AssemblerUserDTO implements IAssemblerUserDTO {

    @Override
    public UserDTO convert(UserDescriptor userDescriptor) {

        UserDTO userDTO = new UserDTO(
                userDescriptor.getFullName(),
                userDescriptor.getNickname(),
                userDescriptor.getEmails(),
                userDescriptor.getPhones(),
                userDescriptor.getAddressDescriptor().getAddressDescription(),
                userDescriptor.getAddressDescriptor().getAddressComplement(),
                userDescriptor.getAddressDescriptor().getNumber(),
                userDescriptor.getAddressDescriptor().getZipCode(),
                userDescriptor.getAddressDescriptor().getCity().getCityID()
        );

       

        return userDTO;
    }

}
