package com.resow.authenticationidentity.infrastructure.repository.hibernate;

import com.resow.authenticationidentity.domain.model.identity.Country;
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
public class StateRepository implements com.resow.authenticationidentity.domain.model.identity.repository.StateRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<State> findAll() {
        TypedQuery<State> query = entityManager.createQuery("SELECT * FROM State", State.class);
        return query.getResultList();
    }

    @Override
    public List<State> findAllByCountry(Country country) {
        TypedQuery<State> query = entityManager.createQuery("SELECT c FROM State c WHERE c.country=:country", State.class);
        query.setParameter("country", country);
        return query.getResultList();
    }

    @Override
    @Transactional
    public State findById(Long id) {
        TypedQuery<State> query = entityManager.createQuery("SELECT c FROM State c WHERE c.id=:id", State.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

}
