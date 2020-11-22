package com.ntustars.Boundary;
import javax.security.sasl.SaslClient;
import java.io.Console;
import java.util.Scanner;


public class LoginBoundary  {

    public static void login (){
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
        String userName = "";
        Scanner sc = new Scanner(System.in);
        //Console console = System.console();
        System.out.println("You User Group : ");
        String userGroup = sc.next();
        switch (userGroup){
            case "1":
                System.out.println("");
                System.out.println("            *  Please login as STUDENT   *");
                System.out.println("");
                userName = loginfunction();
                System.out.println("");
                System.out.println("            *  You have login as STUDENT  *");
                break;
            case "2":
                System.out.println("");
                System.out.println("            *  Please login as administrator   *");
                System.out.println("");
                userName = loginfunction();
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

    private static String loginfunction  (){
//        Console consoleLogin = System.console();
//        String user = consoleLogin.readLine("Enter You User Name : ");
//        String password = new String (consoleLogin.readPassword("Enter Your Passoword : "));
//        return user;
        Scanner sc1 = new Scanner(System.in);
        System.out.println("Enter You User Name : ");
        String user = sc1.next();
        System.out.println("Enter You Passwprd : ");
        String password = sc1.next();
        return user;
    }

    public static void main(String[] args) {
        login();
    }

    public static void main(String[] args) {
        login();
    }

}

