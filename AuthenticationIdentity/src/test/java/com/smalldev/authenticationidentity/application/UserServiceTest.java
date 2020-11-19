package com.smalldev.authenticationidentity.application;

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
import com.resow.authenticationidentity.application.service.impl.UserAddressService;
import com.resow.authenticationidentity.application.service.impl.UserContactService;
import com.resow.authenticationidentity.application.service.impl.UserRegistrationService;
import com.resow.authenticationidentity.domain.BeanFacades;
import com.resow.authenticationidentity.domain.model.identity.City;
import com.resow.authenticationidentity.domain.model.identity.User;
import com.resow.authenticationidentity.domain.model.identity.descriptor.AddressDescriptor;
import com.resow.authenticationidentity.domain.model.identity.descriptor.UserDescriptor;
import com.resow.authenticationidentity.domain.model.identity.exception.UserNotFoundException;
import com.resow.authenticationidentity.domain.model.identity.repository.CityRepository;
import com.resow.authenticationidentity.domain.model.identity.repository.UserRepository;
import com.resow.authenticationidentity.domain.model.identity.service.impl.UserService;
import com.resow.authenticationidentity.infrastructure.hash.HashFunctionArgon2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import com.resow.authenticationidentity.application.service.IUserAddressService;
import com.resow.authenticationidentity.application.service.IUserContactService;
import com.resow.authenticationidentity.application.service.IUserCredentialsService;
import com.resow.authenticationidentity.application.service.IUserRegistrationService;
import com.resow.authenticationidentity.application.service.impl.UserCredentialsService;
import com.resow.authenticationidentity.domain.model.identity.Address;
import com.resow.authenticationidentity.domain.model.identity.Country;
import com.resow.authenticationidentity.domain.model.identity.DateOfBirth;
import com.resow.authenticationidentity.domain.model.identity.EmailAddress;
import com.resow.authenticationidentity.domain.model.identity.FullName;
import com.resow.authenticationidentity.domain.model.identity.Login;
import com.resow.authenticationidentity.domain.model.identity.Person;
import com.resow.authenticationidentity.domain.model.identity.PhoneNumber;
import com.resow.authenticationidentity.domain.model.identity.State;
import com.resow.authenticationidentity.domain.model.identity.UserUUID;
import com.resow.authenticationidentity.domain.model.identity.exception.NameInvalidException;
import com.resow.authenticationidentity.infrastructure.acl.zipcode.gateway.ZipCodeValidator;
import com.resow.common.exception.GenerateHashException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.mockito.Mockito;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
@TestMethodOrder(OrderAnnotation.class)
public class UserServiceTest {

    private UserService userService;
    private IUserRegistrationService registerUserService;
    private IUserContactService contactUserService;
    private IUserAddressService iAddressUserService;
    private IUserCredentialsService iUserCredentialsService;

    private UserRepository userRepository = Mockito.mock(UserRepository.class);
    private CityRepository cityRepository = Mockito.mock(CityRepository.class);

    private final String nickname = "nickname";
    private final String nicknameAltered = "newNickname";
    private final String password = "123";
    private String newPassword = "asdf124ç~#12";
    private final String fullname = "Joao carlos da silva";
    private final String email = "joao@teste.com";

    private final String newEmail1 = "carlos.joao.silve@teste.com";
    private final String newEmail2 = "alterado@teste.com";
    private final String newPhone = "24999999999";
    private final String newPhone1 = "24888888888";

    private final String addressDescription = "Rua teste";
    private final String addressComplement = "Casa A";
    private final String zipCode = "25645000";

    @BeforeEach
    public void init() throws GenerateHashException, NameInvalidException {

        BeanFacades.initialize(new HashFunctionArgon2());

        this.userService = new UserService();

        this.registerUserService = new UserRegistrationService(userService, new ZipCodeValidator(), userRepository, cityRepository);

        this.contactUserService = new UserContactService(userRepository);

        State state = new State(1l, "São Paulo", "SP", new Country(1l, "Brasil", "BR"));
        City city = new City(1l, "São Paulo", state);
        City city2 = new City(2l, "Santos", state);

        List<City> cityList = new ArrayList<>();
        cityList.add(city);
        cityList.add(city2);

        Mockito.when(cityRepository.findAll()).thenReturn(cityList);
        Mockito.when(cityRepository.findById(1l)).thenReturn(city);
        Mockito.when(cityRepository.findById(2l)).thenReturn(city2);
        Mockito.when(cityRepository.findAllByState(state)).thenReturn(cityList);

        User userMock = this.getUserMock();
        UserDescriptor userDescriptor = userMock.descriptor();

        UserUUID userUUID = new UserUUID(userDescriptor.getUserUUID());

        List<User> userList = new ArrayList<>();
        userList.add(userMock);

        Mockito.when(userRepository.nextID()).thenReturn(userUUID);
        Mockito.when(userRepository.findAll()).thenReturn(userList);
        Mockito.when(userRepository.findByUUID(userUUID)).thenReturn(userMock);
        Mockito.when(userRepository.findByNickName(userDescriptor.getNickname())).thenReturn(userMock);
        Mockito.when(userRepository.existsByNickName(userDescriptor.getNickname())).thenReturn(Boolean.TRUE);
        Mockito.when(userRepository.findByEmail(new EmailAddress(userDescriptor.getEmails().stream().findFirst().get()))).thenReturn(userMock);
        Mockito.when(userRepository.findByEmail(new EmailAddress(newEmail1))).thenReturn(userMock);
        Mockito.when(userRepository.findByEmail(new EmailAddress(newEmail2))).thenReturn(userMock);
        Mockito.when(userRepository.findByPhone(new PhoneNumber(userDescriptor.getPhones().stream().findFirst().get()))).thenReturn(userMock);

        this.iAddressUserService = new UserAddressService(userRepository, cityRepository);

        this.iUserCredentialsService = new UserCredentialsService(userRepository);
    }

