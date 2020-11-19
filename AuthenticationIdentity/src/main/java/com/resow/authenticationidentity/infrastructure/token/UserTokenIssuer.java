package com.resow.authenticationidentity.infrastructure.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.resow.authenticationidentity.domain.model.authenticantion.UserTokenData;
import java.util.Date;
import com.resow.common.exception.InvalidTokenSecretException;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import com.resow.authenticationidentity.application.service.TokenIssuer;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class UserTokenIssuer implements TokenIssuer<UserTokenData> {

    private static final long EXPIRATION_TIME = 86400000; // One day

    public UserTokenIssuer() {
    }

    @Override
    public String create(UserTokenData userTokenData, String secret) throws InvalidTokenSecretException {

        if (Objects.isNull(secret) || secret.isEmpty()) {
            throw new InvalidTokenSecretException();
        }

        String token = JWT.create()
                .withIssuer(userTokenData.getIssuer())
                .withSubject(userTokenData.getSubject())
                .withExpiresAt(Date.from(Instant.ofEpochMilli(userTokenData.getExpiration())))
                .withClaim("username", userTokenData.getNickname())
                .withClaim("hashValid", userTokenData.getHashValid())
                .sign(Algorithm.HMAC256(secret));

        return token;
    }

    public Optional<UserTokenData> decode(String tokenClient) {
        try {
            DecodedJWT decodedJWT = JWT
                    .decode(tokenClient);

            UserTokenData userTokenData = buildUserTokenData(decodedJWT);

            return Optional.of(userTokenData);

        } catch (JWTVerificationException jwtve) {
            jwtve.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<UserTokenData> verifyAndDecode(String tokenClient, String secret) throws InvalidTokenSecretException {
        try {
            if (Objects.isNull(secret) || secret.isEmpty()) {
                throw new InvalidTokenSecretException();
            }

            DecodedJWT decodedJWT = JWT
                    .require(Algorithm.HMAC256(secret))
                    .build()
                    .verify(tokenClient);

            UserTokenData userTokenData = buildUserTokenData(decodedJWT);

            return Optional.of(userTokenData);

        } catch (JWTVerificationException jwtve) {
            jwtve.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Boolean verify(String tokenToVerify, String secret) throws InvalidTokenSecretException {
        return this.verifyAndDecode(tokenToVerify, secret).isPresent();
    }

    private UserTokenData buildUserTokenData(DecodedJWT decodedJWT) {

        String issuer = decodedJWT.getIssuer();
        String subject = decodedJWT.getSubject();
        List<String> audience = decodedJWT.getAudience();
        Date expiresAt = decodedJWT.getExpiresAt();
        String username = decodedJWT.getClaim("username").asString();
        String hashValid = decodedJWT.getClaim("hashValid").asString();
        String[] audiences = new String[audience != null ? audience.size() : 0];
        if (Objects.nonNull(audience) && audience.size() > 0) {
            audiences = audience.toArray(audiences);
        }

        UserTokenData userTokenData = new UserTokenData(issuer,
                subject,
                audiences,
                expiresAt.getTime(),
                username,
                hashValid);
        return userTokenData;
    }

}
