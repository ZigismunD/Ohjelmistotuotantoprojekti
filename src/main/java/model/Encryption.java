package model;

import java.util.Base64;

public class Encryption {
    private static Encryption INSTANCE = null;

    private Encryption() {

    }

    public static Encryption getInstance() {
        if (INSTANCE == null) {
            return new Encryption();
        }
        return INSTANCE;
    }

    public String encrypt(String syote) {
        return Base64.getEncoder().encodeToString(syote.getBytes());
    }

}
