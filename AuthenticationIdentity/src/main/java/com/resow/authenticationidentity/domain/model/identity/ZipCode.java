package com.resow.authenticationidentity.domain.model.identity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
@Embeddable
public class ZipCode {

    @Column(name = "zipCode")
    private String code;

    private ZipCode() {
        super();
    }

    public ZipCode(String code) {
        this();
        this.code = code;
    }

    public String code() {
        return code;
    }

}
