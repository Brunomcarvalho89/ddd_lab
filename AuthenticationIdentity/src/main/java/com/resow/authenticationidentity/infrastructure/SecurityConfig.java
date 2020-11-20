package com.resow.authenticationidentity.infrastructure;

import com.resow.authenticationidentity.application.service.impl.UserAuthenticationByTokenService;
import com.resow.authenticationidentity.infrastructure.api.filter.TokenFilter;
import com.resow.authenticationidentity.infrastructure.repository.hibernate.UserRepository;
import com.resow.authenticationidentity.infrastructure.repository.hibernate.UserTokenRepository;
import com.resow.authenticationidentity.infrastructure.token.UserTokenIssuer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.resow.authenticationidentity.application.service.IUserAuthenticationByTokenService;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private IUserAuthenticationByTokenService authenticationUserByTokenService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/user/authenticate/").permitAll()
                .antMatchers("/user/register").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/management/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new TokenFilter(authenticationUserByTokenService), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public IUserAuthenticationByTokenService authenticationUserByTokenService(UserRepository userRepository, UserTokenRepository userTokenRepository) {
        return new UserAuthenticationByTokenService(userRepository, userTokenRepository, new UserTokenIssuer());
    }

}
