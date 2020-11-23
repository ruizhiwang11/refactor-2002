package com.ntustars;

import com.ntustars.Boundary.LoginBoundary;
import com.ntustars.Boundary.StudentBoundary;
import com.ntustars.controller.AdminManager;
import com.ntustars.controller.CourseManager;
import com.ntustars.entity.Course;
import com.ntustars.entity.CourseCompo;
import com.ntustars.entity.CourseIndex;
import com.ntustars.entity.Student;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args){
        ArrayList <String> loginInfo = LoginBoundary.login();
        if(loginInfo != null){
            if(loginInfo.get(0).equals("STUDENT")){
                StudentBoundary studentBoundary = new StudentBoundary();
                studentBoundary.selectFunction(loginInfo.get(1));
            }
        }

    }
}
