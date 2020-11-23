package com.ntustars.Boundary;

import com.ntustars.controller.CourseManager;
import com.ntustars.controller.StudentManager;
import com.ntustars.controller.TextReaderWriter;
import com.ntustars.controller.WaitListManager;
import com.ntustars.entity.Course;
import com.ntustars.entity.CourseCompo;
import com.ntustars.entity.CourseIndex;
import com.ntustars.entity.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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

    public StudentBoundary(){

    }

    private void printStudentFunction() {
        System.out.println();
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

    private String getCourseIDAdd(){
        String courseIDStr;
        ArrayList courseArrayList = courseManager.readAllCourseIDFromDB();
        boolean f ;
        do {
            System.out.println("Please key in course ID:");
            courseIDStr = sc.next();
            if (!courseArrayList.contains(courseIDStr)) {
                f = true;
                System.out.println("This course Id is not valid. Please select another course ID. ");
            }
            else
                f = false;
        } while (f); //not_in_the_list
//        this.courseID = courseID;
        return courseIDStr;
    }

    private String getCourseIndexAdd(String courseID, String userName, String exceptionIndex){
        Course tmpCourse = courseManager.readCourseByID(courseID);
        ArrayList<String> tempCourseIndexArray = new ArrayList<>();
        CourseIndex cI;
        String tmpType = null;
        String tmpDay = null;
        String tmpTimeSlot = null;
        boolean f = false;
        ArrayList<String> indexList = new ArrayList<>();
        for(CourseIndex tmpIndex : tmpCourse.getCourseIndices()){
            tempCourseIndexArray.add(tmpIndex.getIndex());
        }
        System.out.println("Here are the index for "+ courseID+":");
        for(CourseIndex tmpIndex : tmpCourse.getCourseIndices()){
            indexList.add(tmpIndex.getIndex());
            System.out.println(tmpIndex.getIndex() + "\t" + "Available Slot: " + tmpIndex.getSlot());
            for(CourseCompo compo : tmpIndex.getCourseCompos()){
                tmpType = compo.getCompoType();
                tmpDay = compo.getDay();
                tmpTimeSlot = compo.getTimeSlot();
                System.out.println("\t"+tmpType+ "\t"+ tmpDay +"\t"+ tmpTimeSlot);
            }
        }
        do {
            boolean f1 ;
            do {
                System.out.println("Please key in course index: ");
                courseIndex = sc.next();
                if (!indexList.contains(courseIndex)){
                    f1 = true;
                    System.out.println("This course index is not valid. Please key in a valid course index.");
                }
                else
                    f1 = false;
            } while (f1);
            cI = courseManager.readCourseIndexbyID(courseIndex);
            if (cI.getSlot() < 1) {
                System.out.println("There is no slot for this course index. ");
                System.out.println("You will be placed into the waiting list");
                WaitListManager.updateWaitList(courseIndex,userName);
                System.exit(0);
            }
            f = false;
            for (String index : studentManager.readSingleStudent(this.userName).getCourseIndexList()) {
//                System.out.println(courseIndex2.getIndex());
                if (!index.equals(exceptionIndex)) {
                    CourseIndex courseIndex2 = courseManager.readCourseIndexbyID(index);
//                boolean t = courseManager.isCourseIndexCollision(courseIndex2, cI);
                    ////////////////////////////////////////////////////////////////////////////////////////////////////////////below exception index
                    if (courseManager.isCourseIndexCollision(courseIndex2, cI)) {
                        f = true;
                        System.out.println("This index has collision with your registered course. " +
                                "\nPlease choose another index.");
                        break;
                    }
                }
            }
        } while (!tempCourseIndexArray.contains(courseIndex) || f); //not_in_the_list);
        return courseIndex;
    }


    private String getCourseIndexRemove(String userName){
        String courseIndexStr;
        ArrayList<String> indexArrayList = studentManager.readSingleStudent(userName).getCourseIndexList();
        String tmpCourseID;
        System.out.println("Your registered courses and indexes:");
        for (String index : indexArrayList){
            tmpCourseID = courseManager.readCourseIndexbyID(index).getCourseID();
            System.out.println(tmpCourseID + "\t" + index);
        }
        do {
            System.out.println("Please key in course INDEX:");
            courseIndexStr = sc.next();
        } while (!indexArrayList.contains(courseIndexStr)); //not_in_the_list
//        this.courseID = courseID;
        return courseIndexStr;
    }

    private String getUser2(){
        String userName;
        do {
            System.out.println("Please key in user name of User2:");
            userName = sc.next();
        } while (!studentManager.readAllUserName().contains(userName));
        return userName;
    }

    private void updateCourseIndexInfoAdd (String userName, String courseIndex){
        Student student = studentManager.updateSingleStudent(userName, courseIndex, "ADD");
        studentManager.updateStudentInfoDB(student);
        CourseIndex courseIndex11 = courseManager.readCourseIndexbyID(courseIndex);
        courseIndex11.addStudent(student.getUsername());
        courseIndex11.setSlot(courseIndex11.getSlot() - 1);
        courseManager.updateCourseIndexInfoCompoDB(courseIndex11);
    }

    private void updateCourseIndexInfoRemove (String userName, String courseIndex) {
        Student student2 = studentManager.updateSingleStudent(userName, courseIndex, "REMOVE");
        studentManager.updateStudentInfoDB(student2);
        CourseIndex courseIndex21 = courseManager.readCourseIndexbyID(courseIndex);
        courseIndex21.dropStudent(student2.getUsername());
        courseIndex21.setSlot(courseIndex21.getSlot() + 1);
        courseManager.updateCourseIndexInfoCompoDB(courseIndex21);
    }

    public void selectFunction(String userName){

        this.userName = userName;
        do {
            getSelection();
            switch (this.select) {
                case 1:
                    System.out.println("Select 1. Add Course");
                    boolean f;
                    String courseID;
                    do {
                        f = false;
                        courseID = getCourseIDAdd();
                        if (studentManager.isCourseIDCollision(userName, courseID)) {
                            System.out.println("You have already registered this course!");
                            f = true;
                        }
                    } while (f);
                    String courseIndexToAddStr = getCourseIndexAdd(courseID,userName,null);
                    Student student = studentManager.updateSingleStudent(userName, courseIndexToAddStr, "ADD");
                    studentManager.updateStudentInfoDB(student);
                    CourseIndex courseIndexToAdd = courseManager.readCourseIndexbyID(courseIndexToAddStr);
                    courseIndexToAdd.addStudent(student.getUsername());
                    courseIndexToAdd.setSlot(courseIndexToAdd.getSlot() - 1);
                    courseManager.updateCourseIndexInfoCompoDB(courseIndexToAdd);
                    System.out.println("You have successfully added " + courseID + ": " + courseIndexToAddStr);
                    break;
                case 2:
                    System.out.println("Select 2. Drop Course");
                    String courseIndexStr;
                    courseIndexStr = getCourseIndexRemove(userName);

                    Student student2 = studentManager.updateSingleStudent(userName, courseIndexStr, "REMOVE");
                    studentManager.updateStudentInfoDB(student2);
                    CourseIndex courseIndex21 = courseManager.readCourseIndexbyID(courseIndexStr);
                    courseIndex21.dropStudent(student2.getUsername());
                    courseIndex21.setSlot(courseIndex21.getSlot() + 1);
                    courseManager.updateCourseIndexInfoCompoDB(courseIndex21);
                    System.out.println("You have successfully dropped " + courseIndexStr);
                    if(courseIndex21.getSlot() == 1){
                        HashMap<String,ArrayList<String>> waitListMap = WaitListManager.readWaitList();
                        if(waitListMap != null){
                            if(waitListMap.get(courseIndexStr)!=null){
                                System.out.println("Adding the student from Waiting list");
                                String tmpStudentStr = WaitListManager.popWaitList(courseIndexStr);
                                courseID = courseIndex21.getCourseID();
                                if (studentManager.isCourseIDCollision(userName, courseID)) {
                                    System.out.println("The student in the Waiting list has registered this course!");
                                    System.exit(1);
                                }
                                String waitListCourseIndexToAddStr = courseIndex21.getIndex();
                                Student waitListstudent = studentManager.updateSingleStudent(tmpStudentStr, waitListCourseIndexToAddStr, "ADD");
                                studentManager.updateStudentInfoDB(waitListstudent);
                                CourseIndex waitListCourseIndexToAdd = courseManager.readCourseIndexbyID(waitListCourseIndexToAddStr);
                                waitListCourseIndexToAdd.addStudent(waitListstudent.getUsername());
                                waitListCourseIndexToAdd.setSlot(waitListCourseIndexToAdd.getSlot() - 1);
                                courseManager.updateCourseIndexInfoCompoDB(waitListCourseIndexToAdd);
                            }
                        }
                    }
                    break;
                case 3:
                    System.out.println("Select 3. Check Courses Registered");
                    System.out.print("Your AU taken: ");
                    System.out.println(studentManager.readSingleStudent(this.userName).getAuTaken());
                    System.out.println("Your registered courses:");
                    ArrayList<String> courseIndex = studentManager.readSingleStudent(this.userName).getCourseIndexList();
                    for (String index : courseIndex) {
                        CourseIndex courseIndex3 = courseManager.readCourseIndexbyID(index);
//                    String courseID = courseManager.getCourseIDbyCourseIndex(index.getIndex());
                        System.out.print(courseManager.getCourseIDbyCourseIndex(courseIndex3.getIndex()));
                        System.out.println("\t" + courseIndex3.getIndex());
                    }
                    break;

                case 4:
                    System.out.println("4. Check Vacancies Available");
                    String tmpID = getCourseIDAdd();
//                    ArrayList<String> tmpIndexList = ;
//                    courseManager.readCourseByID(tmpIndex).getCourseIndices();
                    for (CourseIndex courseIndex1 : courseManager.readCourseByID(tmpID).getCourseIndices()) {
                        System.out.print(courseIndex1.getIndex() + "\t" + "Slots Available: ");
                        System.out.println(courseIndex1.getSlot());
                        for (CourseCompo courseCompo : courseIndex1.getCourseCompos()) {
                            System.out.print("\t" + courseCompo.getCompoType() + "\t");
                            System.out.print(courseCompo.getDay() + "\t");
                            System.out.println(courseCompo.getTimeSlot());
                        }
                    }
                    break;

                case 5:
                    System.out.println("5. Change Index Number of Course");
                    String tmpID1 = getCourseIDAdd();
                    String tmpCurrentIndex = null;
                    for (String index : studentManager.readSingleStudent(userName).getCourseIndexList()) {
                        if (courseManager.getCourseIDbyCourseIndex(index).equals(tmpID1)) {
                            tmpCurrentIndex = index;
                            break;
                        }
                    }
                    if (tmpCurrentIndex.equals(null)){
                        System.out.println("You do not have this course.");
                    }
                    else {
                        System.out.println("Your current register index for " + tmpID1 + " is " + tmpCurrentIndex);
                        String newIndex = getCourseIndexAdd(tmpID1,userName,tmpCurrentIndex);
                        updateCourseIndexInfoRemove(userName, tmpCurrentIndex);
                        updateCourseIndexInfoAdd(userName, newIndex);
                        System.out.println("You have changed " + tmpID1 + " index from "+ tmpCurrentIndex + " to " + newIndex);
                    }
                    break;
                case 6:
                    System.out.println("Select 4. Swap Index Number with Another Student");

                    String courseIndex411, courseIndex421;
                    System.out.println("Key in the Index to swap: ");
                    courseIndex411 = getCourseIndexRemove(userName);
                    System.out.println("PLease the user name your want to swap with");
                    String userName42 = getUser2();
                    System.out.println("Key in the Index to swap with main user: ");
                    courseIndex421 = getCourseIndexRemove(userName42);
                    int s = 0;
                    for ( String index : studentManager.readSingleStudent(userName).getCourseIndexList()) {
                        if (!index.equals(courseIndex411)) {
                            if (courseManager.isCourseIndexCollision(courseManager.readCourseIndexbyID(index),
                                    courseManager.readCourseIndexbyID(courseIndex421))) {
                                s = 1;
                            }
                        }
                    }
                    for ( String index : studentManager.readSingleStudent(userName42).getCourseIndexList()) {
                        if (!index.equals(courseIndex421)) {
                            if (courseManager.isCourseIndexCollision(courseManager.readCourseIndexbyID(index),
                                    courseManager.readCourseIndexbyID(courseIndex411))) {
                                s = 2;
                            }
                        }
                    }

                    if (s == 0){
                        updateCourseIndexInfoRemove(userName,courseIndex411);
                        updateCourseIndexInfoRemove(userName42,courseIndex421);

                        updateCourseIndexInfoAdd(userName,courseIndex421);
                        updateCourseIndexInfoAdd(userName42,courseIndex411);

                        System.out.println("You have successfully swapped " + courseIndex411 + " with "+ userName42 +"'s " + courseIndex421 );

                    } else if (s == 1) {
                        System.out.println("Course collision with " + userName);
                        System.exit(0);
                    } else if (s == 2) {
                        System.out.println("Course collision with " + userName42);
                        System.exit(0);
                    }
                    break;
            }
        } while (this.select != 0);
        System.exit(0);
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        StudentBoundary sb = new StudentBoundary();

        System.out.println("Please key in your user name:");
        String userName = sc.next();

        sb.selectFunction(userName);
    }
}
