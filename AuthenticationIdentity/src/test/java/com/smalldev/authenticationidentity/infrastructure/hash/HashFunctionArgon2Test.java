package com.smalldev.authenticationidentity.infrastructure.hash;

import com.resow.authenticationidentity.domain.BeanFacades;
import com.resow.authenticationidentity.infrastructure.hash.HashFunctionArgon2;
import com.resow.common.utils.hash.IHashFunction;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class HashFunctionArgon2Test {

    @BeforeAll
    static void initUser() {
        BeanFacades.initialize(new HashFunctionArgon2());
    }

    @Test
    void testGenerateHash() {

        IHashFunction hashFunction = BeanFacades.instance().getHashFunction();

        String value = UUID.randomUUID().toString();

        String hash = hashFunction.hash(value);

        Assertions.assertTrue(hashFunction.verify(hash, value));
    }

}
