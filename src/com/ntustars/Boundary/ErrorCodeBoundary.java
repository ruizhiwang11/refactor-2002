package com.ntustars.Boundary;

public class ErrorCodeBoundary {
    public static void printErrorIndexAlreadyExist(){
        System.out.println("Course index already exist");
    }
    public static void printErrorCourseAlreadyExist(){
        System.out.println("Course already exist in DB");
    }
    public static void printErrorMissingCourseIndex(){
        System.out.println("There is no such a course Index in DB");
    }
    public static void printErrorMissingCourse(){
        System.out.println("There is no such a course in DB");
    }
    public static void printInvalidUserName(){System.out.println("Invalid Username");}
    public static void printInvalidPassword(){System.out.println("Invalid Password");}
}
