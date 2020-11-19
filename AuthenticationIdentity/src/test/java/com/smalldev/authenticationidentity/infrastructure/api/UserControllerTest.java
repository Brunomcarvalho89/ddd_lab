package com.smalldev.authenticationidentity.infrastructure.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.resow.authenticationidentity.application.ApplicationStart;
import com.resow.authenticationidentity.application.command.AuthenticateByUsernameAndPasswordCommand;
import com.resow.authenticationidentity.application.command.ChangeAddressComplementCommand;
import com.resow.authenticationidentity.application.command.ChangeAddressDescriptionCommand;
import com.resow.authenticationidentity.application.command.ChangeCityCommand;
import com.resow.authenticationidentity.application.command.ChangeEmailCommand;
import com.resow.authenticationidentity.application.command.ChangeNicknameCommand;
import com.resow.authenticationidentity.application.command.ChangeNumberAddressCommand;
import com.resow.authenticationidentity.application.command.ChangePasswordCommand;
import com.resow.authenticationidentity.application.command.ChangePhoneCommand;
import com.resow.authenticationidentity.application.command.ChangeZipCodeCommand;
import com.resow.authenticationidentity.application.command.NewEmailCommand;
import com.resow.authenticationidentity.application.command.NewPhoneCommand;
import com.resow.authenticationidentity.application.command.RemoveEmailCommand;
import com.resow.authenticationidentity.application.command.RemovePhonelCommand;
import com.resow.authenticationidentity.application.command.UserDeregisterCommand;
import com.resow.authenticationidentity.application.command.UserRegisterCommand;
import com.resow.authenticationidentity.application.dto.UserDTO;
import com.resow.authenticationidentity.domain.model.identity.exception.UserNotFoundException;
import com.resow.authenticationidentity.infrastructure.SpringInitialization;
import com.resow.authenticationidentity.infrastructure.hash.HashFunctionArgon2;
import java.net.URISyntaxException;
import java.util.Objects;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
@ExtendWith({org.flywaydb.test.junit5.FlywayTestExtension.class})
@ContextConfiguration(classes = SpringInitialization.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
@FlywayTest
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static String tokenTest = null;

    private static String nickname = "nickname_test";
    private static String newnickname = "nickname_test";

    @BeforeAll
    public static void init() {

//        ApplicationStart
//                .instance()
//                .clear()
//                .initializeBeansFacade(new HashFunctionArgon2());
    }

    @Test
    @Order(1)
    public void testRegister() throws URISyntaxException {

        Assertions.assertDoesNotThrow(() -> {
            MvcResult andReturn = mockMvc.perform(
                    MockMvcRequestBuilders
                            .post("/user/register")
                            .accept(MediaType.APPLICATION_JSON_VALUE)
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(new ObjectMapper().writeValueAsString(new UserRegisterCommand(nickname, "password_test", "name_test middle_name_test", "test@test.com", "address_description_test", "address_complement_test", 22, "88888888", 1l))))
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andReturn();
        });
    }

    @Test
    @Order(2)
    public void testGetToken() {
        Assertions.assertDoesNotThrow(() -> {
            MvcResult andReturn = mockMvc
                    .perform(
                            MockMvcRequestBuilders
                                    .post("/user/authenticate/")
                                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                                    .content(new ObjectMapper().writeValueAsString(new AuthenticateByUsernameAndPasswordCommand(nickname, "password_test"))))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn();

            UserControllerTest.tokenTest = andReturn.getResponse().getContentAsString();

            Assertions.assertTrue(Objects.nonNull(this.tokenTest) && this.tokenTest.length() > 0);
        });
    }

    @Test
    @Order(3)
    public void testGet() {

        Assertions.assertDoesNotThrow(() -> {
            String userByNickname = getUserByNickname(nickname);

            Assertions.assertTrue(Objects.nonNull(userByNickname.isEmpty()));
            Assertions.assertTrue(!userByNickname.isEmpty());
        });
    }

    @Test
    @Order(4)
    public void testNewEmail() {
        Assertions.assertDoesNotThrow(() -> {
            mockMvc.perform(
                    MockMvcRequestBuilders
                            .post("/user/contact-information/email")
                            .header("Authorization", "bearer " + UserControllerTest.tokenTest)
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(new ObjectMapper().writeValueAsString(new NewEmailCommand(nickname, "asdf@asdf.com"))))
                    .andExpect(MockMvcResultMatchers.status().isOk());

            String userByNickname = getUserByNickname(nickname);

            UserDTO userDTO = new ObjectMapper().readValue(userByNickname, UserDTO.class);

            Assertions.assertTrue(userDTO.getEmail().contains("asdf@asdf.com"));
        });
    }

    @Test
    @Order(5)
    public void testChangeEmail() {
        Assertions.assertDoesNotThrow(() -> {
            mockMvc.perform(
                    MockMvcRequestBuilders
                            .patch("/user/contact-information/email")
                            .header("Authorization", "bearer " + UserControllerTest.tokenTest)
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(new ObjectMapper().writeValueAsString(new ChangeEmailCommand(nickname, "asdf1234s@asdf.com", "asdf@asdf.com"))))
                    .andExpect(MockMvcResultMatchers.status().isOk());

            String userByNickname = getUserByNickname(nickname);

            UserDTO userDTO = new ObjectMapper().readValue(userByNickname, UserDTO.class);

            Assertions.assertTrue(userDTO.getEmail().contains("asdf1234s@asdf.com"));
            Assertions.assertFalse(userDTO.getEmail().contains("asdf@asdf.com"));
        });
    }

    @Test
    @Order(6)
    public void testRemoveEmail() {
        Assertions.assertDoesNotThrow(() -> {
            mockMvc.perform(
                    MockMvcRequestBuilders
                            .delete("/user/contact-information/email")
                            .header("Authorization", "bearer " + UserControllerTest.tokenTest)
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(new ObjectMapper().writeValueAsString(new RemoveEmailCommand(nickname, "asdf1234s@asdf.com"))))
                    .andExpect(MockMvcResultMatchers.status().isOk());

            String userByNickname = getUserByNickname(nickname);

            UserDTO userDTO = new ObjectMapper().readValue(userByNickname, UserDTO.class);

            Assertions.assertFalse(userDTO.getEmail().contains("asdf1234s@asdf.com"));
        });
    }

    @Test
    @Order(7)
    public void testNewPhone() {
        Assertions.assertDoesNotThrow(() -> {
            mockMvc.perform(
                    MockMvcRequestBuilders
                            .post("/user/contact-information/phone")
                            .header("Authorization", "bearer " + UserControllerTest.tokenTest)
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(new ObjectMapper().writeValueAsString(new NewPhoneCommand(nickname, "777777777"))))
                    .andExpect(MockMvcResultMatchers.status().isOk());

            String userByNickname = getUserByNickname(nickname);

            UserDTO userDTO = new ObjectMapper().readValue(userByNickname, UserDTO.class);

            Assertions.assertTrue(userDTO.getPhones().contains("777777777"));
        });
    }

    @Test
    @Order(8)
    public void testChangePhone() {
        Assertions.assertDoesNotThrow(() -> {
            mockMvc.perform(
                    MockMvcRequestBuilders
                            .patch("/user/contact-information/phone")
                            .header("Authorization", "bearer " + UserControllerTest.tokenTest)
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(new ObjectMapper().writeValueAsString(new ChangePhoneCommand(nickname, "777754777", "777777777"))))
                    .andExpect(MockMvcResultMatchers.status().isOk());

            String userByNickname = getUserByNickname(nickname);

            UserDTO userDTO = new ObjectMapper().readValue(userByNickname, UserDTO.class);

            Assertions.assertTrue(userDTO.getPhones().contains("777754777"));
            Assertions.assertFalse(userDTO.getPhones().contains("777777777"));
        });
    }

    @Test
    @Order(9)
    public void testRemovePhone() {
        Assertions.assertDoesNotThrow(() -> {
            mockMvc.perform(
                    MockMvcRequestBuilders
                            .delete("/user/contact-information/phone")
                            .header("Authorization", "bearer " + UserControllerTest.tokenTest)
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(new ObjectMapper().writeValueAsString(new RemovePhonelCommand(nickname, "777754777"))))
                    .andExpect(MockMvcResultMatchers.status().isOk());

            String userByNickname = getUserByNickname(nickname);

            UserDTO userDTO = new ObjectMapper().readValue(userByNickname, UserDTO.class);

            Assertions.assertFalse(userDTO.getPhones().contains("777754777"));
        });
    }

    @Test
    @Order(10)
    public void testChangeAddressDescription() {
        Assertions.assertDoesNotThrow(() -> {
            mockMvc.perform(
                    MockMvcRequestBuilders
                            .patch("/user/address/addressDescription")
                            .header("Authorization", "bearer " + UserControllerTest.tokenTest)
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(new ObjectMapper().writeValueAsString(new ChangeAddressDescriptionCommand(nickname, "Rua 2 teste"))))
                    .andExpect(MockMvcResultMatchers.status().isOk());

            String userByNickname = getUserByNickname(nickname);

            UserDTO userDTO = new ObjectMapper().readValue(userByNickname, UserDTO.class);

            Assertions.assertTrue(userDTO.getAddressDescription().equals("Rua 2 teste"));
        });
    }

    @Test
    @Order(11)
    public void testChangeAddressComplement() {
        Assertions.assertDoesNotThrow(() -> {
            mockMvc.perform(
                    MockMvcRequestBuilders
                            .patch("/user/address/addressComplement")
                            .header("Authorization", "bearer " + UserControllerTest.tokenTest)
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(new ObjectMapper().writeValueAsString(new ChangeAddressComplementCommand(nickname, "Logo ali"))))
                    .andExpect(MockMvcResultMatchers.status().isOk());

            String userByNickname = getUserByNickname(nickname);

            UserDTO userDTO = new ObjectMapper().readValue(userByNickname, UserDTO.class);

            Assertions.assertTrue(userDTO.getAddressComplement().equals("Logo ali"));
        });
    }

    @Test
    @Order(12)
    public void testChangeZipCode() {
        Assertions.assertDoesNotThrow(() -> {
            mockMvc.perform(
                    MockMvcRequestBuilders
                            .patch("/user/address/zipcode")
                            .header("Authorization", "bearer " + UserControllerTest.tokenTest)
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(new ObjectMapper().writeValueAsString(new ChangeZipCodeCommand(nickname, "444555666"))))
                    .andExpect(MockMvcResultMatchers.status().isOk());

            String userByNickname = getUserByNickname(nickname);

            UserDTO userDTO = new ObjectMapper().readValue(userByNickname, UserDTO.class);

            Assertions.assertTrue(userDTO.getZipCode().equals("444555666"));
        });
    }

    @Test
    @Order(13)
    public void testChangeCity() {
        Assertions.assertDoesNotThrow(() -> {
            mockMvc.perform(
                    MockMvcRequestBuilders
                            .patch("/user/address/city")
                            .header("Authorization", "bearer " + UserControllerTest.tokenTest)
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(new ObjectMapper().writeValueAsString(new ChangeCityCommand(nickname, 2l))))
                    .andExpect(MockMvcResultMatchers.status().isOk());

            String userByNickname = getUserByNickname(nickname);

            UserDTO userDTO = new ObjectMapper().readValue(userByNickname, UserDTO.class);

            Assertions.assertTrue(userDTO.getCityID() == 2);
        });
    }

    @Test
    @Order(14)
    public void testChangeNumberAddress() {
        Assertions.assertDoesNotThrow(() -> {
            mockMvc.perform(
                    MockMvcRequestBuilders
                            .patch("/user/address/number")
                            .header("Authorization", "bearer " + UserControllerTest.tokenTest)
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(new ObjectMapper().writeValueAsString(new ChangeNumberAddressCommand(nickname, 21212))))
                    .andExpect(MockMvcResultMatchers.status().isOk());

            String userByNickname = getUserByNickname(nickname);

            UserDTO userDTO = new ObjectMapper().readValue(userByNickname, UserDTO.class);

            Assertions.assertTrue(userDTO.getNumber() == 21212);
        });
    }

    @Test
    @Order(15)
    public void testChangeNickname() {
        Assertions.assertDoesNotThrow(() -> {
            mockMvc.perform(
                    MockMvcRequestBuilders
                            .post("/user/credentials/nickname")
                            .header("Authorization", "bearer " + UserControllerTest.tokenTest)
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(new ObjectMapper().writeValueAsString(new ChangeNicknameCommand(nickname, newnickname))))
                    .andExpect(MockMvcResultMatchers.status().isOk());

            MvcResult andReturn = mockMvc
                    .perform(
                            MockMvcRequestBuilders
                                    .post("/user/authenticate/")
                                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                                    .content(new ObjectMapper().writeValueAsString(new AuthenticateByUsernameAndPasswordCommand(newnickname, "password_test"))))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn();

            UserControllerTest.tokenTest = andReturn.getResponse().getContentAsString();

            Assertions.assertTrue(Objects.nonNull(this.tokenTest) && this.tokenTest.length() > 0);

        });
    }

    @Test
    @Order(16)
    public void testChangePassword() {
        Assertions.assertDoesNotThrow(() -> {
            mockMvc.perform(
                    MockMvcRequestBuilders
                            .post("/user/credentials/password")
                            .header("Authorization", "bearer " + UserControllerTest.tokenTest)
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(new ObjectMapper().writeValueAsString(new ChangePasswordCommand(newnickname, "newpassword"))))
                    .andExpect(MockMvcResultMatchers.status().isOk());

            MvcResult andReturn = mockMvc
                    .perform(
                            MockMvcRequestBuilders
                                    .post("/user/authenticate/")
                                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                                    .content(new ObjectMapper().writeValueAsString(new AuthenticateByUsernameAndPasswordCommand(newnickname, "newpassword"))))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn();

            Assertions.assertFalse(andReturn.getResponse().getStatus() == HttpStatus.UNAUTHORIZED.value());
            Assertions.assertFalse(andReturn.getResponse().getStatus() == HttpStatus.NOT_FOUND.value());

            UserControllerTest.tokenTest = andReturn.getResponse().getContentAsString();

            Assertions.assertTrue(Objects.nonNull(UserControllerTest.tokenTest) && UserControllerTest.tokenTest.length() > 0);

        });
    }

    @Test
    @Order(17)
    public void testDeregister() {
        Assertions.assertDoesNotThrow(() -> {
            mockMvc.perform(
                    MockMvcRequestBuilders
                            .delete("/user/deregister")
                            .header("Authorization", "bearer " + UserControllerTest.tokenTest)
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(new ObjectMapper().writeValueAsString(new UserDeregisterCommand(newnickname))))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn();
        });

    }

    private String getUserByNickname(String nickname) throws Exception {

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/user/" + nickname)
                        .header("Authorization", "bearer " + UserControllerTest.tokenTest)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        if (mvcResult.getResponse().getStatus() == HttpStatus.NOT_FOUND.value()) {
            throw new UserNotFoundException("User not found exception");
        }

        return mvcResult.getResponse().getContentAsString();
    }

}
