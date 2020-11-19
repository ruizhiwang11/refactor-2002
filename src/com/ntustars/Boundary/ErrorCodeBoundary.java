package com.ntustars.Boundary;

public class ErrorCodeBoundary {
    public void printErrorIndexAlreadyExist(){
        System.out.println("Course index already exist");
    }
    public void printErrorCourseAlreadyExist(){
        System.out.println("Course already exist in DB");
    }
    public void printErrorMissingCourseIndex(){
        System.out.println("There is no such a course Index in DB");
    }
    public void printErrorMissingCourse(){
        System.out.println("There is no such a course in DB");
    }
}
