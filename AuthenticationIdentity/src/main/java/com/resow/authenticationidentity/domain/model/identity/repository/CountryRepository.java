package com.resow.authenticationidentity.domain.model.identity.repository;

import com.resow.authenticationidentity.domain.model.identity.Country;
import com.resow.common.domain.repository.IRepository;
import java.util.List;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public interface CountryRepository extends IRepository {

    List<Country> findAll();

    Country findById(Long id);
}
