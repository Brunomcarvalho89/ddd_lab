package com.resow.authenticationidentity.domain;

import com.resow.common.utils.hash.IHashFunction;

/**
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class BeanFacades {

    private static BeanFacades beanFacades;

    private IHashFunction iHashFunction;

    private BeanFacades(IHashFunction iHashFunction) {
        this.iHashFunction = iHashFunction;
    }

    public static BeanFacades instance() {
        if (beanFacades == null) {
            throw new RuntimeException("Run class initialization method");
        }
        return BeanFacades.beanFacades;
    }

    public static BeanFacades initialize(IHashFunction iHashFunction) {
        BeanFacades.beanFacades = new BeanFacades(iHashFunction);
        return BeanFacades.beanFacades;
    }

    public IHashFunction getHashFunction() {
        return this.iHashFunction;
    }
}
