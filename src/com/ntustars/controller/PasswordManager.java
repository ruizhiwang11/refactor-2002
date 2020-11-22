package com.ntustars.controller;

import java.security.GeneralSecurityException;
import java.security.Key;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
public class PasswordManager
{
    private static Cipher cipher ;

    private static Key key = Generator();
    private  static KeyGenerator keyGenerator;
    public static String encrypt(String text){
        byte[] encryptedByte;
        try {
            cipher = Cipher.getInstance("DES");
            byte[] plainTextByte = text.getBytes();
            cipher.init(Cipher.ENCRYPT_MODE, key);
            encryptedByte = cipher.doFinal(plainTextByte);
            Encoder encoder = Base64.getEncoder(); //ERROR "cannot resolve method"
            String encryptedText = encoder.encodeToString(encryptedByte);
            return encryptedText;
        }catch (GeneralSecurityException e){
            e.printStackTrace();
        }
        return null;
    }
    public static String decrypt(String text){
        try{
            cipher = Cipher.getInstance("DES");
            Decoder decoder = Base64.getDecoder(); //ERROR "cannot resolve method"
            byte[] encryptedTextByte = (byte[]) decoder.decode(text);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
            String decryptedText = new String(decryptedByte);
            return decryptedText;
        }catch (GeneralSecurityException e){
            e.printStackTrace();
        }
        return  null;

    }
    private static Key Generator() {
        try {
            keyGenerator = KeyGenerator.getInstance("DES");
            keyGenerator.init(56);
            key = keyGenerator.generateKey();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return key;
    }
    public static void main(String[] args) throws Exception
    {
        String password = "admin";
        String encryptedText = PasswordManager.encrypt(password);
        System.out.println(encryptedText);
        String decryptedText = PasswordManager.decrypt(encryptedText);
        System.out.println(decryptedText);
    }

}