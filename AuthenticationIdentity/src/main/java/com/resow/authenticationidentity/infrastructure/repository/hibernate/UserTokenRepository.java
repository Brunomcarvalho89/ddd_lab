package com.resow.authenticationidentity.infrastructure.repository.hibernate;

import com.resow.authenticationidentity.domain.model.authenticantion.UserToken;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
@Repository
public class UserTokenRepository implements com.resow.authenticationidentity.domain.model.identity.repository.UserTokenRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public void add(UserToken userToken) {
        entityManager.persist(userToken);
    }

    @Override
    @Transactional
    public UserToken findByUserID(String userID) {

        TypedQuery<UserToken> query = entityManager.createQuery("SELECT u FROM UserToken u WHERE u.userID=:userID", UserToken.class);

        query.setParameter("userID", userID);

        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void removeByUserID(String userID) {

        Query query = entityManager.createQuery("DELETE FROM UserToken u WHERE u.userID=:userID");

        query.setParameter("userID", userID);

        query.executeUpdate();
    }

}
