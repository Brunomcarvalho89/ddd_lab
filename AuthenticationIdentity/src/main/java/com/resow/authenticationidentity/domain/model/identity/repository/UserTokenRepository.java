package com.resow.authenticationidentity.domain.model.identity.repository;

import com.resow.authenticationidentity.domain.model.authenticantion.UserToken;
import com.resow.common.domain.repository.IRepository;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public interface UserTokenRepository extends IRepository {

    public void add(UserToken userToken);

    public UserToken findByUserID(String userID);

    public void removeByUserID(String userID);
}
