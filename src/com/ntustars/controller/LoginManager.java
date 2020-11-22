package com.ntustars.controller;

import com.ntustars.Boundary.ErrorCodeBoundary;
import com.ntustars.entity.Admin;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class LoginManager {
    private static ArrayList loadAdminInformationDB(){
        ArrayList stringArray = (ArrayList)TextReaderWriter.readtxt("adminInformation.txt");
        return  stringArray;
    }

    public static ArrayList loadDBStudentInformation(){
        ArrayList stringArray = (ArrayList)TextReaderWriter.readtxt("studentInformation.txt");
        return  stringArray;
    }
    public static boolean isValidAdmin(String username, String password){
        Admin admin = readUserAdminFromDB();
        if (!admin.getUsername().equals(username) ){
            ErrorCodeBoundary.printInvalidUserName();
            return false;
        }
        else{
            if (!admin.getPassword().equals(password)){
                ErrorCodeBoundary.printInvalidPassword();
                return  false;
            }
            else {
                return true;
            }
        }
    }
    public static boolean isValidStudent(String username, String password){
        ArrayList<ArrayList<String>> studentUserInfo = readUserStudentFromDB();
        for(int i =0; i<studentUserInfo.size();i++){
            if (username.equals(studentUserInfo.get(i).get(0))){
                if(password.equals(studentUserInfo.get(i).get(1))){
                    return true;
                }
                ErrorCodeBoundary.printInvalidPassword();
                return false;
            }
        }
        ErrorCodeBoundary.printInvalidUserName();
        return false;
    }
    private static Admin readUserAdminFromDB(){
        ArrayList adminInformation = loadAdminInformationDB();
        String st = (String) adminInformation.get(0);
        StringTokenizer star = new StringTokenizer(st, ",");
        String adminUserName = star.nextToken().trim();
        String adminPassword = PasswordManager.decrypt(star.nextToken().trim());
        Admin admin = new Admin(adminUserName, adminPassword);
        return admin;
    }
    private static ArrayList readUserStudentFromDB(){
        ArrayList studentInformation = loadDBStudentInformation();
        ArrayList<ArrayList<String>> studentUserInfo = new ArrayList<>();
        for(int i =0; i < studentInformation.size(); i++){
            String st = (String) studentInformation.get(i);
            StringTokenizer star = new StringTokenizer(st, ",");
            ArrayList<String> tempArray = new ArrayList<>();
            tempArray.add(star.nextToken().trim());
            tempArray.add(PasswordManager.decrypt(star.nextToken().trim()));
            studentUserInfo.add(tempArray);
        }
        return studentUserInfo;
    }
}
