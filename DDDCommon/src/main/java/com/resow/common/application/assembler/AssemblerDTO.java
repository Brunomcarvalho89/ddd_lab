package com.resow.common.application.assembler;

import com.resow.common.application.dto.BaseDTO;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public interface AssemblerDTO<K, T> {

    T convert(K obj);

}
