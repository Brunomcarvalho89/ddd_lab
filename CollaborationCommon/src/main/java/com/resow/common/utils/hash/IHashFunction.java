package com.resow.common.utils.hash;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public interface IHashFunction<T> {

    String hash(T password);

    Boolean verify(String key, T password);

}
