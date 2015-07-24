package com.javaloping.homr.app.controller;

import com.javaloping.homr.app.dto.user.UserDTO;
import com.javaloping.homr.app.security.AuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author victormiranda@gmail.com
 */

@RestController
public class LoginController extends BaseController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(name = "/login")
    public AuthenticationToken login(@RequestParam UserDTO user) {

        final UserCredentials credentials = new UserCredentials(user.getUsername(), user.getPassword());

        final UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUsername(), credentials);

        Authentication authentication = null;

        authentication = this.authenticationManager.authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new AuthenticationToken();
    }

}
