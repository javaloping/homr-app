package com.javaloping.homr.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author victormiranda@gmail.com
 */

public class RestTokenProcessingFilter extends GenericFilterBean {

    @Autowired
    private final UserDetailsService userService;

    public RestTokenProcessingFilter(UserDetailsService userService) {
        this.userService = userService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        final HttpServletRequest httpRequest = this.getAsHttpRequest(request);

        final String authToken = this.extractAuthTokenFromRequest(httpRequest);

        final String username = TokenUtil.getUsernameFromToken(authToken);

        if (username != null) {
            UserDetails userDetails = null;
            try {
                userDetails = userService.loadUserByUsername(username);
            } catch (Exception e) {
                logger.debug(e);
                chain.doFilter(request, response);

                return;
            }

            if (isValidToken(authToken, userDetails)) {
                final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        chain.doFilter(request, response);
    }

    private boolean isValidToken(String authToken, UserDetails userDetails) {
        return TokenUtil.validateToken(authToken, userDetails.getUsername(), userDetails.getPassword());
    }

    private HttpServletRequest getAsHttpRequest(ServletRequest request) {
        if (!(request instanceof HttpServletRequest)) {
            throw new IllegalStateException("Expecting an HTTP request");
        }

        return (HttpServletRequest) request;
    }

    private String extractAuthTokenFromRequest(HttpServletRequest httpRequest) {
        return httpRequest.getHeader(TokenUtil.X_AUTH_TOKEN);
    }
}