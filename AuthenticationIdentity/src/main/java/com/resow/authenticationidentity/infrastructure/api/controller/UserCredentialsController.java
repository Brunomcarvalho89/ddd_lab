package com.resow.authenticationidentity.infrastructure.api.controller;

import com.resow.authenticationidentity.application.api.IUserCredentialsController;
import com.resow.authenticationidentity.application.command.ChangeDateOfBirthCommand;
import com.resow.authenticationidentity.application.command.ChangeFullnameCommand;
import com.resow.authenticationidentity.application.command.ChangeNicknameCommand;
import com.resow.authenticationidentity.application.command.ChangePasswordCommand;
import com.resow.authenticationidentity.application.service.IUserAuthenticationByPasswordAndGenerateTokenService;
import com.resow.authenticationidentity.application.service.IUserAuthenticationByPasswordService;
import com.resow.authenticationidentity.application.service.IUserCredentialsService;
import com.resow.authenticationidentity.application.service.impl.UserCredentialsService;
import com.resow.authenticationidentity.domain.model.identity.exception.NameInvalidException;
import com.resow.authenticationidentity.domain.model.identity.repository.UserRepository;
import com.resow.authenticationidentity.infrastructure.repository.hibernate.UserTokenRepository;
import com.resow.common.exception.GenerateHashException;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author brunomcarvalho89@gmail.com
 */
@RestController
@RequestMapping("/user/credentials")
public class UserCredentialsController implements IUserCredentialsController {

    @Autowired
    private UserRepository userRepository;

    private IUserCredentialsService iUserCredentialsService;

    @PostConstruct
    private void init() {
        this.iUserCredentialsService = new UserCredentialsService(userRepository);
    }

    @Override
    @PostMapping(path = "/fullname", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object changeFullname(@RequestBody ChangeFullnameCommand changeFullnameCommand) throws NameInvalidException {
        this.iUserCredentialsService.changeFullName(changeFullnameCommand);
        return ResponseEntity.ok().build();
    }

    @Override
    @PostMapping(path = "/date-of-birth", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object changeDateOfBirth(@RequestBody ChangeDateOfBirthCommand changeDateOfBirth) throws GenerateHashException {
        this.iUserCredentialsService.changeDateOfBirth(changeDateOfBirth);
        return ResponseEntity.ok().build();
    }

    @Override
    @PostMapping(path = "/nickname", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object changeNickname(@RequestBody ChangeNicknameCommand changeNicknameCommand) {
        this.iUserCredentialsService.changeNickname(changeNicknameCommand);
        return ResponseEntity.ok().build();
    }

    @Override
    @PostMapping(path = "/password", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object changePassword(@RequestBody ChangePasswordCommand changePassowordCommand) throws GenerateHashException {
        this.iUserCredentialsService.changePassword(changePassowordCommand);
        return ResponseEntity.ok().build();
    }

}
