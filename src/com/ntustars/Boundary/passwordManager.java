package com.ntustars.Boundary;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;

public class passwordManager {

        private Key key; 
        private String plainPassword; 
    
        KeyGenerator keyGenerator;
        Cipher cipher;
    
        passwordManager(String password) {
            Key key = keyGenerator();
            plainPassword = password;
        }
    
        public byte[] encrypt() {
            byte[] cipherText = null;
    
            try {
                cipher = Cipher.getInstance("DES");
                cipher.init(Cipher.ENCRYPT_MODE, key); 
                cipherText = cipher.doFinal(plainPassword.getBytes()); 
            } catch (Exception e) {
                e.printStackTrace();
            }
            return cipherText;
        }
    
        public byte[] getBinaryKey(Key k) {
            byte[] bk = null;
            try {
                bk = cipher.wrap(k);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return bk;
        }
    
        public String decrypt(byte[] cipherText) {
            byte[] sourceText = null;
    
            try {
                cipher = Cipher.getInstance("DES");
                cipher.init(Cipher.DECRYPT_MODE, key);
                sourceText = cipher.doFinal(cipherText);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new String(sourceText);
    
        }
    
        public Key keyGenerator() {
            try {
                keyGenerator = KeyGenerator.getInstance("DES");
                keyGenerator.init(56); 
                key = keyGenerator.generateKey();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return key;
        }
    

        //public void passwordEncrypt(String password){}


        public static void main(String[] args) {
            String thePassword = "I am the password"; 
            passwordManager cm = new passwordManager(thePassword);
            byte[] msg = cm.encrypt();
            System.out.println("after encry" + new String(msg));
            System.out.println("after decry：" + cm.decrypt(msg));
    
        }
    
        


}
