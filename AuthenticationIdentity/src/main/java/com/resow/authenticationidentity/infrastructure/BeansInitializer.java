package com.resow.authenticationidentity.infrastructure;

import com.resow.authenticationidentity.application.dto.assembler.IAssemblerUserDTO;
import com.resow.authenticationidentity.application.dto.assembler.impl.AssemblerUserDTO;
import com.resow.authenticationidentity.application.service.IUserDataService;
import com.resow.authenticationidentity.application.service.impl.UserDataService;
import com.resow.authenticationidentity.domain.model.identity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
@Component
public class BeansInitializer {

    @Autowired
    UserRepository userRepository;

    @Bean
    IAssemblerUserDTO assemblerUserDTO() {
        return new AssemblerUserDTO();
    }

    @Bean
    IUserDataService userDataService() {
        return new UserDataService(userRepository, assemblerUserDTO());
    }

}
