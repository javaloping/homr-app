package com.javaloping.homr.app.controller;

import com.javaloping.homr.app.dto.user.UserDTO;
import com.javaloping.homr.app.security.AuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author victormiranda@gmail.com
 */

@RestController
public class LoginController extends BaseController {

    @RequestMapping(name = "/login")
    public AuthenticationToken login(@RequestParam UserDTO user) {
        return new AuthenticationToken();
    }

}
