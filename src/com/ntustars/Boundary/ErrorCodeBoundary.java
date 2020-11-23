package com.ntustars.Boundary;
/**
 To handle the Error Code most in Boundary
 @author WANG RUIZHI
 @version 1.0
 @since 2020-11-10
 */

public class ErrorCodeBoundary {
    /**
     print Error when Index Already Exist
     */
    public static void printErrorIndexAlreadyExist(){
        System.out.println("Course index already exist");
    }
    /**
     print Error when Course Already Exist
     */
    public static void printErrorCourseAlreadyExist(){
        System.out.println("Course already exist in DB");
    }
    /**
     print Error when Missing Course Index
     */
    public static void printErrorMissingCourseIndex(){
        System.out.println("There is no such a course Index in DB");
    }
    /**
     print Error when Missing Course
     */
    public static void printErrorMissingCourse(){
        System.out.println("There is no such a course in DB");
    }
    /**
     print when Invalid UserName
     */
    public static void printInvalidUserName(){System.out.println("Invalid Username");}
    /**
     print when Invalid Password
     */
    public static void printInvalidPassword(){System.out.println("Invalid Password");}
}
