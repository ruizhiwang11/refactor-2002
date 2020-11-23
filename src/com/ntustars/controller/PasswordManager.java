package com.ntustars.controller;
/**
Password manager to encrypt and decrypt the user password
 @author WANG RUIZHI
 @version 1.0
 @since 2020-11-10
 */
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class PasswordManager
{
    /**
     * SecretKeySpec object
     */
    private static SecretKeySpec secretKey;
    /**
     * Converted binary key
     */
    private static byte[] key;
    /**
     * Encryption 64 bit hash key
     */
    private static final String secret = "ssshhhhhhhhhhh!!!!";

    /**
     * Function to setup the hash key
     * @param myKey setting the key
     */
    public static void setKey(String myKey)
    {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    /**
     * To encrypt the password
     * @param strToEncrypt the password need to encrypt
     * @return the encrypted password
     */
    public static String encrypt(String strToEncrypt)
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        }
        catch (Exception e)
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }
    /**
     * To decrypt the password
     * @param strToDecrypt the password need to encrypt
     * @return the encrypted password
     */
    public static String decrypt(String strToDecrypt)
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        }
        catch (Exception e)
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

}