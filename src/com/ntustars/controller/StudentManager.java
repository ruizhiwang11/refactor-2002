package com.ntustars.controller;

import com.ntustars.Boundary.ErrorCodeBoundary;
import com.ntustars.entity.Course;
import com.ntustars.entity.CourseIndex;
import com.ntustars.entity.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;


public class StudentManager {
    public static final String SEPARATOR = ",";
    private TextReaderWriter textReaderWriter;
    private ArrayList<Student> allStudents;
    private Student student;
    private ErrorCodeBoundary errorCodeBoundary;

    public StudentManager(){
        textReaderWriter = new TextReaderWriter();
        allStudents = new ArrayList<>();
        errorCodeBoundary = new ErrorCodeBoundary();
    }

    private ArrayList loadDBStudentInfo(){
        ArrayList stringArray = (ArrayList) textReaderWriter.readtxt("studentInformation.txt");
        return stringArray;
    }

    public ArrayList<String> readAllUserName(){
        CourseManager cm = new CourseManager();
        ArrayList<String> userNameList= new ArrayList<>();
        ArrayList stringArray = (ArrayList)textReaderWriter.readtxt("studentInformation.txt");
        for (int i = 0 ; i < stringArray.size() ; i++){
            this.student = new Student();
            ArrayList<String> indexArray = new ArrayList<>();
            String st = (String)stringArray.get(i);
            StringTokenizer star = new StringTokenizer(st , SEPARATOR);
            userNameList.add(star.nextToken().trim());
        }
        return userNameList;
    }

    public Student readSingleStudent(String userName){
        ArrayList studentInfo = loadDBStudentInfo();
        CourseManager cm = new CourseManager();
        Student student = new Student();

        for (int i = 0; i < studentInfo.size(); i++) {
            String st = (String) studentInfo.get(i);
            if (st.contains(userName)) {
                StringTokenizer star = new StringTokenizer(st, SEPARATOR);
                student.setUsername(star.nextToken().trim());
                student.setPassword(PasswordManager.decrypt(star.nextToken().trim()));
                student.setName(star.nextToken().trim());
                student.setMatricNumber(star.nextToken().trim());
                student.setGender(star.nextToken().trim());
                student.setNationality(star.nextToken().trim());
                student.setAuTaken(Integer.parseInt(star.nextToken().trim()));
                while (star.hasMoreTokens()) {
                    student.addCourseIndex(star.nextToken().trim());
                }
                break;
            }
        }
        return student;
    }
    public boolean isCourseIDCollision (String userName, String id) {
        Student student1 = readSingleStudent(userName);
        CourseManager cm = new CourseManager();
//        String id = cm.getCourseIDbyCourseIndex(index);
        if(id != null){
            for (String tmpIndex : student1.getCourseIndexList()) {
                String tmpIndex2 = cm.getCourseIDbyCourseIndex(tmpIndex);
                if(tmpIndex2.equals(null)){
                    System.out.print("Fail to get course id from current course index");
                    System.exit(1);
                }
                else{
                    if (id.equals(tmpIndex2))
                        return true;
                }

            }
            return false;
        }
        else{
            System.out.print("Fail to get course id from current course index");
            System.exit(1);
        }
        return false;
    }

    public Student updateSingleStudent(String userName, String courseIndex, String set) {
        ArrayList studentInfo = loadDBStudentInfo();
        CourseManager cm = new CourseManager();
        Student student = new Student();
        for (int i = 0; i < studentInfo.size(); i++) {
            String st = (String) studentInfo.get(i);
            if (st.contains(userName)) {
                StringTokenizer star = new StringTokenizer(st, SEPARATOR);
                student.setUsername(star.nextToken().trim());
                student.setPassword(star.nextToken().trim());
                student.setName(star.nextToken().trim());
                student.setMatricNumber(star.nextToken().trim());
                student.setGender(star.nextToken().trim());
                student.setNationality(star.nextToken().trim());
                student.setAuTaken(Integer.parseInt(star.nextToken().trim()));
                int au = 0;
                if (set == "ADD") {
                    while (star.hasMoreTokens()) {
                        student.addCourseIndex(star.nextToken().trim());
                    }
                    student.addCourseIndex(courseIndex);
                } else if (set =="REMOVE") {
                    ArrayList<String> tmpCourseIndexList = new ArrayList<>();
                    while (star.hasMoreTokens()) {
                        String tmpCourseIndex = null;
                        tmpCourseIndex = star.nextToken().trim();
                        tmpCourseIndexList.add(tmpCourseIndex);
                    }
                    for (String index : tmpCourseIndexList){
                        if (!index.equals(courseIndex)) {
                            student.addCourseIndex(index);
                        }
                    }
                }
                au = calculateTotalAu(student);
                student.setAuTaken(au);
                break;
            }
        }
        return student;
    }

