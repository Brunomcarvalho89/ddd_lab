package com.resow.authenticationidentity.infrastructure;

import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringInitialization.class);
    }

    @Bean
    public PhysicalNamingStrategy physicalNamingStrategy() {
        return new PhysicalNamingStrategyStandardImpl();
    }
}
