package com.javaloping.homr.app.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author victormiranda@gmail.com
 */
public class BaseController {

    @ExceptionHandler({DataAccessException.class})
    public String databaseError() {
        return "TODO";
    }


}
