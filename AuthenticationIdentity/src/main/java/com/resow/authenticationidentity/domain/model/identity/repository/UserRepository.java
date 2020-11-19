package com.resow.authenticationidentity.domain.model.identity.repository;

import com.resow.authenticationidentity.domain.model.identity.Address;
import com.resow.authenticationidentity.domain.model.identity.Email;
import com.resow.authenticationidentity.domain.model.identity.EmailAddress;
import com.resow.authenticationidentity.domain.model.identity.Login;
import com.resow.authenticationidentity.domain.model.identity.Phone;
import com.resow.authenticationidentity.domain.model.identity.PhoneNumber;
import com.resow.authenticationidentity.domain.model.identity.User;
import com.resow.authenticationidentity.domain.model.identity.UserUUID;
import com.resow.authenticationidentity.domain.model.identity.exception.UserNotFoundException;
import com.resow.common.domain.repository.IRepository;
import java.util.List;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public interface UserRepository extends IRepository {

    UserUUID nextID();

    void save(User user);

    void save(Login login);

    void save(Email email);

    void save(Phone phone);

    void save(Address address);

    void update(User user);
    
    void update(Login login);

    void update(Address address);

    void remove(User user);

    void remove(Email email);

    void remove(Phone phone);

    List<User> findAll();

    User findByUUID(UserUUID userUUID);

    User findByNickName(String nickname) throws UserNotFoundException;

    Boolean existsByNickName(String nickname);

    User findByEmail(EmailAddress emailAddress);

    User findByPhone(PhoneNumber phoneNumber);
}
