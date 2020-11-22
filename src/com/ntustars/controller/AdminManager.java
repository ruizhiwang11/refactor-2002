package com.ntustars.controller;

import com.ntustars.entity.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
<<<<<<< HEAD
=======
import java.util.Collections;
>>>>>>> master
import java.util.StringTokenizer;

public class AdminManager {
    private ArrayList loadAdminInformationDB(){
        ArrayList stringArray = (ArrayList)TextReaderWriter.readtxt("adminInformation.txt");
        return  stringArray;
    }

<<<<<<< HEAD
    private static ArrayList loadAdminInformationDB() throws IOException {
        ArrayList stringArray = (ArrayList)TextReaderWriter.readtxt("adminInformation.txt");
=======
    public static ArrayList loadDBStudentInformation(){
        ArrayList stringArray = (ArrayList)TextReaderWriter.readtxt("studentInformation.txt");
>>>>>>> master
        return  stringArray;
    }
    private CourseManager courseManager = new CourseManager();


    public int addCourseIndex(CourseIndex courseIndex){
        int code = 0;
        code += courseManager.addCourseIndexInCourseAndCourseIndexDB(courseIndex);
        code += courseManager.addCourseIndexInfoCompoDB(courseIndex);
        code += courseManager.addCourseIndexInCourseIndexAndCourseCompoDB(courseIndex);
        return code;
    }

    public int addCourse(Course course){
        return courseManager.addCourseToDB(course);
    }
    public int updateCourseIndex(CourseIndex courseIndex){
        int code = 0;
        code += courseManager.updateCourseIndexInfoCompoDB(courseIndex);
        code += courseManager.updateCourseIndexInCourseIndexAndCourseCompoDB(courseIndex);
        return code;
    }
    public int updateCourse(Course course){
        return courseManager.updateCoursetoDB(course);
    }
<<<<<<< HEAD
    public int addAccessPeriod(String startingDay, String endingDay) throws IOException{
=======
    public int addAccessPeriod(String startingDay, String endingDay){
>>>>>>> master
        ArrayList adminInformation = loadAdminInformationDB();
        String st = (String) adminInformation.get(0);
        StringTokenizer star = new StringTokenizer(st, ",");
        String adminUserName = star.nextToken().trim();
        String adminPassowrd = star.nextToken().trim();
        StringBuilder builder = new StringBuilder();
        builder.append(adminUserName);
        builder.append(",");
        builder.append(adminPassowrd);
        builder.append(",");
        builder.append(startingDay);
        builder.append(",");
        builder.append(endingDay);
        adminInformation.set(0,builder.toString());
        TextReaderWriter.writetxt("adminInformation",adminInformation);
        return 0;
    }
<<<<<<< HEAD
    public ArrayList getStudentByIndex(String index) throws IOException{
        CourseIndex courseIndex = courseManager.readCourseIndexbyID(index);
        ArrayList<String> studentUserNameList = new ArrayList<>();
        for(Student student : courseIndex.getStudentList()){
            studentUserNameList.add(student.getUsername());
        }
        return studentUserNameList;
    }
    public ArrayList getStudentByCourse(String courseID) throws IOException{
=======
    public ArrayList getStudentByIndex(String index){
        CourseIndex courseIndex = courseManager.readCourseIndexbyID(index);
        ArrayList<String> studentUserNameList = new ArrayList<>();
        for(String student : courseIndex.getStudentList()){
            studentUserNameList.add(student);
        }
        return studentUserNameList;
    }
    public ArrayList getStudentByCourse(String courseID){
>>>>>>> master
        Course course = courseManager.readCourseByID(courseID);
        ArrayList<String> studentUserNameList = new ArrayList<>();
        for(CourseIndex courseIndex : course.getCourseIndices()){
            studentUserNameList.addAll(getStudentByIndex(courseIndex.getIndex()));
        }
        return studentUserNameList;
    }
<<<<<<< HEAD
=======

    public int addStudent(Student student){
        ArrayList studentInformation = loadDBStudentInformation();
        String stUserName = student.getUsername();
        // encrypt password
        String encryStPassword = PasswordManager.encrypt(student.getPassword());
        String stName = student.getName();
        String stGender = student.getGender();
        String stMatricNumber = student.getMatricNumber();
        String stNationality = student.getNationality();
        //student.getAuTaken();
        StringBuilder builder = new StringBuilder();
        builder.append(stUserName);
        builder.append(",");
        builder.append(encryStPassword);
        builder.append(",");
        builder.append(stName);
        builder.append(",");
        builder.append(stMatricNumber);
        builder.append(",");
        builder.append(stGender);
        builder.append(",");
        builder.append(stNationality);
        studentInformation.add(builder.toString());
        Collections.sort(studentInformation);
        TextReaderWriter.writetxt("studentInformation.txt", studentInformation);
        return 0;
    }


>>>>>>> master

}

