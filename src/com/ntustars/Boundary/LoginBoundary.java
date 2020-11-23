package com.ntustars.Boundary;
import com.ntustars.controller.LoginManager;
import com.ntustars.controller.PasswordManager;

import javax.security.sasl.SaslClient;
import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;


public class LoginBoundary  {

    public static ArrayList login (){
        ArrayList<String> loginInfoList = new ArrayList();
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
        String password = "";
        String[] userStr;
        Scanner sc = new Scanner(System.in);
        //Console console = System.console();
        System.out.println("You User Group : ");
        String userGroup = sc.next();
        switch (userGroup){
            case "1":
                System.out.println("");
                System.out.println("            *  Please login as STUDENT   *");
                System.out.println("");
                userStr = loginfunction();
                userName = userStr[0];
                password = userStr[1];
                while (!LoginManager.isValidStudent(userName,password)) {
                    userStr = loginfunction();
                    userName = userStr[0];
                    password = userStr[1];
                }
                System.out.println("");
                System.out.println("            *  You have login as STUDENT  *");
                loginInfoList.add("STUDENT");
                loginInfoList.add(userName);
                return loginInfoList;
            case "2":
                System.out.println("");
                System.out.println("            *  Please login as administrator   *");
                System.out.println("");
                userStr = loginfunction();
                userName = userStr[0];
                password = userStr[1];
                while (!LoginManager.isValidAdmin(userName,password)) {
                    userStr = loginfunction();
                    userName = userStr[0];
                    password = userStr[1];
                }
                System.out.println("");
                System.out.println("            *  You have login as ADMINISTATOR  *");
                loginInfoList.add("ADMIN");
                loginInfoList.add(userName);
                return loginInfoList;
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
        return null;
    }
    //System.out.println(user);

    private static String[] loginfunction  (){
        String[] str = new String[2];
        Scanner sc1 = new Scanner(System.in);
        System.out.println("Enter You User Name : ");
        String user = sc1.next();
        System.out.println("Enter You Password : ");
        String password = sc1.next();
        str[0] = user;
        str[1] = password;
        return str;
    }
}

