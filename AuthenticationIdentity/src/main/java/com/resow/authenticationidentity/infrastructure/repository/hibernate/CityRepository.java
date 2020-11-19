package com.resow.authenticationidentity.infrastructure.repository.hibernate;

import com.resow.authenticationidentity.domain.model.identity.City;
import com.resow.authenticationidentity.domain.model.identity.State;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
@Repository
public class CityRepository implements com.resow.authenticationidentity.domain.model.identity.repository.CityRepository {

    @Autowired
    private EntityManager entityManager;
    
    @Override
    @Transactional
    public List<City> findAll() {
        TypedQuery<City> query = entityManager.createQuery("SELECT c FROM City c", City.class);
        return query.getResultList();
    }

    @Override
    public List<City> findAllByState(State state) {
        TypedQuery<City> query = entityManager.createQuery("SELECT c FROM City c WHERE c.state=:state", City.class);
        query.setParameter("state", state);
        return query.getResultList();
    }

    @Override
    @Transactional
    public City findById(Long id) {
        TypedQuery<City> query = entityManager.createQuery("SELECT c FROM City c WHERE c.id=:id", City.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

}
