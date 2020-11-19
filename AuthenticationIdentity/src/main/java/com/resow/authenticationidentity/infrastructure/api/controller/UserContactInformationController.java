package com.resow.authenticationidentity.infrastructure.api.controller;

import com.resow.authenticationidentity.application.command.ChangeEmailCommand;
import com.resow.authenticationidentity.application.command.ChangePhoneCommand;
import com.resow.authenticationidentity.application.command.NewEmailCommand;
import com.resow.authenticationidentity.application.command.NewPhoneCommand;
import com.resow.authenticationidentity.application.command.RemoveEmailCommand;
import com.resow.authenticationidentity.application.command.RemovePhonelCommand;
import com.resow.authenticationidentity.application.service.impl.UserContactService;
import com.resow.authenticationidentity.infrastructure.repository.hibernate.UserRepository;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.resow.authenticationidentity.application.service.IUserContactService;
import com.resow.authenticationidentity.application.api.IUserContactInformationController;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
@RestController
@RequestMapping("/user/contact-information")
public class UserContactInformationController implements IUserContactInformationController {

    private IUserContactService contactUserService;

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    private void init() {
        this.contactUserService = new UserContactService(userRepository);
    }

    @Override
    @PostMapping(path = "/email", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity newEmail(@RequestBody NewEmailCommand newEmailCommand) {
        contactUserService.newEmail(newEmailCommand);
        return ResponseEntity.ok().build();
    }

    @Override
    @PostMapping(path = "/phone", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity newPhone(@RequestBody NewPhoneCommand newPhonelCommand) {
        contactUserService.newPhone(newPhonelCommand);
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping(path = "/email", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity removeEmail(@RequestBody RemoveEmailCommand removeEmailCommand) {
        contactUserService.removeEmail(removeEmailCommand);
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping(path = "/phone", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity removePhone(@RequestBody RemovePhonelCommand removePhoneCommand) {
        contactUserService.removePhone(removePhoneCommand);
        return ResponseEntity.ok().build();
    }

    @Override
    @PatchMapping(path = "/email", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity changeEmail(@RequestBody ChangeEmailCommand changeEmail) {
        contactUserService.changeEmail(changeEmail);
        return ResponseEntity.ok().build();
    }

    @Override
    @PatchMapping(path = "/phone", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity changePhone(@RequestBody ChangePhoneCommand changePhoneCommand) {
        contactUserService.changePhone(changePhoneCommand);
        return ResponseEntity.ok().build();
    }

}
