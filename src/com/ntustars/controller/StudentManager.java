package com.ntustars.controller;
import com.ntustars.entity.Course;
import com.ntustars.entity.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;


import com.ntustars.Boundary.ErrorCodeBoundary;
import com.ntustars.entity.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;


public class StudentManager {
    public static final String SEPARATOR = ",";
<<<<<<< HEAD
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
=======
    private TextReaderWriter textReaderWriter;
    private ArrayList<Student> allStudents;
    private Student student;
    private ErrorCodeBoundary errorCodeBoundary;

    public StudentManager() throws IOException {
        textReaderWriter = new TextReaderWriter();
        allStudents = new ArrayList<>();
        errorCodeBoundary = new ErrorCodeBoundary();
    }

    private ArrayList loadDBStudentInfo(){
        ArrayList stringArray = (ArrayList) textReaderWriter.readtxt("studentInformation.txt");
        return stringArray;
    }

//    public ArrayList<Student> readAllIndex() throws IOException {
//        CourseManager cm = new CourseManager();
//        ArrayList stringArray = (ArrayList)textReaderWriter.readtxt("studentInformation.txt");
//        for (int i = 0 ; i < stringArray.size() ; i++){
//            this.student = new Student();
//            ArrayList<String> indexArray = new ArrayList<>();
//            String st = (String)stringArray.get(i);
//            StringTokenizer star = new StringTokenizer(st , SEPARATOR);
//            student.setUsername(star.nextToken().trim());
//            student.setPassword(star.nextToken().trim());
//            student.setName(star.nextToken().trim());
//            student.setMatricNumber(star.nextToken().trim());
//            student.setGender(star.nextToken().trim());
//            student.setNationality(star.nextToken().trim());
//            student.setAuTaken(Integer.parseInt(star.nextToken().trim()));
//            while (star.hasMoreTokens()){
//                student.addCourseIndex(cm.readCourseIndexbyID(star.nextToken().trim()));
//            }
//        }
//        return allStudents;
//    }

    public Student readStudentbyID(String userName){
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
                while (star.hasMoreTokens()) {
                    student.addCourseIndex(star.nextToken().trim());
                }
                break;
            }
        }
        return student;
    }

    public Student updateSingleStudent(String userName, String courseIndex) {
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
                while (star.hasMoreTokens()) {
                    student.addCourseIndex(star.nextToken().trim());
                }
                student.addCourseIndex(courseIndex);
                break;
            }
        }
        return student;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int updateStudentInfoDB(Student stu) {
//        CourseManager courseManager = new CourseManager();
//        for(CourseIndex coureadSingleStudentrseIndex : stu.getCourseIndexList()){
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
                if (stu.getCourseIndexList().isEmpty()) {
//                    builder.append(SEPARATOR);
                } else {

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

////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public boolean addCourse(String userName) throws IOException {
        String index;
        CourseManager courseMgr = new CourseManager();


>>>>>>> master
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

<<<<<<< HEAD
    public boolean dropCourse(String userName, String index){
=======
    public boolean dropCourse(String userName) {
>>>>>>> master

        //key in username
        //locateStudent()
        //print all course id and index
        //key in index
        //check in index list
        //remove
        //Vacancy + 1
        return false;
    }

<<<<<<< HEAD
    public boolean changeIndex(String userName, String index){
=======
    public boolean courseRegistered(String userName) {

        for (String index : student.getCourseIndexList()) {
            System.out.println(index);
        }
        //key in username
        //locateStudent()
        //print all course id and index
        //key in index
        //check in index list
        //remove
        //Vacancy + 1
        return false;
    }

    public boolean changeIndex(String userName, String index) {
>>>>>>> master
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

<<<<<<< HEAD
    public boolean swapIndex(String username1, String username2){
=======
    public boolean swapIndex(String username1, String username2) {
>>>>>>> master
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

<<<<<<< HEAD
    public int checkVacancy(String index){
=======
    public int checkVacancy(String index) {
>>>>>>> master

        return 0;
    }

<<<<<<< HEAD
    public boolean courseConflict (String userName, String index) {
=======
    public boolean courseConflict(String userName, String index) {
>>>>>>> master
        //check course overlap
        return false;
    }

    public boolean timeConflict(String userName, String index) {
        //check time overlap
        return false;
    }

    public static void main(String[] args) throws IOException {
        StudentManager mgr = new StudentManager();
<<<<<<< HEAD
        mgr.readAllIndex();
        //mgr.addCourse("YCYC","CZ2004");
    }
}
=======
        //mgr.readAllIndex();
        //mgr.addCourse("YCYC","CZ2004");
        Student stu = mgr.readStudentbyID("HAHA123");
        System.out.println("hahah");
        //System.out.println(stu.getCourseIndexList().toString());
        for (String courseIndex : stu.getCourseIndexList()) {
            System.out.println(courseIndex);
        }

    }
}
>>>>>>> master
