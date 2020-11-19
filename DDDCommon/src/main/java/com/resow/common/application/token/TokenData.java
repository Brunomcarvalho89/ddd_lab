package com.resow.common.application.token;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public abstract class TokenData implements ITokenData {

    protected String issuer;
    protected String subject;
    protected String[] audience;
    protected long expiration;

    public TokenData(String issuer, String subject, String[] audience, long expiration) {
        this.issuer = issuer;
        this.subject = subject;
        this.audience = audience;
        this.expiration = expiration;
    }

    public String getIssuer() {
        return issuer;
    }

    public String getSubject() {
        return subject;
    }

    public String[] getAudience() {
        return audience;
    }

    public long getExpiration() {
        return expiration;
    }
}
