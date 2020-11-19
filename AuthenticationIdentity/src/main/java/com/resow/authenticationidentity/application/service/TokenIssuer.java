package com.resow.authenticationidentity.application.service;

import java.util.Optional;
import com.resow.common.application.token.ITokenData;
import com.resow.common.exception.InvalidTokenSecretException;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public interface TokenIssuer<T extends ITokenData> {

    String create(T tokenData, String secret) throws InvalidTokenSecretException;

    Optional<T> verifyAndDecode(String tokenForDecode, String secret) throws InvalidTokenSecretException;

    Optional<T> decode(String tokenForDecode);

    Boolean verify(String tokenToVerify, String secret) throws InvalidTokenSecretException;
}
