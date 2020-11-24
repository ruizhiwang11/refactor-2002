package com.ntustars;

import com.ntustars.Boundary.AdminBoundary;
import com.ntustars.Boundary.LoginBoundary;
import com.ntustars.Boundary.StudentBoundary;
import java.util.ArrayList;
/**
 The main function to start this project
 @author WANG RUIZHI
 @version 1.0
 @since 2020-11-10
 */
public class Main {

    public static void main(String[] args) throws Exception {
        ArrayList <String> loginInfo = LoginBoundary.login();
        if(loginInfo != null){
            if(loginInfo.get(0).equals("STUDENT")){
                StudentBoundary studentBoundary = new StudentBoundary();
                studentBoundary.selectFunction(loginInfo.get(1));
            }
            if(loginInfo.get(0).equals("ADMIN")){
                AdminBoundary adminBoundary = new AdminBoundary();
                adminBoundary.adminMenu();
            }
        }

    }
}
