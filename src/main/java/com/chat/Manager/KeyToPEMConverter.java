package com.chat.Manager;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class KeyToPEMConverter {
    public static String privateKeyToPEM(PrivateKey privateKey) throws Exception {
        byte[] privateKeyBytes = privateKey.getEncoded();
        String privateKeyPEM = Base64.getEncoder().encodeToString(privateKeyBytes);

        StringBuilder result = new StringBuilder();
        result.append("-----BEGIN PRIVATE KEY-----\n");
        result.append(privateKeyPEM);
        result.append("\n-----END PRIVATE KEY-----\n");

        return result.toString();
    }

    public static String publicKeyToPEM(PublicKey publicKey) throws Exception {
        byte[] publicKeyBytes = publicKey.getEncoded();
        String publicKeyPEM = Base64.getEncoder().encodeToString(publicKeyBytes);

        StringBuilder result = new StringBuilder();
        result.append("-----BEGIN PUBLIC KEY-----\n");
        result.append(publicKeyPEM);
        result.append("\n-----END PUBLIC KEY-----\n");

        return result.toString();
    }

}

