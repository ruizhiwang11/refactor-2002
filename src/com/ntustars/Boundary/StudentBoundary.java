package com.ntustars.Boundary;

<<<<<<< HEAD
import java.util.Scanner;

public class StudentBoundary {

    public StudentBoundary() {
=======
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
>>>>>>> master

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

<<<<<<< HEAD
    public int getSelection() {
        Scanner sc = new Scanner(System.in);
        String i = "";
        int select = -1;
=======
    private void getSelection() {

        String i = "";
        int s = -1;
>>>>>>> master

        do {
            printStudentFunction();
            i = sc.next();
            try {
                if (Integer.parseInt(i) >= 0 && Integer.parseInt(i) <= 6)
<<<<<<< HEAD
                    select = Integer.parseInt(i);
=======
                    s = Integer.parseInt(i);
>>>>>>> master
                else {
                    System.out.println("Invalid selection! \nPlease key in a number from 0 to 6");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid selection! \nPlease key in a number from 0 to 6");
            }
        }
<<<<<<< HEAD
        while (select == -1) ;
        sc.close();
        return select;
    }

    public static void main(String[] args) {
        StudentBoundary sb = new StudentBoundary();
        int select = sb.getSelection();
//        System.out.println("Your selection is: " + select);
        switch (select) {
            case 0:
                System.out.println("select 0");
            case 1:
                System.out.println("select 1");
            case 2:
                System.out.println("select 2");
            case 3:
                System.out.println("select 3");
            case 4:
                System.out.println("select 4");
            case 5:
                System.out.println("select 5");
            case 6:
                System.out.println("select 6");
        }
    }
}
=======
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
        CourseIndex cI;
        String tmpType = null;
        String tmpDay = null;
        String tmpTimeSlot = null;
        boolean f = false;
        for(CourseIndex tmpIndex : tmpCourse.getCourseIndices()){
            tempCourseIndexArray.add(tmpIndex.getIndex());
        }
        System.out.println("Here are the index for "+ courseID+":");

        for(CourseIndex tmpIndex : tmpCourse.getCourseIndices()){
            System.out.println(tmpIndex.getIndex());
            for(CourseCompo compo : tmpIndex.getCourseCompos()){
                tmpType = compo.getCompoType();
                tmpDay = compo.getDay();
                tmpTimeSlot = compo.getTimeSlot();
                System.out.println("\t"+tmpType+ "\t"+ tmpDay +"\t"+ tmpTimeSlot);
            }
        }
//        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Please key in course index: ");
            courseIndex = sc.next();
            cI = courseManager.readCourseIndexbyID(courseIndex);
            if (cI.getSlot() < 1) {
                System.out.println("There is no slot for this course index. \nPlease choose another index.");
                courseIndex = "00000";
            }
            f = false;
            for (String index : studentManager.readStudentbyID(this.userName).getCourseIndexList()) {
//                System.out.println(courseIndex2.getIndex());
                CourseIndex courseIndex2 = courseManager.readCourseIndexbyID(index);
//                boolean t = courseManager.isCourseIndexCollision(courseIndex2, cI);
                if (courseManager.isCourseIndexCollision(courseIndex2, cI)){
                    f = true;
                    System.out.println("This index has collision with your registered course. " +
                            "\nPlease choose another index.");
                    break;
                }
            }

        } while (!tempCourseIndexArray.contains(courseIndex) || f); //not_in_the_list);

        cI.getSlot();
//        this.courseIndex = courseIndex;
        return courseIndex;
    }

    private void getSwapInfo() {
        //aaaaa
    }

    public void selectFunction(String userName) throws IOException {

        this.userName = userName;
        getSelection();
//        while ()
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
                CourseIndex courseIndex3 = courseManager.readCourseIndexbyID(courseIndex1);
                courseIndex3.addStudent(student.getUsername());
                courseIndex3.setSlot(courseIndex3.getSlot()-1);
                courseManager.updateCourseIndexInfoCompoDB(courseIndex3);
                studentManager.addCourse(this.userName);
                break;
            case 2:
                System.out.println("Select 2. Drop Course");
                getCourseID();
                studentManager.dropCourse(this.userName);
                break;
            case 3:
                System.out.println("Select 3. Check Courses Registered");
                ArrayList<String> courseIndex = studentManager.readStudentbyID(this.userName).getCourseIndexList();
                for (String index : courseIndex) {
                    CourseIndex courseIndex2 = courseManager.readCourseIndexbyID(index);
//                    String courseID = courseManager.getCourseIDbyCourseIndex(index.getIndex());
                    System.out.print(courseManager.getCourseIDbyCourseIndex(courseIndex2.getIndex()));
                    System.out.println("\t"+courseIndex2.getIndex());
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
            sb.selectFunction("RARA123");
        }
}
>>>>>>> master
