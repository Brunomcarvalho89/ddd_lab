package com.resow.authenticationidentity.infrastructure.repository.hibernate;

import com.resow.authenticationidentity.domain.model.identity.Address;
import com.resow.authenticationidentity.domain.model.identity.Email;
import com.resow.authenticationidentity.domain.model.identity.EmailAddress;
import com.resow.authenticationidentity.domain.model.identity.Login;
import com.resow.authenticationidentity.domain.model.identity.Phone;
import com.resow.authenticationidentity.domain.model.identity.PhoneNumber;
import com.resow.authenticationidentity.domain.model.identity.User;
import com.resow.authenticationidentity.domain.model.identity.UserUUID;
import com.resow.authenticationidentity.domain.model.identity.exception.UserNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
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
public class UserRepository implements com.resow.authenticationidentity.domain.model.identity.repository.UserRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public UserUUID nextID() {
        return new UserUUID(UUID.randomUUID().toString());
    }

    @Override
    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void save(Login login) {
        entityManager.persist(login);
    }

    @Override
    @Transactional
    public void save(Email email) {
        entityManager.persist(email);
    }

    @Override
    @Transactional
    public void save(Phone phone) {
        entityManager.persist(phone);
    }

    @Override
    @Transactional
    public void save(Address address) {
        entityManager.persist(address);
    }

    @Override
    @Transactional
    public void update(Login login) {
        entityManager.persist(entityManager.merge(login));
    }
    
    @Override
    @Transactional
    public void update(Address address) {
        entityManager.persist(entityManager.merge(address));
    }
    
    @Override
    @Transactional
    public void update(User user) {
        entityManager.persist(entityManager.merge(user));
    }

    @Override
    @Transactional
    public void remove(User user) {
        entityManager.remove(entityManager.merge(user.address()));
        entityManager.remove(entityManager.merge(user.login()));
    }

    @Override
    @Transactional
    public void remove(Email email) {
        entityManager.remove(entityManager.merge(email));
    }

    @Override
    @Transactional
    public void remove(Phone phone) {
        entityManager.remove(entityManager.merge(phone));
    }

    @Override
    @Transactional
    public User findByUUID(UserUUID userUUID) {

        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.userUUID=:userUUID", User.class);

        query.setParameter("userUUID", userUUID);

        return query.getSingleResult();
    }

    @Override
    @Transactional
    public User findByNickName(String nickname) throws UserNotFoundException {

        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u INNER JOIN u.login l on u.login.id=l.id WHERE l.nickName=:nickName", User.class);

        query.setParameter("nickName", nickname);

        try {
            return query.getSingleResult();
        } catch (Exception ex) {
            throw new UserNotFoundException("User not found.");
        }
    }

    @Override
    @Transactional
    public Boolean existsByNickName(String nickname) {

        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u INNER JOIN u.login l on u.login.id=l.id WHERE l.nickName=:nickName", User.class);

        query.setParameter("nickName", nickname);

        try {
            return query.getSingleResult() != null;
        } catch (Exception ex) {
            return Boolean.FALSE;
        }
    }

    @Override
    @Transactional
    public User findByEmail(EmailAddress emailAddress) {

        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u INNER JOIN u.contact.emails e WHERE e.email=:email", User.class);

        query.setParameter("email", emailAddress.value());

        User user = query.getSingleResult();

        return user;
    }

    @Override
    @Transactional
    public User findByPhone(PhoneNumber phoneNumber) {

        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u INNER JOIN Phone e on u.id=e.user.id WHERE e.number=:phone", User.class);

        query.setParameter("phone", phoneNumber.value());

        User user = query.getSingleResult();

        return user;
    }

    @Override
    public List<User> findAll() {
        try {
            return entityManager
                    .createQuery("from User", User.class)
                    .getResultList();
        } catch (Exception ex) {
            return Arrays.asList();
        }
    }

}
