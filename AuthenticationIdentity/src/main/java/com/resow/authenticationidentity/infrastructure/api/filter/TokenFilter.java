package com.resow.authenticationidentity.infrastructure.api.filter;

import com.resow.authenticationidentity.application.command.AuthenticateByTokenCommand;
import com.resow.common.exception.TokenNotFoundException;
import java.io.IOException;
import java.util.regex.Pattern;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import com.resow.authenticationidentity.application.service.IUserAuthenticationByTokenService;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class TokenFilter extends GenericFilterBean {

    private final IUserAuthenticationByTokenService authenticationUserByTokenService;

    public TokenFilter(IUserAuthenticationByTokenService authenticationUserByTokenService) {
        this.authenticationUserByTokenService = authenticationUserByTokenService;
    }

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain filterChain) throws AuthenticationException, IOException, ServletException, TokenNotFoundException {

        HttpServletRequest httpServletRequest = ((HttpServletRequest) sr);

        String autorizationHeader = httpServletRequest.getHeader("Authorization");

        if (autorizationHeader != null && Pattern.compile(Pattern.quote("bearer"), Pattern.CASE_INSENSITIVE).matcher(autorizationHeader).find()) {

            String token = autorizationHeader.replaceFirst("(?i)bearer", "");

            if (this.authenticationUserByTokenService.authenticateByToken(new AuthenticateByTokenCommand(token.trim()))) {
                UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(token, null, AuthorityUtils.createAuthorityList());
                SecurityContextHolder.getContext().setAuthentication(authReq);
            }
        }

        filterChain.doFilter(sr, sr1);
    }
}