    public int calculateTotalAu (Student stu) {
        CourseManager courseManager = new CourseManager();
        int sum=0, au;
        for (String index : stu.getCourseIndexList()) {
            au=courseManager.readCourseIndexbyID(index).getAu();
            sum+=au;
        }

        return sum;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int updateStudentInfoDB(Student stu) {
//        CourseManager courseManager = new CourseManager();
//        for(CourseIndex courseIndex : stu.getCourseIndexList()){
//            courseManager.updateCourseIndexInfoCompoDB(courseIndex);
//        }
        ArrayList studentInfo = loadDBStudentInfo();
        for (int i = 0; i < studentInfo.size(); i++) {
            String st = (String) studentInfo.get(i);
            if (st.contains(stu.getUsername())) {
                StringBuilder builder = new StringBuilder();
                builder.append(stu.getUsername());
                builder.append(SEPARATOR);
                builder.append(stu.getPassword());
                builder.append(SEPARATOR);
                builder.append(stu.getName());
                builder.append(SEPARATOR);
                builder.append(stu.getMatricNumber());
                builder.append(SEPARATOR);
                builder.append(stu.getGender());
                builder.append(SEPARATOR);
                builder.append(stu.getNationality());
                builder.append(SEPARATOR);
                builder.append(stu.getAuTaken());
                builder.append(SEPARATOR);
                if (!stu.getCourseIndexList().isEmpty()) {
                    for (String index : stu.getCourseIndexList()) {
//                        builder.append(SEPARATOR);
                        builder.append(index);
                        builder.append(SEPARATOR);
                    }
                }
                studentInfo.set(i, builder.toString());
                Collections.sort(studentInfo);

                textReaderWriter.writetxt("studentInformation.txt", studentInfo);
                return 0;
            }
        }
        return addStudentInfoDB(stu);
    }

    public int addStudentInfoDB(Student stu){
        ArrayList studentInfo = loadDBStudentInfo();
        for (int i = 0; i < studentInfo.size(); i++) {
            String st = (String) studentInfo.get(i);
            if (st.contains(stu.getUsername())) {
                errorCodeBoundary.printErrorIndexAlreadyExist();
                return 1; // error code for duplicated index under the same course
            }
        }
        StringBuilder builder = new StringBuilder();
        builder.append(stu.getUsername());
        builder.append(SEPARATOR);
        builder.append(stu.getPassword());
        builder.append(SEPARATOR);
        builder.append(stu.getName());
        builder.append(SEPARATOR);
        builder.append(stu.getMatricNumber());
        builder.append(SEPARATOR);
        builder.append(stu.getGender());
        builder.append(SEPARATOR);
        builder.append(stu.getNationality());
        builder.append(SEPARATOR);
        builder.append(stu.getAuTaken());
        if (stu.getCourseIndexList().isEmpty()) {
            builder.append(SEPARATOR);
        } else {
            for (String index : stu.getCourseIndexList()) {
                builder.append(SEPARATOR);
                builder.append(index);
                builder.append(SEPARATOR);
            }
        }
        studentInfo.add(builder.toString());

        Collections.sort(studentInfo);
        textReaderWriter.writetxt("studentInformation.txt", studentInfo);
        return 0;
    }

}