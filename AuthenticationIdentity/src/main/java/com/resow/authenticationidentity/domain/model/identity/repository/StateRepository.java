package com.resow.authenticationidentity.domain.model.identity.repository;

import com.resow.authenticationidentity.domain.model.identity.Country;
import com.resow.authenticationidentity.domain.model.identity.State;
import com.resow.common.domain.repository.IRepository;
import java.util.List;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public interface StateRepository extends IRepository {

    List<State> findAll();

    List<State> findAllByCountry(Country country);

    State findById(Long id);
}
