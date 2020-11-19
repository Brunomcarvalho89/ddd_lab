package com.resow.authenticationidentity.application.dto.assembler;

import com.resow.authenticationidentity.application.dto.UserDTO;
import com.resow.authenticationidentity.domain.model.identity.descriptor.UserDescriptor;
import com.resow.common.application.assembler.AssemblerDTO;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public interface IAssemblerUserDTO extends AssemblerDTO<UserDescriptor, UserDTO> {

    /**
     *
     * @param userDescriptor
     * @return
     */
    @Override
    UserDTO convert(UserDescriptor userDescriptor);

}
