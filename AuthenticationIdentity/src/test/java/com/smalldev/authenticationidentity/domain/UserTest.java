package com.smalldev.authenticationidentity.domain;

import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.resow.authenticationidentity.domain.BeanFacades;
import com.resow.authenticationidentity.domain.model.identity.Address;
import com.resow.authenticationidentity.domain.model.identity.AddressComplement;
import com.resow.authenticationidentity.domain.model.identity.AddressDescription;
import com.resow.authenticationidentity.domain.model.identity.City;
import com.resow.authenticationidentity.domain.model.identity.Country;
import com.resow.authenticationidentity.domain.model.identity.DateOfBirth;
import com.resow.authenticationidentity.domain.model.identity.EmailAddress;
import com.resow.authenticationidentity.domain.model.identity.FullName;
import com.resow.authenticationidentity.domain.model.identity.Login;
import com.resow.authenticationidentity.domain.model.identity.Person;
import com.resow.authenticationidentity.domain.model.identity.PhoneNumber;
import com.resow.authenticationidentity.domain.model.identity.State;
import com.resow.authenticationidentity.domain.model.identity.User;
import com.resow.authenticationidentity.domain.model.identity.UserUUID;
import com.resow.authenticationidentity.domain.model.identity.ZipCode;
import com.resow.authenticationidentity.domain.model.identity.event.AddressComplementChanged;
import com.resow.authenticationidentity.domain.model.identity.event.AddressDescriptionChanged;
import com.resow.authenticationidentity.domain.model.identity.event.CityChanged;
import com.resow.authenticationidentity.domain.model.identity.event.ContactEmailAdded;
import com.resow.authenticationidentity.domain.model.identity.event.ContactEmailRemoved;
import com.resow.authenticationidentity.domain.model.identity.event.ContactPhoneAdded;
import com.resow.authenticationidentity.domain.model.identity.event.ContactPhoneRemoved;
import com.resow.authenticationidentity.domain.model.identity.event.DateOfBirthChanged;
import com.resow.authenticationidentity.domain.model.identity.event.FullNameChanged;
import com.resow.authenticationidentity.domain.model.identity.event.NicknameChanged;
import com.resow.authenticationidentity.domain.model.identity.event.NumberAddressChanged;
import com.resow.authenticationidentity.domain.model.identity.event.ZipCodeChanged;
import com.resow.authenticationidentity.domain.model.identity.exception.AddressDescriptionException;
import com.resow.authenticationidentity.domain.model.identity.exception.NumberAddressException;
import com.resow.authenticationidentity.infrastructure.hash.HashFunctionArgon2;
import com.resow.common.event.AbstractEventSubscriber;
import com.resow.common.event.EventPublisher;
import com.resow.common.exception.GenerateHashException;
import java.time.LocalDate;
import java.time.Month;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserTest {

    private static User user;

    @BeforeAll
    static void initUser() throws GenerateHashException {

        BeanFacades.initialize(new HashFunctionArgon2());

        Login login = Login.builder()
                .withNickName("nickname")
                .withPassword("1234")
                .build();

        Person person = new Person(FullName.builder()
                .withFirstName("Bruno")
                .withLastName("Carvalho de Mariz")
                .build(),
                new DateOfBirth(LocalDate.of(1989, Month.APRIL, 15))
        );

        Address address = Address.builder()
                .withAddressDescription("Rua teste")
                .withAddressComplement("Casa A")
                .withNumber(1)
                .withZipCode("25645-000")
                .withCity(new City(1l, "Petrópolis", new State(1L, "Rio de Janeiro", "rj", new Country(1l, "Brasil", "br"))))
                .build();

        UserTest.user = new User(new UserUUID(UUID.randomUUID().toString()), login, person, address);

    }

    @Test
    @Order(1)
    void testChangeFullName() {

        EventPublisher
                .instance()
                .subscribe(new AbstractEventSubscriber<FullNameChanged>() {
                    @Override
                    public void handleEvent(FullNameChanged event) {
                        Assertions.assertNotEquals(event.getOldFullName(), event.getNewFullName());
                    }
                });

        FullName fullName = FullName.builder()
                .withFirstName("Bruno")
                .withLastName("Silva")
                .build();

        user.changeFullName(fullName);

        fullName = FullName.builder()
                .withFirstName("Joao")
                .withLastName("Carvalho de Mariz")
                .build();

        user.changeFullName(fullName);
    }

    @Test
    @Order(2)
    void testChangeLogin() {

        Assertions.assertDoesNotThrow(() -> {
            EventPublisher
                    .instance()
                    .subscribe(new AbstractEventSubscriber<NicknameChanged>() {

                        @Override
                        public void handleEvent(NicknameChanged event) {
                            Assertions.assertNotEquals(event.getNewNickname(), event.getOldNickname());
                        }
                    });

        });

        UserTest.user.changeNickname("nicknamenovo");

        Assertions.assertDoesNotThrow(() -> {
            UserTest.user.changePassword("1234");
        });
    }

    @Test
    @Order(3)
    void testAddContactEmail() {

        EventPublisher
                .instance()
                .subscribe(new AbstractEventSubscriber<ContactEmailAdded>() {

                    @Override
                    public void handleEvent(ContactEmailAdded event) {
                        Assertions.assertNotEquals(0, event.getEmailListSize());
                    }
                });
        UserTest.user.addContact(new EmailAddress("bruno@teste.com.br"));
    }

    @Test
    @Order(4)
    void testAddContactPhone() {

        EventPublisher
                .instance()
                .subscribe(new AbstractEventSubscriber<ContactPhoneAdded>() {
                    
                    @Override
                    public void handleEvent(ContactPhoneAdded event) {
                        Assertions.assertNotEquals(0, event.getPhoneListSize());
                    }
                });

        UserTest.user.addContact(new PhoneNumber("999999999"));
    }

    @Test
    @Order(5)
    void testRemoveContactEmail() {

        EmailAddress emailAddress = new EmailAddress("bruno@teste.com.br");

        UserTest.user.addContact(emailAddress);

        EventPublisher
                .instance()
                .subscribe(new AbstractEventSubscriber<ContactEmailRemoved>() {

                    @Override
                    public void handleEvent(ContactEmailRemoved event) {
                        Assertions.assertTrue(event.contains(emailAddress));
                    }
                });

        UserTest.user.removeContact(emailAddress);
    }

    @Test
    @Order(6)
    void testRemoveContactPhone() {

        PhoneNumber phoneNumber = new PhoneNumber("bruno@teste.com.br");

        UserTest.user.addContact(phoneNumber);

        EventPublisher
                .instance()
                .subscribe(new AbstractEventSubscriber<ContactPhoneRemoved>() {
                    
                    @Override
                    public void handleEvent(ContactPhoneRemoved event) {
                        Assertions.assertTrue(event.contains(phoneNumber));
                    }
                });

        UserTest.user.removeContact(phoneNumber);
    }

    @Test
    @Order(7)
    void testChangeAddressDescription() {
        try {

            EventPublisher.instance().subscribe(new AbstractEventSubscriber<AddressDescriptionChanged>() {

                @Override
                public void handleEvent(AddressDescriptionChanged event) {
                    Assertions.assertTrue(
                            event.getOldAddressDescription().equals("Rua teste")
                            && event.getNewAddressDescription().equals("Rua teste 1"));
                }
            });

            UserTest.user.changeAddressDescription(new AddressDescription("Rua teste 1"));
        } catch (AddressDescriptionException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    @Order(8)
    void testChangeNumberAddress() {
        try {

            EventPublisher.instance().subscribe(new AbstractEventSubscriber<NumberAddressChanged>() {

                @Override
                public void handleEvent(NumberAddressChanged event) {
                    Assertions.assertTrue(
                            event.getOldNumber().equals(1)
                            && event.getNewNumber().equals(1000));
                }
            });

            UserTest.user.changeNumberAddress(1000);
        } catch (NumberAddressException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    @Order(9)
    void testChangeAddressComplement() {
        EventPublisher.instance().subscribe(new AbstractEventSubscriber<AddressComplementChanged>() {

            @Override
            public void handleEvent(AddressComplementChanged event) {
                Assertions.assertTrue(
                        event.getOldAddressComplement().equals("Casa A")
                        && event.getNewAddressComplement().equals("Casa B"));
            }
        });

        UserTest.user.changeAddressComplement(new AddressComplement("Casa B"));
    }

    @Test
    @Order(10)
    void testChangeZipCode() {
        EventPublisher.instance().subscribe(new AbstractEventSubscriber<ZipCodeChanged>() {

            @Override
            public void handleEvent(ZipCodeChanged event) {
                Assertions.assertTrue(
                        event.getOldZipCode().equals("25645-000")
                        && event.getNewZipCode().equals("25645-111"));
            }
        });

        Assertions.assertDoesNotThrow(() -> UserTest.user.changeZipCode(new ZipCode("25645-111")));
    }

    @Test
    @Order(11)
    void testChangeCity() {
        EventPublisher.instance().subscribe(new AbstractEventSubscriber<CityChanged>() {
            @Override
            public void handleEvent(CityChanged event) {
                Assertions.assertFalse(event.getOldCity().equals(event.getNewCity()));
            }
        });

        Assertions.assertDoesNotThrow(() -> UserTest.user.changeCity(new City(1l, "Los Angeles", new State(1L, "California", "ca", new Country(1l, "United States", "us")))));
    }

    @Test
    @Order(12)
    protected void testChangeDateOfBirth() {

        EventPublisher
                .instance()
                .subscribe(new AbstractEventSubscriber<DateOfBirthChanged>() {
                    @Override
                    public void handleEvent(DateOfBirthChanged event) {
                        Assertions.assertNotEquals(event.getNewDateOfBirth(), event.getOldDateOfBirth());
                    }
                });

        user.changeDateOfBirth(new DateOfBirth(LocalDate.MAX));
    }

    @Test
    @Order(13)
    protected void changePassword() {
        Assertions.assertDoesNotThrow(() -> {
            user.changePassword("123");
            user.authenticate("123");
        });
        
        EventPublisher.instance().clearSubscribers();
    }

}
