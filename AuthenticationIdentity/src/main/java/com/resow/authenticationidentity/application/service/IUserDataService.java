package com.resow.authenticationidentity.application.service;

import com.resow.authenticationidentity.application.dto.UserDTO;
import java.util.List;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public interface IUserDataService {

    UserDTO allUserDataByNickname(String nickname);
    
    UserDTO allUserDataByUUID(String userUUID);
    
    List<UserDTO> allUserData();
}
