package com.javaloping.homr.app.security;

import io.swagger.annotations.ApiModel;

/**
 * @author victormiranda@gmail.com
 */
@ApiModel(description = "Login bean")
public class UserLogin {
    private String username;
    private String password;
    private String token;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
