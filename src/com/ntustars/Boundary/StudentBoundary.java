package com.ntustars.Boundary;

import com.ntustars.controller.CourseManager;
import com.ntustars.controller.StudentManager;
import com.ntustars.entity.Course;
import com.ntustars.entity.CourseCompo;
import com.ntustars.entity.CourseIndex;
import com.ntustars.entity.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentBoundary {
    private Student student;
    private int select;
    private String userName;
    private String courseID;
    private String courseIndex;
    StudentManager studentManager = new StudentManager();
    CourseManager courseManager = new CourseManager();
    Scanner sc = new Scanner(System.in);

    public StudentBoundary() throws IOException {

    }

    private void printStudentFunction() {
        System.out.println("==============- CONSOLE - STUDENT MODE -==============");
        System.out.println("                       Main Menu             ");
        System.out.println("______________________________________________________");
        System.out.println("|1. Add Course                                       |");
        System.out.println("|2. Drop Course                                      |");
        System.out.println("|3. Check Courses Registered                         |");
        System.out.println("|4. Check Vacancies Available                        |");
        System.out.println("|5. Change Index Number of Course                    |");
        System.out.println("|6. Swap Index Number with Another Student           |");
        System.out.println("|----------------------------------------------------|");
        System.out.println("|0. Exit                                             |");
        System.out.println("|----------------------------------------------------|");
        System.out.println("______________________________________________________");

        System.out.println("\n\nWhat would you like to do?");
        System.out.println("Please enter your choice:");
    }

    private void getSelection() {

        String i = "";
        int s = -1;

        do {
            printStudentFunction();
            i = sc.next();
            try {
                if (Integer.parseInt(i) >= 0 && Integer.parseInt(i) <= 6)
                    s = Integer.parseInt(i);
                else {
                    System.out.println("Invalid selection! \nPlease key in a number from 0 to 6");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid selection! \nPlease key in a number from 0 to 6");
            }
        }
        while (s == -1) ;
        this.select = s;
    }

    private String getCourseID() throws IOException {
        String courseIDStr;
        ArrayList courseArrayList = courseManager.readAllCourseIDFromDB();


        do {
            System.out.println("Please key in course ID:");
            courseIDStr = sc.next();
        } while (!courseArrayList.contains(courseIDStr)); //not_in_the_list
//        this.courseID = courseID;
        return courseIDStr;
    }

    private String getCourseIndex (String courseID) throws IOException {
        String courseIndex;
        Course tmpCourse = courseManager.readCourseByID(courseID);
        ArrayList<String> tempCourseIndexArray = new ArrayList<>();
        String tmpType = null;
        String tmpDay = null;
        String tmpTimeSlot = null;
        for(CourseIndex tmpIndex : tmpCourse.getCourseIndices()){
            tempCourseIndexArray.add(tmpIndex.getIndex());
        }
        System.out.println("Here are the index for "+ courseID);

        for(CourseIndex tmpIndex : tmpCourse.getCourseIndices()){
            for(CourseCompo compo : tmpIndex.getCourseCompos()){
                tmpType = compo.getCompoCype();
                tmpDay = compo.getDay();
                tmpTimeSlot = compo.getTimeSlot();
                System.out.println(tmpIndex.getIndex() +"\t"+ tmpTimeSlot + "\t"+ tmpDay);
            }

        }
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Please key in course index: ");
            courseIndex = sc.next();
        } while (!tempCourseIndexArray.contains(courseIndex)); //not_in_the_list);
//        this.courseIndex = courseIndex;
        return courseIndex;
    }

    private void getSwapInfo() {
        //aaaaa
    }

    public void selectFunction(String userName) throws IOException {

        this.userName = userName;
        getSelection();
        switch (this.select) {
            case 0:
                System.out.println("EXIT");
                System.exit(0);
                break;
            case 1:
                System.out.println("Select 1. Add Course");
                String courseID =getCourseID();
                String courseIndex1 = getCourseIndex(courseID);

                Student student = studentManager.updateSingleStudent(userName,courseIndex1);
                studentManager.updateStudentInfoDB(student);
                studentManager.addCourse(this.userName);
                break;
            case 2:
                System.out.println("Select 2. Drop Course");
                getCourseID();
                studentManager.dropCourse(this.userName);
                break;
            case 3:
                System.out.println("Select 3. Check Courses Registered");
                ArrayList<CourseIndex> courseIndex = studentManager.readSingleStudent(this.userName).getCourseIndexList();
                for (CourseIndex index : courseIndex) {
//                    String courseID = courseManager.getCourseIDbyCourseIndex(index.getIndex());
                    System.out.print(courseManager.getCourseIDbyCourseIndex(index.getIndex()));
                    System.out.println("\t"+index.getIndex());
                }
                break;
            case 4:
                System.out.println("Select 4. Check Vacancies Available");
                getCourseID();
                courseManager.readCourseIndexbyID(this.courseID);
                break;
            case 5:
                System.out.println("Select 5. Change Index Number of Course");
//                getCourseIndex();
                studentManager.changeIndex(this.userName, this.courseIndex);
                break;
            case 6:
                System.out.println("Select 6. Swap Index Number with Another Student");
//                getCourseIndex();
                studentManager.swapIndex("tom", "jerry");
                break;


        }
    }

    public static void main(String[] args) throws IOException {
            StudentBoundary sb = new StudentBoundary();
            StudentManager studentManager = new StudentManager();
            sb.selectFunction("HAHA123");
        }
}