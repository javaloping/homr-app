package com.javaloping.homr.app.controller;

import com.javaloping.homr.app.security.TokenUtil;
import com.javaloping.homr.app.security.UserLogin;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author victormiranda@gmail.com
 */

@RestController
public class LoginController extends BaseController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "Application/JSON")
    public UserLogin login(final @RequestBody UserLogin userLogin) {

        final UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword());

        final Authentication authentication = this.authenticationManager.authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = TokenUtil.createToken(userLogin);
        userLogin.setToken(token);

        return userLogin;
    }

}
