package com.resow.authenticationidentity.infrastructure.repository.hibernate;

import com.resow.authenticationidentity.domain.model.identity.Country;
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
public class CountryRepository implements com.resow.authenticationidentity.domain.model.identity.repository.CountryRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Country> findAll() {
        TypedQuery<Country> query = entityManager.createQuery("select * from Country", Country.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Country findById(Long id) {
        TypedQuery<Country> query = entityManager.createQuery("select c from Country c where c.id=:id", Country.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

}
