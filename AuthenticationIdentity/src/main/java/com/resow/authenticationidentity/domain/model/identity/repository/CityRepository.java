package com.resow.authenticationidentity.domain.model.identity.repository;

import com.resow.authenticationidentity.domain.model.identity.City;
import com.resow.authenticationidentity.domain.model.identity.State;
import com.resow.common.domain.repository.IRepository;
import java.util.List;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public interface CityRepository extends IRepository {

    List<City> findAll();

    List<City> findAllByState(State state);

    City findById(Long id);

}
