package com.ntustars.controller;
import com.ntustars.entity.Course;
import com.ntustars.entity.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class StudentManager {
    public static final String SEPARATOR = ",";
    private TextReaderWriter txtReaderWriter;
    private ArrayList<Student> allStudents;
    private Student student;

    public StudentManager() throws IOException {
        txtReaderWriter = new TextReaderWriter();
        allStudents = new ArrayList<>();
    }

    public ArrayList<Student> readAllIndex() throws IOException {
        CourseManager cm = new CourseManager();
        ArrayList stringArray = (ArrayList)txtReaderWriter.readtxt("studentInformation.txt");
        for (int i = 0 ; i < stringArray.size() ; i++){
            Student student = new Student();
            ArrayList<String> indexArray = new ArrayList<>();
            String st = (String)stringArray.get(i);
            StringTokenizer star = new StringTokenizer(st , SEPARATOR);
            student.setUsername(star.nextToken().trim());
            student.setPassword(star.nextToken().trim());
            student.setName(star.nextToken().trim());
            student.setMatricNumber(star.nextToken().trim());
            student.setGender(star.nextToken().trim());
            student.setNationality(star.nextToken().trim());
            student.setAuTaken(Integer.parseInt(star.nextToken().trim()));
            while (star.hasMoreTokens()){
                student.addCourseIndex(cm.readCourseIndexbyID(star.nextToken().trim()));
            }

        }
        return allStudents;
    }

    public int locateStudent(String username) {
        int lineNo = 0;

        return lineNo;
    }


    public boolean addCourse(String userName, String courseID) throws IOException {
        String index;
        CourseManager courseMgr = new CourseManager();

        System.out.println("Please key in the index number");
//        do {
//        Scanner sc = new Scanner(System.in);
//        index = sc.next();}
//        while (1);
        return false;

        //key in username
        //locateStudent()
        //key in course id
        //print index and time
        //key in index
        //check in index list
        //checkVacancy()
        //courseOverlap()
        //timeOverlap()
        //append
        //Vacancy - 1
    }

    public boolean dropCourse(String userName, String index){

        //key in username
        //locateStudent()
        //print all course id and index
        //key in index
        //check in index list
        //remove
        //Vacancy + 1
        return false;
    }

    public boolean changeIndex(String userName, String index){
        //key in username
        //locateStudent()
        //print all course id and index
        //key in index
        //check in index list
        //reverse lookup all index
        //select index
        //checkVacancy()
        //timeOverlap() regardless of the current index
        //remove
        //Vacancy + 1
        //append
        //Vacancy - 1
        return false;
    }

    public boolean swapIndex(String username1, String username2){
        //key in user1
        //locate the db line
        //print all registered course id and index
        //key in index1
        //check in index list

        //key in user 2
        //locate the db line
        //print all registered course id and index
        //key in index2

        //courseOverlap(user1, index2)
        //courseOverlap(user2, index1)
        //timeOverlap(user1, index2)
        //timeOverlap(user2, index1)

        //user1 remove index1, append index2
        //user2 remove index2, append index1
        return false;
    }

    public int checkVacancy(String index){

        return 0;
    }

    public boolean courseConflict (String userName, String index) {
        //check course overlap
        return false;
    }

    public boolean timeConflict(String userName, String index) {
        //check time overlap
        return false;
    }

    public static void main(String[] args) throws IOException {
        StudentManager mgr = new StudentManager();
        mgr.readAllIndex();
        //mgr.addCourse("YCYC","CZ2004");
    }
}
