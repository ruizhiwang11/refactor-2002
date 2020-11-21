package com.ntustars.Boundary;
import java.io.Console;
//import com.ntustars.controller.passwordManager;




public class LoginBoundary  {
    
    public static void main (String []args){
        System.out.println("");
        System.out.println("");
        System.out.println("    ============================ - Console mode - ======================    "); 
        System.out.println("");
        System.out.println("                            Welcome to XXXXX program                          ");
        System.out.println("");
        System.out.println("    ====================================================================    "); 
        System.out.println("");
        System.out.println("                                    Main Menu                           ");
        System.out.println("");
        System.out.println("                     *  Please Choose Your User Group:    *                      ");
        System.out.println("");
        System.out.println("                     '1' if you are a student"); 
        System.out.println("                     '2' if you are an administrator");
        System.out.println("                     '0' to exist the system"); 
        System.out.println("");
        System.out.println("    ====================================================================    "); 
        System.out.println("");
        System.out.println("");
        Console console = System.console();
        String userGroup = console.readLine("You User Group : ");
        switch (userGroup){
            case "1":
                System.out.println("");
                System.out.println("            *  Please login as STUDENT   *");
                System.out.println("");
                loginfunction();
                System.out.println("");
                System.out.println("            *  You have login as STUDENT  *");
                break;
            case "2":
                System.out.println("");
                System.out.println("            *  Please login as administrator   *");
                System.out.println("");
                loginfunction();
                System.out.println("");
                System.out.println("            *  You have login as ADMINISTATOR  *");
                break;
            case "0":
                System.out.println("");
                System.out.println("*  '0' entered  *");
                System.out.println("*  exist from program  *");
                break;
            default :
                System.out.println("");
                System.out.println("*  Wrong input  *");
                System.out.println("*  exist from program  *");
                break;

        }
        System.out.println("");
        System.out.println("    ====================================================================    "); 
        System.out.println("");
        System.out.println("");
        }
        //System.out.println(user);

        private static void loginfunction  (){
            Console consoleLogin = System.console();
            String user = consoleLogin.readLine("Enter You User Name : ");
            String password = new String (consoleLogin.readPassword("Enter Your Passoword : "));
            /*
            passwordManager crypt = new passwordManager(password);
            
            byte[] msg = crypt.encrypt();
            System.out.println("origina user name : " + user);            
            System.out.println("origina password : " + password);            
            System.out.println("Encrypted password : " + new String(msg));
            System.out.println("After decrypted password : " + crypt.decrypt(msg));
*/
        }

    }



