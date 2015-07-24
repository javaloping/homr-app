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

    private static final String MAGIC_KEY = "ct3H1Dd3N_Cm0n!!!Yeah";

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

        return signature.equals(TokenUtil.computeSignature(userDetails, expires));
    }

    public static String createToken(UserDetails actualUserDetails, UserDetails targetUserDetails) {
        final long expires = System.currentTimeMillis() + 1000L * 60 * 60;

        final StringBuilder tokenBuilder = new StringBuilder();

        tokenBuilder.append(actualUserDetails.getUsername());
        tokenBuilder.append(":");
        tokenBuilder.append(expires);
        tokenBuilder.append(":");
        tokenBuilder.append(TokenUtil.computeSignature(actualUserDetails, expires));

        return tokenBuilder.toString();
    }

    public static String computeSignature(final UserDetails userDetails, final long expires) {
        StringBuilder signatureBuilder = new StringBuilder();
        signatureBuilder.append(userDetails.getUsername());
        signatureBuilder.append(":");
        signatureBuilder.append(expires);
        signatureBuilder.append(":");
        signatureBuilder.append(userDetails.getPassword());
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
