package com.resow.authenticationidentity.infrastructure;

import com.resow.authenticationidentity.application.ApplicationStart;
import com.resow.authenticationidentity.application.event.subscribers.CreatedUserSubscriber;
import com.resow.authenticationidentity.application.notification.UserCreatedNotification;
import com.resow.authenticationidentity.domain.model.authenticantion.UserToken;
import com.resow.authenticationidentity.domain.model.identity.User;
import com.resow.authenticationidentity.domain.model.identity.repository.UserRepository;
import com.resow.authenticationidentity.infrastructure.hash.HashFunctionArgon2;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
@SpringBootApplication
@ComponentScan(basePackageClasses = {SpringInitialization.class})
@EntityScan(basePackageClasses = {User.class, UserToken.class})
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class SpringInitialization {

    public static void main(String[] args) {
        SpringApplication.run(SpringInitialization.class, args);
    }

    @Autowired
    private UserCreatedNotification userCreatedNotification;

    @PostConstruct
    public void initialize() {

        ApplicationStart
                .instance()
                .subscribeEvent(new CreatedUserSubscriber(userCreatedNotification))
                .initializeBeansFacade(new HashFunctionArgon2())
                .start();
    }
}
