package com.resow.authenticationidentity.infrastructure.api.controller;

import com.resow.authenticationidentity.application.api.IUserController;
import com.resow.authenticationidentity.application.command.ChangeEmailCommand;
import com.resow.authenticationidentity.application.command.ChangePhoneCommand;
import com.resow.authenticationidentity.application.command.NewEmailCommand;
import com.resow.authenticationidentity.application.command.NewPhoneCommand;
import com.resow.authenticationidentity.application.command.RemoveEmailCommand;
import com.resow.authenticationidentity.application.command.RemovePhonelCommand;
import com.resow.authenticationidentity.application.command.UserDeregisterCommand;
import com.resow.authenticationidentity.application.command.UserRegisterCommand;
import com.resow.authenticationidentity.application.dto.UserDTO;
import com.resow.authenticationidentity.application.dto.assembler.impl.AssemblerUserDTO;
import com.resow.authenticationidentity.application.service.IUserDataService;
import com.resow.authenticationidentity.application.service.impl.UserRegistrationService;
import com.resow.authenticationidentity.application.service.impl.UserDataService;
import com.resow.authenticationidentity.domain.model.identity.exception.AddContactUserException;
import com.resow.authenticationidentity.domain.model.identity.exception.NameInvalidException;
import com.resow.authenticationidentity.domain.model.identity.exception.NicknameAlreadyExistsException;
import com.resow.authenticationidentity.domain.model.identity.exception.ZipCodeException;
import com.resow.authenticationidentity.domain.model.identity.service.impl.UserService;
import com.resow.authenticationidentity.infrastructure.repository.hibernate.CityRepository;
import com.resow.authenticationidentity.infrastructure.repository.hibernate.UserRepository;
import com.resow.common.exception.GenerateHashException;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.resow.authenticationidentity.application.service.IUserRegistrationService;
import com.resow.authenticationidentity.domain.model.identity.exception.CityException;
import com.resow.authenticationidentity.infrastructure.acl.zipcode.gateway.ZipCodeValidator;
import java.util.List;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
@RestController
@RequestMapping("/user")
public class UserController implements IUserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CityRepository cityRepository;

    private IUserRegistrationService registrationUserService;
    private IUserDataService userDataService;

    @PostConstruct
    private void init() {

        this.registrationUserService = new UserRegistrationService(
                new UserService(),
                new ZipCodeValidator(),
                userRepository,
                cityRepository);

        this.userDataService = new UserDataService(userRepository, new AssemblerUserDTO());
    }

    @Override
    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity register(@RequestBody UserRegisterCommand userRegisterCommand) throws NameInvalidException, AddContactUserException, GenerateHashException, NicknameAlreadyExistsException, ZipCodeException, CityException {

        registrationUserService.register(userRegisterCommand);

        return ResponseEntity
                .created(WebMvcLinkBuilder
                        .linkTo(WebMvcLinkBuilder
                                .methodOn(UserController.class)
                                .get(userRegisterCommand.getNickName()))
                        .withSelfRel()
                        .toUri())
                .build();
    }

    @Override
    @DeleteMapping(path = "/deregister", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deregister(@RequestBody UserDeregisterCommand userDeregisterCommand) {
        registrationUserService.deregister(userDeregisterCommand);
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping("{nickname}")
    public ResponseEntity get(@PathVariable("nickname") String nickname) {

        UserDTO userDTO = userDataService.allUserDataByNickname(nickname);

        userDTO.add(
                "self",
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(UserController.class)
                                .get(nickname))
                        .withSelfRel()
                        .getHref());

        userDTO.add(
                "deregister",
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(UserController.class)
                                .deregister(new UserDeregisterCommand(nickname)))
                        .withSelfRel()
                        .getHref());

        userDTO.add(
                "new-email",
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(UserContactInformationController.class)
                                .newEmail(new NewEmailCommand(nickname, "new-email-here")))
                        .withSelfRel()
                        .getHref());

        userDTO.add("new-phone",
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserContactInformationController.class)
                        .newPhone(new NewPhoneCommand(nickname, "new-phone-here")))
                        .withSelfRel()
                        .getHref());

        userDTO.add(
                "remove-email",
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(UserContactInformationController.class)
                                .removeEmail(new RemoveEmailCommand(nickname, "email-remove-here")))
                        .withSelfRel()
                        .getHref());

        userDTO.add(
                "remove-phone",
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(UserContactInformationController.class)
                                .removePhone(new RemovePhonelCommand(nickname, "phone-remove-here")))
                        .withSelfRel()
                        .getHref());

        userDTO.add(
                "remove-email",
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(UserContactInformationController.class)
                                .changeEmail(new ChangeEmailCommand(nickname, "old-email-here", "new-email-here")))
                        .withSelfRel()
                        .getHref());

        userDTO.add(
                "remove-phone",
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(UserContactInformationController.class)
                                .changePhone(new ChangePhoneCommand(nickname, "old-phone-here", "new-phone-here")))
                        .withSelfRel()
                        .getHref());

        return ResponseEntity.ok(userDTO);
    }

    @Override
    @GetMapping("/list")
    public ResponseEntity getAll() {

        List<UserDTO> allUserData = userDataService.allUserData();

        allUserData.forEach(userDTO -> {

            userDTO.add(
                    "self",
                    WebMvcLinkBuilder.linkTo(
                            WebMvcLinkBuilder.methodOn(UserController.class)
                                    .get(userDTO.getNickname()))
                            .withSelfRel()
                            .getHref());

            userDTO.add(
                    "deregister",
                    WebMvcLinkBuilder.linkTo(
                            WebMvcLinkBuilder.methodOn(UserController.class)
                                    .deregister(new UserDeregisterCommand(userDTO.getNickname())))
                            .withSelfRel()
                            .getHref());

            userDTO.add(
                    "new-email",
                    WebMvcLinkBuilder.linkTo(
                            WebMvcLinkBuilder.methodOn(UserContactInformationController.class)
                                    .newEmail(new NewEmailCommand(userDTO.getNickname(), "new-email-here")))
                            .withSelfRel()
                            .getHref());

            userDTO.add("new-phone",
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserContactInformationController.class)
                            .newPhone(new NewPhoneCommand(userDTO.getNickname(), "new-phone-here")))
                            .withSelfRel()
                            .getHref());

            userDTO.add(
                    "remove-email",
                    WebMvcLinkBuilder.linkTo(
                            WebMvcLinkBuilder.methodOn(UserContactInformationController.class)
                                    .removeEmail(new RemoveEmailCommand(userDTO.getNickname(), "email-remove-here")))
                            .withSelfRel()
                            .getHref());

            userDTO.add(
                    "remove-phone",
                    WebMvcLinkBuilder.linkTo(
                            WebMvcLinkBuilder.methodOn(UserContactInformationController.class)
                                    .removePhone(new RemovePhonelCommand(userDTO.getNickname(), "phone-remove-here")))
                            .withSelfRel()
                            .getHref());

            userDTO.add(
                    "remove-email",
                    WebMvcLinkBuilder.linkTo(
                            WebMvcLinkBuilder.methodOn(UserContactInformationController.class)
                                    .changeEmail(new ChangeEmailCommand(userDTO.getNickname(), "old-email-here", "new-email-here")))
                            .withSelfRel()
                            .getHref());

            userDTO.add(
                    "remove-phone",
                    WebMvcLinkBuilder.linkTo(
                            WebMvcLinkBuilder.methodOn(UserContactInformationController.class)
                                    .changePhone(new ChangePhoneCommand(userDTO.getNickname(), "old-phone-here", "new-phone-here")))
                            .withSelfRel()
                            .getHref());
        });

        return ResponseEntity.ok(allUserData);
    }

}
