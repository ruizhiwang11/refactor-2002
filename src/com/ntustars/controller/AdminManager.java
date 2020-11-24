package com.ntustars.controller;
import com.ntustars.entity.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.StringTokenizer;
/**
 Controller to control all the student related operation.
 @author WANG RUIZHI
 @author FENG HAOLIN
 @version 1.0
 @since 2020-11-10
 */
public class AdminManager {
    /**
    load db
     */
    private ArrayList loadAdminInformationDB(){
        ArrayList stringArray = (ArrayList)TextReaderWriter.readtxt("adminInformation.txt");
        return  stringArray;
    }
    /**
     load db
     */
    public static ArrayList loadDBStudentInformation(){
        ArrayList stringArray = (ArrayList)TextReaderWriter.readtxt("studentInformation.txt");
        return  stringArray;
    }
    /**
     Course manager
     */
    private CourseManager courseManager = new CourseManager();
    /**
     add Course Index to DB
     @param courseIndex course index object
     @return  to tell whether the operation is successful
     */
    public int addCourseIndex(CourseIndex courseIndex){
        int code = 0;
        code += courseManager.addCourseIndexInCourseAndCourseIndexDB(courseIndex);
        code += courseManager.addCourseIndexInfoCompoDB(courseIndex);
        code += courseManager.addCourseIndexInCourseIndexAndCourseCompoDB(courseIndex);
        return code;
    }
    /**
     add Course to DB
     @param course course object
     @return  to tell whether the operation is successful
     */
    public int addCourse(Course course){
        return courseManager.addCourseToDB(course);
    }
    /**
     update Course to DB
     @param courseIndex course index object
     @return int tell whether the operation is successful
     */
    public int updateCourseIndex(CourseIndex courseIndex){
        int code = 0;
        code += courseManager.updateCourseIndexInfoCompoDB(courseIndex);
        code += courseManager.updateCourseIndexInCourseIndexAndCourseCompoDB(courseIndex);
        return code;
    }
    /**
     update Course to DB
     @param course course object
     @return int tell whether the operation is successful
     */
    public int updateCourse(Course course){
        return courseManager.updateCoursetoDB(course);
    }
    /**
     add access period to DB
     @param startingDay starting day
     @param endingDay ending
     @return int tell whether the operation is successful
     */
    public int addAccessPeriod(String startingDay, String endingDay){
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
        TextReaderWriter.writetxt("adminInformation.txt",adminInformation);
        return 0;
    }
    /**
     get student by course index
     @param index
     @return int tell whether the operation is successful
     */
    public ArrayList getStudentByIndex(String index){
        CourseIndex courseIndex = courseManager.readCourseIndexbyID(index);
        if(courseIndex == null)
        {
            return null;
        }
        ArrayList<Student> studentList = new ArrayList<>();
        for(String studentStr : courseIndex.getStudentList()){
            StudentManager studentManager = new StudentManager();
            Student student = studentManager.readSingleStudent(studentStr);
            studentList.add(student);
        }
        return studentList;
    }
    /**
     get student by course
     @param courseID
     @return int tell whether the operation is successful
     */
    public ArrayList getStudentByCourse(String courseID){
        Course course = courseManager.readCourseByID(courseID);
        ArrayList<Student> studentList = new ArrayList<>();
        for(CourseIndex courseIndex : course.getCourseIndices()){
            studentList.addAll(getStudentByIndex(courseIndex.getIndex()));
        }
        return studentList;
    }
    /**
     add student to DB
     @param student student object
     @return int tell whether the operation is successful
     */
    public int addStudent(Student student){
        if(student == null){
            System.out.println("Student is null");
            return 1;
        }
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
        builder.append(",");
        builder.append(student.getAuTaken());
        builder.append(",");
        studentInformation.add(builder.toString());
        Collections.sort(studentInformation);
        TextReaderWriter.writetxt("studentInformation.txt", studentInformation);
        return 0;
    }
    /**
     update student to DB
     @param student student object
     @return int tell whether the operation is successful
     */
    public int updateStudent(Student student){
        ArrayList studentInformation = loadDBStudentInformation();
        for(int i=0; i<studentInformation.size();i++){
            String st = (String)studentInformation.get(i);
            if(st.contains(student.getUsername())){
                String encryStPassword = PasswordManager.encrypt(student.getPassword());
                StringTokenizer star = new StringTokenizer(st, ",");
                String stName = student.getName();
                String stGender = student.getGender();
                String stMatricNumber = student.getMatricNumber();
                String stNationality = student.getNationality();
                StringBuilder builder = new StringBuilder();
                builder.append(student.getUsername());
                builder.append(",");
                star.nextToken().trim();
                builder.append(encryStPassword);
                builder.append(",");
                star.nextToken().trim();
                builder.append(stName);
                builder.append(",");
                star.nextToken().trim();
                builder.append(stMatricNumber);
                builder.append(",");
                star.nextToken().trim();
                builder.append(stGender);
                builder.append(",");
                star.nextToken().trim();
                builder.append(stNationality);
                builder.append(",");
                star.nextToken().trim();
                builder.append(student.getAuTaken());
                builder.append(",");
                star.nextToken().trim();
                while (star.hasMoreTokens()){
                    builder.append(star.nextToken().trim());
                    builder.append(",");
                }
                studentInformation.set(i,builder.toString());
                Collections.sort(studentInformation);
                TextReaderWriter.writetxt("studentInformation.txt", studentInformation);
                return 0;
            }
        }
        System.out.println("No such a student in DB");
        return 1;
    }

}

