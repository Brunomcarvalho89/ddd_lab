package com.resow.authenticationidentity.application.service.impl;

import com.resow.authenticationidentity.application.dto.UserDTO;
import com.resow.authenticationidentity.application.dto.assembler.IAssemblerUserDTO;
import com.resow.authenticationidentity.application.service.IUserDataService;
import com.resow.authenticationidentity.domain.model.identity.User;
import com.resow.authenticationidentity.domain.model.identity.UserUUID;
import com.resow.authenticationidentity.domain.model.identity.exception.UserNotFoundException;
import com.resow.authenticationidentity.domain.model.identity.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class UserDataService implements IUserDataService {

    private final UserRepository userRepository;
    private final IAssemblerUserDTO assemblerUserDTO;

    public UserDataService(UserRepository userRepository, IAssemblerUserDTO assemblerUserDTO) {
        this.userRepository = userRepository;
        this.assemblerUserDTO = assemblerUserDTO;
    }

    @Override
    public UserDTO allUserDataByNickname(String nickname) {

        User user = userRepository.findByNickName(nickname);

        if (user == null) {
            throw new UserNotFoundException("User not found");
        }

        UserDTO userDTO = this.assemblerUserDTO.convert(user.descriptor());

        return userDTO;
    }

    @Override
    public UserDTO allUserDataByUUID(String userUUID) {

        User user = userRepository.findByUUID(new UserUUID(userUUID));

        if (user == null) {
            throw new UserNotFoundException("User not found");
        }

        UserDTO userDTO = this.assemblerUserDTO.convert(user.descriptor());

        return userDTO;
    }

    @Override
    public List<UserDTO> allUserData() {
        return userRepository.findAll()
                .stream()
                .map(user -> this.assemblerUserDTO.convert(user.descriptor()))
                .collect(Collectors.toList());
    }

}
