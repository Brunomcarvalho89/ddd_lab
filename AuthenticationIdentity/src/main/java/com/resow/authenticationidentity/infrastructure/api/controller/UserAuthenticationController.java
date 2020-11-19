package com.resow.authenticationidentity.infrastructure.api.controller;

import com.resow.authenticationidentity.application.api.IUserAuthenticationController;
import com.resow.authenticationidentity.application.command.AuthenticateByUsernameAndPasswordCommand;
import com.resow.authenticationidentity.application.service.IUserAuthenticationByPasswordAndGenerateTokenService;
import com.resow.authenticationidentity.application.service.impl.UserAuthenticationByPasswordService;
import com.resow.authenticationidentity.application.service.impl.UserAuthenticationByPasswordAndGenerateTokenService;
import com.resow.authenticationidentity.application.service.impl.UserTokenService;
import com.resow.authenticationidentity.infrastructure.repository.hibernate.UserRepository;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.resow.authenticationidentity.infrastructure.repository.hibernate.UserTokenRepository;
import com.resow.authenticationidentity.infrastructure.token.UserTokenIssuer;
import com.resow.authenticationidentity.application.service.IUserAuthenticationByPasswordService;
import com.resow.authenticationidentity.domain.model.identity.exception.InvalidPasswordException;
import com.resow.authenticationidentity.domain.model.identity.exception.UserNotFoundException;
import com.resow.common.exception.InvalidTokenSecretException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
@RestController
@RequestMapping("/user/authenticate")
public class UserAuthenticationController implements IUserAuthenticationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTokenRepository userTokenRepository;

    private IUserAuthenticationByPasswordService authenticationService;
    private IUserAuthenticationByPasswordAndGenerateTokenService authenticationByPasswordAndGenerateTokenService;

    @PostConstruct
    public void init() {

        this.authenticationService = new UserAuthenticationByPasswordService(userRepository);
        this.authenticationByPasswordAndGenerateTokenService = new UserAuthenticationByPasswordAndGenerateTokenService(
                authenticationService,
                userRepository,
                new UserTokenService(
                        userTokenRepository,
                        new UserTokenIssuer()));
    }

    @Override
    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity authenticate(@RequestBody AuthenticateByUsernameAndPasswordCommand authenticateCommand) throws InvalidTokenSecretException, UserNotFoundException, InvalidPasswordException {
        return ResponseEntity.ok(this.authenticationByPasswordAndGenerateTokenService.authenticateAndIssueTokenTo(authenticateCommand));
    }

}
