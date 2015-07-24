package com.javaloping.homr.app.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.codec.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author victormiranda@gmail.com
 */

public class TokenUtil {
    public static final String X_AUTH_TOKEN = "X-Auth-Token";

    private static final String MAGIC_KEY = "h0m3rcmnnny34444!!d=-_+";

    public static String getUsernameFromToken(String authToken) {
        if (authToken == null) {
            return null;
        }
        return authToken.split(":")[0];
    }

    public static boolean validateToken(final String authToken, final UserDetails userDetails) {
        final String[] parts = authToken.split(":");
        final long expires = Long.parseLong(parts[1]);

        final String signature = parts[2];

        final String computedSign =
                TokenUtil.computeSignature(userDetails.getUsername(), userDetails.getPassword(), expires);

        return signature.equals(computedSign);
    }

    public static String createToken(final UserLogin userLogin) {
        final long expires = System.currentTimeMillis() + 1000L * 60 * 60;

        final StringBuilder tokenBuilder = new StringBuilder();

        tokenBuilder.append(userLogin.getUsername());
        tokenBuilder.append(":");
        tokenBuilder.append(expires);
        tokenBuilder.append(":");
        tokenBuilder.append(TokenUtil.computeSignature(userLogin.getUsername(), userLogin.getPassword(), expires));

        return tokenBuilder.toString();
    }

    public static String computeSignature(final String username, final String password, final long expires) {
        final StringBuilder signatureBuilder = new StringBuilder();

        signatureBuilder.append(username);
        signatureBuilder.append(":");
        signatureBuilder.append(expires);
        signatureBuilder.append(":");
        signatureBuilder.append(password);
        signatureBuilder.append(":");
        signatureBuilder.append(TokenUtil.MAGIC_KEY);

        final MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("sha");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No sha algorithm available!");
        }

        return new String(Hex.encode(digest.digest(signatureBuilder.toString().getBytes())));
    }
}