    @Test
    @Order(1)
    void testRegisterUser() {

        Assertions.assertDoesNotThrow(() -> {

            Mockito.when(userRepository.existsByNickName(nickname)).thenReturn(Boolean.FALSE);

            City city = cityRepository.findById(1l);

            registerUserService.register(
                    new UserRegisterCommand(
                            nickname,
                            password,
                            fullname,
                            email,
                            addressDescription,
                            addressComplement,
                            1,
                            zipCode,
                            city.id()
                    ));

            Assertions.assertNotNull(userRepository.findByNickName(nickname));
        });
    }

    @Test
    @Order(2)
    void testNewEmail() {

        contactUserService.newEmail(new NewEmailCommand(nickname, newEmail1));

        User user = userRepository.findByNickName(nickname);

        UserDescriptor userDescriptor = user.descriptor();

        Assertions.assertTrue(userDescriptor.getEmails().contains(newEmail1));
    }

    @Test
    @Order(3)
    void testNewPhone() {

        contactUserService.newPhone(new NewPhoneCommand(nickname, newPhone));

        User user = userRepository.findByNickName(nickname);

        UserDescriptor userDescriptor = user.descriptor();

        Assertions.assertTrue(userDescriptor.getPhones().contains(newPhone));
    }

    @Test
    @Order(4)
    void testChangeEmail() {

        ChangeEmailCommand changeEmailCommand = new ChangeEmailCommand("nickname", newEmail2, email);

        contactUserService.changeEmail(changeEmailCommand);

        User user = userRepository.findByNickName(nickname);

        UserDescriptor userDescriptor = user.descriptor();

        Assertions.assertTrue(userDescriptor.getEmails().contains(newEmail2) && !userDescriptor.getEmails().contains(email));
    }

    @Test
    @Order(5)
    void testChangePhone() {

        ChangePhoneCommand changePhoneCommand = new ChangePhoneCommand("nickname", newPhone1, newPhone);

        contactUserService.changePhone(changePhoneCommand);

        User user = userRepository.findByNickName(nickname);

        UserDescriptor userDescriptor = user.descriptor();

        Assertions.assertTrue(userDescriptor.getPhones().contains(newPhone1) && !userDescriptor.getPhones().contains(newPhone));
    }

    @Test
    @Order(6)
    void testRemoveEmail() {

        RemoveEmailCommand removeEmailCommand = new RemoveEmailCommand("nickname", newEmail2);

        contactUserService.removeEmail(removeEmailCommand);

        User user = userRepository.findByNickName(nickname);

        UserDescriptor userDescriptor = user.descriptor();

        Assertions.assertTrue(!userDescriptor.getEmails().contains(newEmail2));
    }

    @Test
    @Order(7)
    void testRemovePhone() {

        RemovePhonelCommand removePhonelCommand = new RemovePhonelCommand("nickname", newPhone1);

        contactUserService.removePhone(removePhonelCommand);

        User user = userRepository.findByNickName(nickname);

        UserDescriptor userDescriptor = user.descriptor();

        Assertions.assertTrue(!userDescriptor.getPhones().contains(newPhone1));
    }

    @Test
    @Order(8)
    void testChangeAddressDescription() {

        ChangeAddressDescriptionCommand changeAddressDescription = new ChangeAddressDescriptionCommand(nickname, "Rua alterada");

        Assertions.assertDoesNotThrow(() -> iAddressUserService.changeAddressDescription(changeAddressDescription));

        User user = userRepository.findByNickName(nickname);

        AddressDescriptor addressDescriptor = user.address().addressDescriptor();

        Assertions.assertTrue(addressDescriptor.getAddressDescription().equals("Rua alterada"));
    }

