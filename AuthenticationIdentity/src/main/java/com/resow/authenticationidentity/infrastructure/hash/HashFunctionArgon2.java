package com.resow.authenticationidentity.infrastructure.hash;

import com.resow.common.utils.hash.IHashFunction;
import de.mkammerer.argon2.Argon2Factory;
import de.mkammerer.argon2.Argon2Factory.Argon2Types;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class HashFunctionArgon2 implements IHashFunction<String> {

    private ExecutorService executorService = Executors.newCachedThreadPool();

    public HashFunctionArgon2() {
    }

    @Override
    public String hash(String password) {
        return Argon2Factory.create(Argon2Types.ARGON2id).hash(1, 1024, 2, password);
    }

    @Override
    public Boolean verify(String key, String password) {
        return Argon2Factory.create(Argon2Types.ARGON2id).verify(key, password);
    }

}
