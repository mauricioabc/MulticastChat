package com.chat.Manager;

import com.logger.Log.Log;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class Security {

    private static Security security;
    private Config config;
    
    private Security() {
        
    }

    public static Security getInstance() {
        if(Security.security == null)
            Security.security = new Security();
        return Security.security;
    }
    
    public boolean generateKeyPair(){
        Log.LogAuthenticationComponent("Security", "INFO", "Iniciando processo de criação/atualização do par de chaves de criptografia.");
        GenerateKeys geraChaves = GenerateKeys.getInstance();
        KeyPair keyPair = geraChaves.generateKeys();
        
        String publicKey, privateKey;
        publicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
        privateKey = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
        
        this.config = new Config(publicKey, privateKey);
        
        Log.LogAuthenticationComponent("Security", "INFO", "Finalizando processo de criação do par de chaves de criptografia.");
        return true;
    }
    
    public PublicKey getPublicKey() throws NoSuchAlgorithmException, InvalidKeySpecException{
        Log.LogAuthenticationComponent("Security", "INFO", "Buscando PublicKey na base de dados.");
        byte[] publicKeyBytes = Base64.getDecoder().decode(this.config.getPublicKey());
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Log.LogAuthenticationComponent("Security", "INFO", "Retornando PublicKey.");
        return keyFactory.generatePublic(keySpec);
    }
    
    public String encryptMessage(String message) throws NoSuchAlgorithmException, InvalidKeySpecException, Exception{
        Log.LogAuthenticationComponent("Security", "INFO", "Iniciando processo de criptografia de senha.");
        CryptographyManager cripto = CryptographyManager.getInstance();
        String textoCriptografadoComChavePublica = cripto.encrypt(message, getPublicKey());
        Log.LogAuthenticationComponent("Security", "INFO", "Finalizando processo de criptografia de senha.");
        return textoCriptografadoComChavePublica;
    }
    
    public PrivateKey getPrivateKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        Log.LogAuthenticationComponent("Security", "INFO", "Buscando PrivateKey na base de dados.");
        byte[] privateKeyBytes = Base64.getDecoder().decode(this.config.getPrivateKey());
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Log.LogAuthenticationComponent("Security", "INFO", "Retornando PrivateKey.");
        return keyFactory.generatePrivate(keySpec);
    }
    
    public String decryptMessage(String message) throws NoSuchAlgorithmException, InvalidKeySpecException, Exception{
        Log.LogAuthenticationComponent("Security", "INFO", "Iniciando processo de decriptografia de senha.");
        CryptographyManager cripto = CryptographyManager.getInstance();
        String textoDecriptografadoComChavePrivada = cripto.decrypt(message, getPrivateKey());
        Log.LogAuthenticationComponent("Security", "INFO", "Finalizando processo de decriptografia de senha.");
        return textoDecriptografadoComChavePrivada;
    }
    
}
