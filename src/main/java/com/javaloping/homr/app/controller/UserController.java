package com.javaloping.homr.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author victormiranda@gmail.com
 */
@RestController
public class UserController extends BaseController {

    @RequestMapping("/user/placeholder")
    public String placeholder() {
        return "This is a placeholder";
    }
}
