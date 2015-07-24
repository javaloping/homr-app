package com.javaloping.homr.app.test.login;

import com.javaloping.homr.app.security.TokenUtil;
import com.javaloping.homr.app.security.UserLogin;
import junit.framework.Assert;
import org.junit.Test;

/**
 * @author victormiranda@gmail.com
 */
public class TokenTest {

    @Test
    public void testValidToken() {
        final UserLogin userLogin = getUserLogin();

        final String token = TokenUtil.createToken(userLogin);

        final boolean valid = TokenUtil.validateToken(token, userLogin.getUsername(), userLogin.getPassword());

        Assert.assertTrue(valid);
    }

    @Test
    public void testUsernameFromToken() {
        final UserLogin userLogin = getUserLogin();

        final String token = TokenUtil.createToken(userLogin);

        Assert.assertTrue(TokenUtil.getUsernameFromToken(token).equals(userLogin.getUsername()));
    }

    @Test
    public void testInvalidToken() {
        final UserLogin userLogin = getUserLogin();

        final String invalidToken = createInvalidToken(userLogin);

        final boolean valid = TokenUtil.validateToken(invalidToken, userLogin.getUsername(), userLogin.getPassword());

        Assert.assertFalse(valid);
    }

    @Test
    public void testExpiredToken() {
        final UserLogin userLogin = getUserLogin();

        final String invalidToken = createExpiredToken(userLogin);

        final boolean valid = TokenUtil.validateToken(invalidToken, userLogin.getUsername(), userLogin.getPassword());

        Assert.assertFalse(valid);
    }

    @Test
    public void testGetToken() {
        final UserLogin userLogin = getUserLogin();

        final String token = TokenUtil.createToken(userLogin);
        boolean valid = TokenUtil.validateToken(userLogin.getToken(), userLogin.getUsername(), userLogin.getPassword());

        Assert.assertTrue(valid);
        Assert.assertEquals(userLogin.getUsername(), TokenUtil.getUsernameFromToken(token));
    }

    private UserLogin getUserLogin() {
        final UserLogin userLogin = new UserLogin();

        userLogin.setUsername("paco");
        userLogin.setPassword("password");

        return userLogin;
    }

    private String createInvalidToken(final UserLogin userLogin) {
        final long expires = System.currentTimeMillis() + 1000L * 60 * 60;

        final StringBuilder tokenBuilder = new StringBuilder();

        tokenBuilder.append(userLogin.getUsername());
        tokenBuilder.append(":");
        tokenBuilder.append(expires);
        tokenBuilder.append(":");
        tokenBuilder.append(TokenUtil.computeSignature(userLogin.getUsername(), "invalidPassword", expires));

        return tokenBuilder.toString();
    }

    private String createExpiredToken(final UserLogin userLogin) {
        final long expires = System.currentTimeMillis() - 1000L * 60 * 60;

        final StringBuilder tokenBuilder = new StringBuilder();

        tokenBuilder.append(userLogin.getUsername());
        tokenBuilder.append(":");
        tokenBuilder.append(expires);
        tokenBuilder.append(":");
        tokenBuilder.append(TokenUtil.computeSignature(userLogin.getUsername(), "invalidPassword", expires));

        return tokenBuilder.toString();
    }
}
