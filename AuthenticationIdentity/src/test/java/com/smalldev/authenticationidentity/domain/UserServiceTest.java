package com.smalldev.authenticationidentity.domain;

import com.resow.authenticationidentity.domain.BeanFacades;
import com.resow.authenticationidentity.domain.model.identity.City;
import com.resow.authenticationidentity.domain.model.identity.Country;
import com.resow.authenticationidentity.domain.model.identity.State;
import com.resow.authenticationidentity.domain.model.identity.User;
import com.resow.authenticationidentity.domain.model.identity.UserUUID;
import com.resow.authenticationidentity.domain.model.identity.service.impl.UserService;
import com.resow.authenticationidentity.infrastructure.hash.HashFunctionArgon2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class UserServiceTest {

    private static UserService userRegisterService;

    @BeforeAll
    static void initUser() {
        userRegisterService = new UserService();
        BeanFacades.initialize(new HashFunctionArgon2());
    }

    @Test
    void testRegisterUser() {

        Assertions.assertDoesNotThrow(() -> {

            User user = userRegisterService.userFrom(new UserUUID("userUUID"),
                    "nickname",
                    "1234",
                    "Bruno Carvalho de Mariz",
                    "Rua teste",
                    "Casa A",
                    1,
                    "25645000",
                    new City(1l, "Petrópolis", new State(1L, "Rio de Janeiro", "rj", new Country(1l, "Brasil","br")))
            );

            Assertions.assertNotNull(user);
        });

    }

}