    @Test
    @Order(9)
    void testChangeAddressComplement() {

        ChangeAddressComplementCommand changeAddressComplement = new ChangeAddressComplementCommand(nickname, "Complemento alterado");

        Assertions.assertDoesNotThrow(() -> iAddressUserService.changeAddressComplement(changeAddressComplement));

        User user = userRepository.findByNickName(nickname);

        AddressDescriptor addressDescriptor = user.address().addressDescriptor();

        Assertions.assertTrue(addressDescriptor.getAddressComplement().equals("Complemento alterado"));
    }

    @Test
    @Order(10)
    void testChangeZipCode() {

        ChangeZipCodeCommand changeZipCodeCommand = new ChangeZipCodeCommand(nickname, "22222233");

        Assertions.assertDoesNotThrow(() -> iAddressUserService.changeZipCode(changeZipCodeCommand));

        User user = userRepository.findByNickName(nickname);

        AddressDescriptor addressDescriptor = user.address().addressDescriptor();

        Assertions.assertTrue(addressDescriptor.getZipCode().equals("22222233"));
    }

    @Test
    @Order(11)
    void testChangeNumber() {

        ChangeNumberAddressCommand changeNumberAddressCommand = new ChangeNumberAddressCommand(nickname, 2);

        Assertions.assertDoesNotThrow(() -> iAddressUserService.changeNumberAddress(changeNumberAddressCommand));

        User user = userRepository.findByNickName(nickname);

        AddressDescriptor addressDescriptor = user.address().addressDescriptor();

        Assertions.assertTrue(addressDescriptor.getNumber().equals(2));
    }

    @Test
    @Order(12)
    void testChangeCity() {

        User user = userRepository.findByNickName(nickname);

        AddressDescriptor addressDescriptor = user.address().addressDescriptor();

        City city = cityRepository.findAll()
                .stream()
                .filter((City t) -> t.id() != addressDescriptor.getCity().getCityID())
                .findFirst()
                .orElse(null);

        ChangeCityCommand changeCity = new ChangeCityCommand(nickname, city.id());

        Assertions.assertDoesNotThrow(() -> iAddressUserService.changeCity(changeCity));

        user = userRepository.findByNickName(nickname);

        Assertions.assertTrue(user.address().addressDescriptor().getCity().getCityID().equals(city.id()));
    }

    @Test
    @Order(13)
    void testChangeNickname() {

        ChangeNicknameCommand changeNickname = new ChangeNicknameCommand(nickname, nicknameAltered);

        Assertions.assertDoesNotThrow(() -> iUserCredentialsService.changeNickname(changeNickname));
        Assertions.assertDoesNotThrow(() -> userRepository.findByNickName(nicknameAltered));
    }

    @Test
    @Order(14)
    void testChangePassowrd() {
        Assertions.assertDoesNotThrow(() -> {
            User userMock = this.getUserMock();
            userMock.changeNickname(nicknameAltered);
            Mockito.when(userRepository.findByNickName(nicknameAltered)).thenReturn(userMock);

            ChangePasswordCommand changePassowordCommand = new ChangePasswordCommand(nicknameAltered, newPassword);

            iUserCredentialsService.changePassword(changePassowordCommand);
        });
    }

    @Test
    @Order(15)
    void testDeregisterUser() {
        Assertions.assertDoesNotThrow(() -> {
            User userMock = this.getUserMock();
            userMock.changeNickname(nicknameAltered);
            Mockito.when(userRepository.findByNickName(nicknameAltered)).thenReturn(userMock);

            registerUserService.deregister(new UserDeregisterCommand(nicknameAltered));

            Mockito.when(userRepository.findByNickName(nicknameAltered)).thenThrow(UserNotFoundException.class);
            
            Assertions.assertThrows(UserNotFoundException.class, () -> userRepository.findByNickName(nicknameAltered));
            
        });
    }

    User getUserMock() throws GenerateHashException, NameInvalidException {

        Login login = Login.builder()
                .withNickName(nickname)
                .withPassword(password)
                .build();

        Person person = new Person(
                FullName.builder().withFullName(fullname).build(),
                new DateOfBirth(LocalDate.of(1989, Month.APRIL, 15))
        );

        Address address = Address.builder()
                .withAddressDescription(addressDescription)
                .withAddressComplement(addressComplement)
                .withNumber(1)
                .withZipCode(zipCode)
                .withCity(cityRepository.findById(1l))
                .build();

        User user = new User(new UserUUID(UUID.randomUUID().toString()), login, person, address);

        user.addContact(new EmailAddress(email));
        user.addContact(new EmailAddress(newEmail1));
        user.addContact(new EmailAddress(newEmail2));
        user.addContact(new PhoneNumber(newPhone));
        user.addContact(new PhoneNumber(newPhone1));

        return user;
    }
}
