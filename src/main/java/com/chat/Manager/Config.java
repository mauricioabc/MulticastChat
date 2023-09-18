package com.chat.Manager;


/**
 *
 * @author mauricio.rodrigues
 */
public class Config {

    private String publicKey;
    private String privateKey;

    public Config() {
    }

    public Config(String publicKey, String privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    @Override
    public String toString() {
        return "Config{" + ", publicKey=" + publicKey + ", privateKey=" + privateKey + '}';
    }
    
}
