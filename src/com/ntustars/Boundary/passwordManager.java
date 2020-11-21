package com.ntustars.Boundary;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;

import java.util.ArrayList;
import java.io.FileInputStream;
import java.util.Scanner;
import java.io.IOException;


public class passwordManager {

        private Key  key = keyGenerator();;
        private String plainPassword; 
    
        KeyGenerator keyGenerator;
        Cipher cipher;
/*
        public passwordManager(String password) {
            plainPassword = password;
        }
*/
        public byte[] encrypt(String password) {
            byte[] cipherText = null;
            plainPassword = password;
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
    

        public ArrayList<String> passwordEncrypt() throws IOException {
            Scanner scanner = new Scanner(new FileInputStream("adminInformation.txt"));
            ArrayList<String> list = new ArrayList<String>();
            while (scanner.hasNext()){
                list.add(scanner.next());
            }
            scanner.close();
            System.out.println(list);
            return list;
            
        }

// testing for function
        public static void main(String[] args) throws IOException {
            String thePassword = "I am the password";

            passwordManager cm = new passwordManager();

            byte[] msg = cm.encrypt(thePassword);
            System.out.println("after encry" + new String(msg));
            System.out.println("after decry：" + cm.decrypt(msg));
        }

}