package com.ntustars.Boundary;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.ntustars.controller.*;
import com.ntustars.entity.Course;
import com.ntustars.entity.CourseCompo;
import com.ntustars.entity.CourseIndex;
import com.ntustars.entity.Student;

import java.io.IOException;
import java.lang.*;

public class AdminBoundary {
    private AdminManager adminManager;
    private String nameExist,courseIDExist,courseIndexExist;
    private CourseManager courseMgr;
    //private ErrorCodeBoundary errorCodeBoundary;
    private StudentManager studentMgr;

    public AdminBoundary() throws IOException {
        this.adminManager = new AdminManager();
        this.courseMgr = new CourseManager();
        //this.errorCodeBoundary = new ErrorCodeBoundary();
        this.studentMgr = new StudentManager();
        this.nameExist = null;
        this.courseIDExist = null;
        this.courseIndexExist = null;
    }

    public void adminMenu() throws Exception {
        System.out.println("===============- CONSOLE - ADMIN MODE -===============");
        System.out.println("                       Main Menu             ");
        System.out.println("______________________________________________________\n");
        System.out.println("|1. Set Student Access Period                        |");
        System.out.println("|2. Update Student Access Period                     |");
        System.out.println("|3. Add A Student                                    |");
        System.out.println("|4. Update A Student                                 |");
        System.out.println("|5. Add A Course                                     |");
        System.out.println("|6. Update A Course                                  |");
        System.out.println("|7. Check Available Slot For an index number         |");
        System.out.println("|8. Print student list by index number               |");
        System.out.println("|9. Print student list by course                     |");
        System.out.println("|----------------------------------------------------|");
        System.out.println("|0. Exit                                             |");
        System.out.println("|----------------------------------------------------|");
        System.out.println("______________________________________________________");

        System.out.println("\n\nWhat would you like to do?");
        System.out.println("Please enter your choice:");

        try {
            this.adminMenuSelection(this.enterChoice());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private int enterChoice() {
        int choice;
        Scanner sc = new Scanner(System.in);

        do {
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input! Please enter your choice:");
                sc.next();
            }
            choice = sc.nextInt();
            if((choice < 0) || (choice > 9))
                System.out.println("Invalid input! Please enter your choice:");
        } while ((choice < 0) || (choice > 9));

        return choice;
    }

    private void adminMenuSelection(int choice) throws Exception {
        switch (choice) {
            case 1:
                System.out.println("1. Set Student Access Period");
                this.setStudAccPeriod();
                break;

            case 2:
                System.out.println("2. Update Student Access Period");
                this.updStudAccPeriod();
                break;

            case 3:
                System.out.println("3. Add A Student");
                this.addStudentParticulars();
                break;

            case 4:
                System.out.println("4. Update A Student");
                this.updStudentParticulars();
                break;

            case 5:
                System.out.println("5. Add A Course");
                this.addCourse();
                break;

            case 6:
                System.out.println("6. Update A Course");
                this.updCourse();
                break;

            case 7:
                System.out.println("7. Check Available Slot For an index number");
                this.checkVacancy();
                break;

            case 8:
                System.out.println("8. Print student list by index ");
                this.printStudListByIndex();
                break;

            case 9:
                System.out.println("9. Print student list by course ");
                this.printStudListByCourse();
                break;

            default:
                System.out.println("\nHang on a moment while we sign you out.");
                System.out.println("......");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("See you next time! Bye bye ;)\n");
                // Exit ??????????
                // System.exit(0);????????????????????????
        }
    }

    private void setStudAccPeriod() throws IOException {
        
        Scanner sc = new Scanner(System.in);
        
        String startDateTime,endDateTime=null;
        Calendar startCal = null, endCal = null;
        String[] dateTimeSplit;

        do {
            System.out.println("\nPlease enter start date and time (yyyy MM dd HH mm):");
            startDateTime = sc.nextLine();
            while(!startDateTime.matches("^\\d{4} \\d{2} \\d{2} \\d{2} \\d{2}$"))
            {
        	    System.out.println("Invalid input! Please enter again!");
        	    System.out.println("\nPlease enter start date and time (yyyy MM dd HH mm):");
                startDateTime = sc.nextLine();
            }
            dateTimeSplit = startDateTime.split(" ");
            startDateTime = dateTimeSplit[0]+"-"+dateTimeSplit[1]+"-"+dateTimeSplit[2]+" "+dateTimeSplit[3]+":"+dateTimeSplit[4];
            //System.out.println("////////"+startDateTime);
            startCal = DateTimeManager.convertAccessStringToCalendar(startDateTime);
            //System.out.println("////////"+startCal);
            if(startCal  == null){
                System.out.println("Invalid input! Please enter again!");
                continue;
            }

            System.out.println("\nPlease enter end date and time (yyyy MM dd HH mm):");
            endDateTime = sc.nextLine();
            while(!endDateTime.matches("^\\d{4} \\d{2} \\d{2} \\d{2} \\d{2}$"))
            {
        	    System.out.println("Invalid input! Please enter again!");
        	    System.out.println("\nPlease enter end date and time (yyyy MM dd HH mm):");
                endDateTime = sc.nextLine();
            }
            dateTimeSplit = endDateTime.split(" ");
            endDateTime = dateTimeSplit[0]+"-"+dateTimeSplit[1]+"-"+dateTimeSplit[2]+" "+dateTimeSplit[3]+":"+dateTimeSplit[4];
            endCal = DateTimeManager.convertAccessStringToCalendar(endDateTime);
            if (endCal==null){
                System.out.println("Invalid input! Please enter again!");
                continue;
            }

            if((endCal.before(startCal))||(endCal.equals(startCal)))
                System.out.println("Start access period can't be later than equal to end access period!");
            }while(endCal.before(startCal)||(endCal == null)||(startCal == null)||endCal.equals(startCal));

            
            this.adminManager.addAccessPeriod(startDateTime, endDateTime);
            
            System.out.println("\nSet student access period successfully!\n");
        
    }

    private void updStudAccPeriod() throws IOException {

        int choice = 0;
        String startDateTime = null,endDateTime = null;
        String[] dateTimeSplit;
        Scanner sc = new Scanner(System.in);

        ArrayList <String> accessDateTimeStrList = DateTimeManager.readAccessPeriodFromDB();

        Calendar startCal=null, endCal=null;
        if ((accessDateTimeStrList.get(0) == null) && (accessDateTimeStrList.get(1) == null)) {
            System.out.println("\nThe student access period is empty.");
            System.out.println("\n*****Would you like to set student access period ?*****");
            System.out.println("                Set Student Access Period             ");
            System.out.println("______________________________________________________\n");
            System.out.println("|1. Yes                                              |");
            System.out.println("|2. No                                               |");
            System.out.println("______________________________________________________");


            choice = sc.nextInt();
            

            do {
                while (!sc.hasNextInt()) {
                    System.out.println("Invalid input! Please enter your choice:");
                    sc.next();
                }
                choice = sc.nextInt();
                if((choice < 1) || (choice > 2))
                    System.out.println("Invalid input! Please enter your choice:");
            } while ((choice < 1) || (choice > 2));
            if(choice == 1){
                this.setStudAccPeriod();
                return;
            }
            else
                return;

        }
        
        else if(accessDateTimeStrList.get(0)==null){
            System.out.println("\nThe student start access period is empty.");
            System.out.println("Please update student start access period first.");
            endDateTime = accessDateTimeStrList.get(1);
            endCal = DateTimeManager.convertAccessStringToCalendar(endDateTime);
            choice = 1;
        }
        else if (accessDateTimeStrList.get(1)==null){
            System.out.println("\nThe student end access period is empty.");
            System.out.println("Please update student end access period first.");
            startDateTime = accessDateTimeStrList.get(0);
            startCal = DateTimeManager.convertAccessStringToCalendar(startDateTime);
            choice = 2;
        }
        else{
            startDateTime = accessDateTimeStrList.get(0);
            endDateTime = accessDateTimeStrList.get(1);
            startCal = DateTimeManager.convertAccessStringToCalendar(startDateTime);
            endCal = DateTimeManager.convertAccessStringToCalendar(endDateTime);
            System.out.println("\nThe current student access period is from "+startDateTime+" to "+endDateTime);
            System.out.println("\n*****Which access period would you like to update ?*****");
            System.out.println("              Update Student Access Period             ");
            System.out.println("______________________________________________________\n");
            System.out.println("|1. Update student start access period               |");
            System.out.println("|2. Update student end access period                 |");
            System.out.println("|3. Update both                                      |");
            System.out.println("|----------------------------------------------------|");
            System.out.println("|0. Return to admin menu page                        |");
            System.out.println("|----------------------------------------------------|");
            System.out.println("______________________________________________________");

            do {
                while (!sc.hasNextInt()) {
                    System.out.println("Invalid input! Please enter your choice:");
                    sc.next();
                }
                choice = sc.nextInt();
                if((choice < 0) || (choice > 3))
                    System.out.println("Invalid input! Please enter your choice:");
            } while ((choice < 0) || (choice > 3));
        }
 
        sc.nextLine();
        switch(choice){
            case 1:
                do{
                    System.out.println("\nPlease enter start date and time (yyyy MM dd hh mm):");
                    startDateTime = sc.nextLine();
                    while(!startDateTime.matches("^\\d{4} \\d{2} \\d{2} \\d{2} \\d{2}$"))
                    {
                        System.out.println("Invalid input! Please enter again!");
                        System.out.println("\nPlease enter start date and time (yyyy MM dd hh mm):");
                        startDateTime = sc.nextLine();
                    }
                    dateTimeSplit = startDateTime.split(" ");
                    startDateTime = dateTimeSplit[0]+"-"+dateTimeSplit[1]+"-"+dateTimeSplit[2]+" "+dateTimeSplit[3]+":"+dateTimeSplit[4];

                    startCal = DateTimeManager.convertAccessStringToCalendar(startDateTime);
                    if(startCal  == null){
                        System.out.println("Invalid input! Please enter again!");
                    }
                    if((endCal.before(startCal))||(endCal.equals(startCal)))
                        System.out.println("Start access period can't be later than or equal to end access period!");

                    }while(endCal.before(startCal)||endCal.equals(startCal)||(startCal  == null));
                
                break;
            case 2:
                do{
                    System.out.println("\nPlease enter end date and time (yyyy MM dd hh mm):");
                    endDateTime = sc.nextLine();
                    while(!endDateTime.matches("^\\d{4} \\d{2} \\d{2} \\d{2} \\d{2}$"))
                    {
                        System.out.println("Invalid input! Please enter again!");
                        System.out.println("\nPlease enter end date and time (yyyy MM dd hh mm):");
                        endDateTime = sc.nextLine();
                    }

                    dateTimeSplit = endDateTime.split(" ");
                    endDateTime = dateTimeSplit[0]+"-"+dateTimeSplit[1]+"-"+dateTimeSplit[2]+" "+dateTimeSplit[3]+":"+dateTimeSplit[4];

                    endCal = DateTimeManager.convertAccessStringToCalendar(endDateTime);
                    if(endCal  == null){
                        System.out.println("Invalid input! Please enter again!");
                    }
                    if((endCal.before(startCal))||(endCal.equals(startCal)))
                        System.out.println("Start access period can't be later than or equal to end access period!");
                    }while(endCal.before(startCal)||endCal.equals(startCal)||(endCal  == null));
                
                break;
            case 3:
                this.setStudAccPeriod();
                return;
            default:
                return;
            
        }
        adminManager.addAccessPeriod(startDateTime, endDateTime);
        System.out.println("\nUpdate successfully!\n");

    }
    
    private void addStudentParticulars() throws Exception {
        String gender = "MALE";
        Student student = new Student();

        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter the student's username:");
        String username = sc.nextLine();
        while((!username.matches("^[a-zA-Z0-9]+$"))||(username.length()!=7))
        {
            if (username.length()!=7)
                System.out.println("Input characters only can have 7 characters.");
            else
                System.out.println("Only alphanumeric allowed");
        	System.out.println("\nPlease enter the student's username:");
        	username = sc.nextLine();        	
        }
        username = username.toUpperCase();


        while((this.courseMgr.readStudentbyID(username))!=null){
        //while(adminManager.addStudent(student)==1){
        //while(adminManager.addStudent(student)==0){
            ErrorCodeBoundary.printErrorUsernameAlreadyExist();
            System.out.println("\n***********What would you like to do next ?***********");
            System.out.println("                  Student Information          ");
            System.out.println("______________________________________________________\n");
            System.out.println("|1. Add another student                              |");
            System.out.println("|2. Update information for this student              |");
            System.out.println("|----------------------------------------------------|");
            System.out.println("|0. Return to admin menu page                        |");
            System.out.println("|----------------------------------------------------|");
            System.out.println("______________________________________________________");

            //sc.nextLine();
            int choice;
            do {
                while (!sc.hasNextInt()) {
                    System.out.println("Invalid input! Please enter your choice:");
                    sc.next();
                }
                choice = sc.nextInt();
                if((choice < 0) || (choice > 2))
                    System.out.println("Invalid input! Please enter your choice:");
            } while ((choice < 0) || (choice > 2));
            if(choice == 1){
                System.out.println("\nPlease enter the student's username:");
                username = sc.nextLine();
                while(!username.matches("^[a-zA-Z0-9]+$"))
                {
        	        System.out.println("Invalid input! Please enter again!");
        	        System.out.println("\nPlease enter the student's username:");
        	        username = sc.nextLine();        	
                }
                username = username.toUpperCase();
                student.setName(username);
            }
            else if(choice ==2){
                this.nameExist = username;
                this.updStudentParticulars();
            }
            else
                return;

        }

        student.setUsername(username);
        System.out.println(username);

        System.out.println("\nPlease enter "+username+"'s student name:");
        String name = sc.nextLine();
        while(!name.matches("^[ A-Za-z]+$"))
        {
        	System.out.println("Invalid input! Please enter again!");
        	System.out.println("\nPlease enter the student's name:");
        	name = sc.nextLine();        	
        }
        name = name.toUpperCase();
        student.setName(name);


        System.out.println("Please enter "+username+"'s password:");
        String password = sc.nextLine();
        student.setPassword(password);
        System.out.println(password);


        //matric num
        System.out.println("\nPlease enter "+username+"'s matric num (A1234567B):");
        String matricNum = sc.nextLine();
        while(!matricNum.matches("[a-zA-Z]\\d{7}[a-zA-Z]"))
        {
        	System.out.println("Invalid input! Please enter again!");
        	System.out.println("\nPlease enter "+username+"'s matric num (A1234567B):");
        	matricNum = sc.nextLine();        	
        }
        matricNum = matricNum.toUpperCase();
        student.setMatricNumber(matricNum);

        //gender
        System.out.println("\nPlease enter "+username+"'s gender, M or F?");
        char genderChar = sc.next().charAt(0);		
        while((Character.toUpperCase(genderChar)!='M')&&(Character.toUpperCase(genderChar)!='F'))
        {
        	System.out.println("Invalid input! Please enter again!");
        	System.out.println("\nPlease enter "+username+"'s gender, M or F?");
        	genderChar = sc.next().charAt(0); 
        }
        sc.nextLine();///////////////////////////////////////////////////////////////
        genderChar = Character.toUpperCase(genderChar);
        if(genderChar=='F')
            gender = "FEMALE";

        student.setGender(gender);
            
        //nationality
        System.out.println("\nPlease enter "+username+"'s nationality :");
        String nationality = sc.nextLine();
        while(!nationality.matches("^[a-zA-Z]*$"))
        {
        	System.out.println("Invalid input! Please enter again!");
        	System.out.println("\nPlease enter "+username+"'s nationality: ");
        	nationality = sc.nextLine();        	
        }   	
        nationality = nationality.toUpperCase();

        student.setNationality(nationality);

        //System.out.println(student.getMatricNumber());
        adminManager.addStudent(student);
        System.out.println("\nAdd student successfully!\n");
        //add student information
    }

    private void updStudentParticulars() throws InterruptedException {

    	Student newStudent;
        String gender = "MALE";
        String username=null;

        Scanner sc = new Scanner(System.in);


        System.out.println(this.nameExist);
        if (this.nameExist==null){
            System.out.println("\nPlease enter the student's username:");
            username = sc.nextLine();
            while((!username.matches("^[a-zA-Z0-9]+$"))||(username.length()!=7))
            {
                if (username.length()!=7)
                    System.out.println("Input characters only can have 7 characters.");
                else
                    System.out.println("Only alphanumeric allowed");
                System.out.println("\nPlease enter the student's username:");
                username = sc.nextLine();
            }
            username = username.toUpperCase();
        }
        //???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
        else
            username = this.nameExist;
            newStudent = this.courseMgr.readStudentbyID(username);
            if(newStudent!=null) {


                //newStudent = new Student();
                //updStudentName = name;
                System.out.println("\n*****Which information would you like to update ?*****");
                System.out.println("              Update Student Information             ");
                System.out.println("______________________________________________________\n");
                System.out.println("|1. Password                                         |");
                System.out.println("|2. Name                                             |");
                System.out.println("|3. Matriculation Number                             |");
                System.out.println("|4. Gender                                           |");
                System.out.println("|5. Nationality                                      |");
                System.out.println("|----------------------------------------------------|");
                System.out.println("|0. Return to admin menu page                        |");
                System.out.println("|----------------------------------------------------|");
                System.out.println("______________________________________________________");

                int choice;

                do {
                    do {
                        while (!sc.hasNextInt()) {
                            System.out.println("Invalid input! Please enter your choice:");
                            sc.next();
                        }
                        choice = sc.nextInt();
                        if ((choice < 0) || (choice > 6))
                            System.out.println("Invalid input! Please enter your choice:");
                    } while ((choice < 0) || (choice > 6));
                    switch (choice) {

                        //password
                        case 1: {
                            sc.nextLine();
                            System.out.println("Please enter the student's new password:");
                            String password = sc.nextLine();

                            newStudent.setPassword(password);
                            System.out.println(password);
                            break;
                        }

                        //name
                        case 2: {
                            sc.nextLine();
                            System.out.println("Please enter the student's new name:");
                            String newName = sc.nextLine();
                            while (!newName.matches("^[ A-Za-z]+$")) {
                                System.out.println("Invalid input! Please enter again!");
                                System.out.println("Please enter the student's new name:");
                                newName = sc.nextLine();
                            }
                            newName = newName.toUpperCase();

                            newStudent.setName(newName);
                            System.out.println(newName);
                            break;
                        }

                        //matric number
                        case 3: {
                            sc.nextLine();
                            System.out.println("Please input new matric number:");
                            String matricNum = sc.nextLine();
                            while (!matricNum.matches("[a-zA-Z]\\d{7}[a-zA-Z]")) {
                                System.out.println("Invalid input! Please enter again!");
                                System.out.println("Please input new matric number:");
                                matricNum = sc.nextLine();
                            }
                            matricNum = matricNum.toUpperCase();
                            newStudent.setMatricNumber(matricNum);
                            break;
                        }

                        //gender
                        case 4: {
                            System.out.println("Please enter new gender, M or F?");
                            char genderChar = sc.next().charAt(0);

                            while ((Character.toUpperCase(genderChar) != 'M') && (Character.toUpperCase(genderChar) != 'F')) {
                                System.out.println("Invalid input! Please enter again!");
                                System.out.println("Please enter new gender, M or F?");
                                genderChar = sc.next().charAt(0);

                            }
                            sc.nextLine();
                            genderChar = Character.toUpperCase(genderChar);
                            if (genderChar == 'F')
                                gender = "FEMALE";
                            newStudent.setGender(gender);
                            System.out.println(gender);
                            break;
                        }

                        //nationality
                        case 5: {
                            sc.nextLine();
                            System.out.println("Please enter the new nationality :");
                            String nationality = sc.nextLine();
                            while (!nationality.matches("^[a-zA-Z]*$")) {
                                System.out.println("Invalid input! Please enter again!");
                                System.out.println("Please enter the new nationality: ");
                                nationality = sc.nextLine();
                            }
                            nationality = nationality.toUpperCase();
                            newStudent.setNationality(nationality);

                            System.out.println(nationality);
                            break;
                        }

                        //exit
                        default:
                            System.out.println("Hang on a moment while we direct to admin menu page.\n\n");
                            sc.nextLine();
                            return;
                    }


                    this.adminManager.addStudent(newStudent);

                    System.out.println("\n*****Which information would you like to update ?*****");
                    System.out.println("              Update Student Information             ");
                    System.out.println("______________________________________________________\n");
                    System.out.println("|1. Password                                         |");
                    System.out.println("|2. Name                                             |");
                    System.out.println("|3. Matriculation Number                             |");
                    System.out.println("|4. Gender                                           |");
                    System.out.println("|5. Nationality                                      |");
                    System.out.println("|----------------------------------------------------|");
                    System.out.println("|0. Return to admin menu page                        |");
                    System.out.println("|----------------------------------------------------|");
                    System.out.println("______________________________________________________");
                } while ((choice < 6) && (choice > 0));
                System.out.println("\nHang on a second while we return to admin menu page......\n");
                TimeUnit.SECONDS.sleep(1);
            }

            else{
                ErrorCodeBoundary.printErrorMissingUsername();
                System.out.println("\nHang on a second while we return to admin menu page......\n");
                TimeUnit.SECONDS.sleep(1);
                return;
            }
    }

    private void addCourse() throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        Course course = new Course();

        System.out.println("\nPlease enter the course code:");
        String courseID = sc.nextLine();
        while(!courseID.matches("^[a-zA-Z]{2}[0-9]{4}$"))
        {
        	System.out.println("Invalid input! Please enter again!");
        	System.out.println("\nPlease enter the course code:");
        	courseID = sc.nextLine();        	
        }
        courseID = courseID.toUpperCase();
        //Course course = new Course(courseID,null,-1)

        while(courseMgr.readCourseByID(courseID)!=null){
            ErrorCodeBoundary.printErrorCourseAlreadyExist();
            System.out.println("\n***********What would you like to do next ?***********");
            System.out.println("                Course Information         ");
            System.out.println("______________________________________________________\n");
            System.out.println("|1. Add another course                               |");
            System.out.println("|2. Update information for this course               |");
            System.out.println("|----------------------------------------------------|");
            System.out.println("|0. Return to admin menu page                        |");
            System.out.println("|----------------------------------------------------|");
            System.out.println("______________________________________________________");

            //sc.nextLine();
            int choice;

            do {
                while (!sc.hasNextInt()) {
                    System.out.println("Invalid input! Please enter your choice:");
                    sc.next();
                }
                choice = sc.nextInt();
                if((choice < 0) || (choice > 2))
                    System.out.println("Invalid input! Please enter your choice:");
            } while ((choice < 0) || (choice > 2));
            if(choice == 1){
                System.out.println("\nPlease enter another course code:");
                sc.nextLine();
                courseID = sc.nextLine();
                while(!courseID.matches("^[a-zA-Z]{2}[0-9]{4}$"))
                {
        	        System.out.println("Invalid input! Please enter again!");
        	        System.out.println("\nPlease enter the course code:");
        	        courseID = sc.nextLine();        	
                }
                
            }
            else if(choice ==2){
                this.courseIDExist = courseID;
                this.updCourse();
                return;
            }
            else
                return;

            
        }

        courseID = courseID.toUpperCase();
        course.setCourseID(courseID);


        System.out.println("\nPlease enter the school:");
        String school = sc.nextLine();
        while(!school.matches("^[a-zA-Z]*$"))
        {
        	System.out.println("Invalid input! Please enter again!");
        	System.out.println("\nPlease enter the school:");
        	school = sc.nextLine();        	
        }
        school = school.toUpperCase();
        course.setSchool(school);

        System.out.println("\n**************Please choose a course type**************");
        System.out.println("               Course Type Information         ");
        System.out.println("______________________________________________________\n");
        System.out.println("|1. Lecture only                                     |");
        System.out.println("|2. Lecture with tutorial                            |");
        System.out.println("|3. Lecture tutorial and lab                         |");
        System.out.println("______________________________________________________");


        int courseType;
            do {
                while (!sc.hasNextInt()) {
                    System.out.println("Invalid input! Please enter the course type:");
                    sc.next();
                }
                courseType = sc.nextInt();
                if((courseType < 1) || (courseType > 3))
                    System.out.println("Input number out of range! Please enter the course type:");
            } while ((courseType < 1) || (courseType > 3));

        course.setCourseType(courseType);


        System.out.println("\nHow many indices for this course?");
        int noOfIndex;
        do {
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input! Please enter the number of index for this course:");
                sc.next();
            }
            noOfIndex = sc.nextInt();
            if(noOfIndex < 1)
                System.out.println("The number of index can't be less than 1.Please enter the number of index for this course:");
        } while (noOfIndex < 1);

        ArrayList<String> dateAndTime = new ArrayList<String>();
        CourseIndex courseIndex = new CourseIndex();
        ArrayList<CourseCompo> courseCompoList = new ArrayList<CourseCompo>();

        for(int i=0;i<noOfIndex;i++){
            courseIndex = this.addCourseIndex(courseID);
            System.out.println("print AUAUAUAUA");
            System.out.println(courseIndex.getAu());
            if (courseIndex==null)
                return;
            courseCompoList = this.addCourseComp(courseType);
            for(CourseCompo courseComp:courseCompoList){
                courseIndex.addCourseCompo(courseComp);
            }

            /*System.out.println("\nPlease enter the date and time for lecture:\n");
            dateAndTime = this.dateTimeSelection();
            courseIndex.addCourseCompo(new CourseCompo("LEC",dateAndTime.get(0),dateAndTime.get(1)));
            if(courseType ==2){
                    
                System.out.println("\nPlease enter the date and time for tutorial:\n");
                dateAndTime = this.dateTimeSelection();
                courseIndex.addCourseCompo(new CourseCompo("TUT",dateAndTime.get(0),dateAndTime.get(1)));
            }
            else if (courseType ==3){
                System.out.println("\nPlease enter the date and time for tutorial:\n");
                dateAndTime = this.dateTimeSelection();
                courseIndex.addCourseCompo(new CourseCompo("TUT",dateAndTime.get(0),dateAndTime.get(1)));
    
                System.out.println("\nPlease enter the date and time for lab:\n");
                dateAndTime = this.dateTimeSelection();
                courseIndex.addCourseCompo(new CourseCompo("LAB",dateAndTime.get(0),dateAndTime.get(1)));
            
            //course.addCourseIndex(courseIndex);
            
            //CourseCompo courseComponent = this.addCourseComponent(courseType);
        }*/
        course.addCourseIndex(courseIndex);
        CourseManager courseManager = new CourseManager();
        courseManager.addCourseToDB(course);
        System.out.println("\nThe course have been added successfully.");


    }
}

    private ArrayList<CourseCompo> addCourseComp(int courseType){
        ArrayList<String> dateAndTime = new ArrayList<String>();
        ArrayList<CourseCompo> courseCompoList = new ArrayList<CourseCompo>();
        CourseCompo courseCompo = new CourseCompo(null,null,null);
        System.out.println("\nPlease enter the date and time for lecture:\n");
        dateAndTime = this.dateTimeSelection();
        //courseIndex.addCourseCompo(new CourseCompo("LEC",dateAndTime.get(0),dateAndTime.get(1)));
        courseCompoList.add(new CourseCompo("LEC",dateAndTime.get(0),dateAndTime.get(1)));
        if(courseType ==2){

            System.out.println("\nPlease enter the date and time for tutorial:\n");
            dateAndTime = this.dateTimeSelection();
            courseCompoList.add(new CourseCompo("TUT",dateAndTime.get(0),dateAndTime.get(1)));
        }
        else if (courseType ==3){
            System.out.println("\nPlease enter the date and time for tutorial:\n");
            dateAndTime = this.dateTimeSelection();
            courseCompoList.add(new CourseCompo("TUT",dateAndTime.get(0),dateAndTime.get(1)));

            System.out.println("\nPlease enter the date and time for lab:\n");
            dateAndTime = this.dateTimeSelection();
            courseCompoList.add(new CourseCompo("LAB",dateAndTime.get(0),dateAndTime.get(1)));

            //course.addCourseIndex(courseIndex);

            //CourseCompo courseComponent = this.addCourseComponent(courseType);
        }
        return courseCompoList;
    }

    private CourseIndex addCourseIndex(String courseID) throws IOException {
        Scanner sc = new Scanner(System.in);
        CourseIndex courseIndexObj = new CourseIndex();

        ArrayList<String> dateAndTime = new ArrayList<String>();

        if (courseID==null)
            return null;
        courseIndexObj.setCourseID(courseID);
        //ArrayList<CourseIndex> courseIndices = new ArrayList<CourseIndex>();
        //sc.nextLine();

        System.out.println("\nPlease enter the index number:");
        String indexID = sc.nextLine();
        while((!indexID.matches("^[0-9]+$"))||(indexID.length()<2))
        {
            if (!indexID.matches("^[0-9]+$"))
                System.out.println("Input must be numbers! Please enter again!");
            else if(indexID.length()<2)
                System.out.println("Input can't be less than 1! Please enter again!");
        	System.out.println("\nPlease enter the index number:");
            indexID = sc.nextLine();
        }

        while(courseMgr.readCourseIndexbyID(indexID)!=null){
            ErrorCodeBoundary.printErrorIndexAlreadyExist();
            System.out.println("\n***********What would you like to do next ?***********");
            System.out.println("              Course Index Information         ");
            System.out.println("______________________________________________________\n");
            System.out.println("|1. Add another course index                         |");
            System.out.println("|2. Update course component for this course index    |");
            System.out.println("|----------------------------------------------------|");
            System.out.println("|0. Return to admin menu page                        |");
            System.out.println("|----------------------------------------------------|");
            System.out.println("______________________________________________________");
    
                //sc.nextLine();
                //sc.nextLine();
            int choice;
            do {
                while (!sc.hasNextInt()) {
                    System.out.println("Invalid input! Please enter your choice:");
                    sc.next();
                }
                choice = sc.nextInt();
                if((choice < 0) || (choice > 2))
                    System.out.println("Invalid input! Please enter your choice:");
            } while ((choice < 0) || (choice > 2));

            if(choice == 1){
                System.out.println("\nPlease enter another course index:");
                sc.nextLine();
                indexID = sc.nextLine();
                while(!indexID.matches("^[0-9]+$"))
                {
                    System.out.println("Invalid input! Please enter again!");
                    System.out.println("\nPlease enter the course index:");
                    indexID = sc.nextLine();
                }
            }
            else if(choice ==2){
                this.courseIndexExist = indexID;
                    //sc.nextLine();
                this.updCourseIndex(courseID);
                return null;
            }
            else
                return null;

        }
        courseIndexObj.setIndex(indexID);

        System.out.println("How many slots are there for this course index?");
        int slot;
        do {
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input! Please enter the number of slot for this course index:");
                sc.next();
            }
            slot = sc.nextInt();
            if(slot < 1)
                System.out.println("Invalid input! Please enter a number larger than 1:");
        } while(slot < 1);

        courseIndexObj.setSlot(slot);

        int courseAU;
        System.out.println("How many AUs are there for this course index?");
        do {
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input! Please enter the AU for this course:");
                sc.next();
            }
            courseAU = sc.nextInt();
            if(courseAU < 1)
                System.out.println("Course AU can't be less than 1! Please enter the AU for this course:");
        } while(courseAU < 1);
        
        courseIndexObj.setAu(courseAU);

        return courseIndexObj;
    }

    
    private void updCourse() throws InterruptedException, IOException {
        Scanner sc = new Scanner(System.in);
        String courseID = null;
        Course course;
        
        if (this.courseIDExist==null){
            System.out.println("\nPlease enter the course code to update:");
            courseID = sc.nextLine();
            while(!courseID.matches("^[a-zA-Z]{2}[0-9]{4}$"))
            {
        	    System.out.println("Invalid input! Please enter again!");
        	    System.out.println("\nPlease enter the course code to update:");
        	    courseID = sc.nextLine();        	
            }
            courseID = courseID.toUpperCase();
        }
        //???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
        else
        courseID = this.courseIDExist;
        course = this.courseMgr.readCourseByID(courseID);
        if (course!=null){
            //course = new Course();
            //updStudentName = name;
            System.out.println("\n*****Which information would you like to update ?*****");
            System.out.println("              Update Course Information             ");
            System.out.println("______________________________________________________\n");
            System.out.println("|1. School                                           |");
            System.out.println("|2. Course Index Information                         |");
            System.out.println("|----------------------------------------------------|");
            System.out.println("|0. Return to admin menu page                        |");
            System.out.println("|----------------------------------------------------|");
            System.out.println("______________________________________________________");

            int choice;

            do{
                do {
                    while (!sc.hasNextInt()) {
                        System.out.println("Invalid input! Please enter your choice:");
                        sc.next();
                    }
                    choice = sc.nextInt();
                    if ((choice < 0) || (choice > 2))
                        System.out.println("Invalid input! Please enter your choice:");
                } while ((choice < 0) || (choice > 2));

                switch(choice){
                    case 1:
                        sc.nextLine();
                        //System.out.println("Please enter the course new school:");
                        System.out.println("Please enter the course new school:");
                        String school = sc.nextLine();
                        course.setSchool(school);
                        adminManager.updateCourse(course);
                        System.out.println("\nThe course information has updated successfully!");
                        break;
                    case 2:
                        sc.nextLine();
                        //System.out.println("Please enter the course new index:");
                        //String courseIndex = sc.nextLine();
                        this.updCourseIndex(courseID);
                        System.out.println("\nThe course information has updated successfully!");
                        //course.setSchool(school);
                        break;
                    default:
                        System.out.println("\nHang on a second while we return to admin menu page......\n");
                        TimeUnit.SECONDS.sleep(1);
                        return;
                }

                System.out.println("\n*****Which information would you like to update ?*****");
                System.out.println("              Update Course Information             ");
                System.out.println("______________________________________________________\n");
                System.out.println("|1. School                                           |");
                System.out.println("|2. Course Index Information                         |");
                System.out.println("|----------------------------------------------------|");
                System.out.println("|0. Return to admin menu page                        |");
                System.out.println("|----------------------------------------------------|");
                System.out.println("______________________________________________________");
            }while ((choice > -1) || (choice <3));
        }

    }

    private void updCourseIndex(String courseID) throws IOException {
        CourseIndex newCourseIndex = new CourseIndex();
        String indexID = null;
        ArrayList<String> dateAndTime = new ArrayList<String>();
        Course course = new Course();
        ArrayList<CourseCompo> courseCompoList = new ArrayList<CourseCompo>();
        CourseIndex courseIndex = new CourseIndex();
        Scanner sc = new Scanner(System.in);
        //if(this.courseIndexExist==null){
            //this.addCourseIndex(courseID);
            /*System.out.println("\nPlease enter the course index:");
            indexID = sc.nextLine();
            while((!indexID.matches("^[0-9]+$"))||(indexID.length()<2)){
                if (!indexID.matches("^[0-9]+$"))
                    System.out.println("Input must be numbers! Please enter again!");
                else if(indexID.length()<2)
                    System.out.println("Input can't be less than 1! Please enter again!");
                System.out.println("\nPlease enter the index number:");
                indexID = sc.nextLine();
            }*/
        //}
        if(this.courseIndexExist!=null) {
            indexID = this.courseIndexExist;
            while (this.courseMgr.readCourseIndexbyID(indexID) != null) {
                ErrorCodeBoundary.printErrorIndexAlreadyExist();
                System.out.println("\n************What would you like to update?************");
                System.out.println("            Update Course Index Information         ");
                System.out.println("______________________________________________________\n");
                System.out.println("|1. Update to a new course index                     |");
                System.out.println("|2. Update course component for this course index    |");
                System.out.println("|----------------------------------------------------|");
                System.out.println("|0. Return to admin menu page                        |");
                System.out.println("|----------------------------------------------------|");
                System.out.println("______________________________________________________");

                int choice;
                do {
                    do {
                        while (!sc.hasNextInt()) {
                            System.out.println("Invalid input! Please enter your choice:");
                            sc.next();
                        }
                        choice = sc.nextInt();
                        if ((choice < 0) || (choice > 2))
                            System.out.println("Invalid input! Please enter your choice:");
                    } while ((choice < 0) || (choice > 2));

                    if (choice == 1) {
                        sc.nextLine();
                        System.out.println("\nPlease input the new course index:");
                        String newIndex = sc.nextLine();
                        if (courseMgr.readCourseIndexbyID(newIndex) != null) {
                            ErrorCodeBoundary.printErrorIndexAlreadyExist();
                            //indexID = newIndex;

                            choice = -1;
                        } else {
                            //change course index number
                            //get current course index, course component
                            courseIndex = this.courseMgr.readCourseIndexbyID(indexID);
                            courseIndex.setIndex(newIndex);
                            this.adminManager.updateCourseIndex(courseIndex);
                        }
                    } else if (choice == 2) {
                        int courseType = this.courseMgr.getCourseTypebyCourseID(courseID);
                        if (courseType == -1) {
                            ErrorCodeBoundary.printErrorMissingCourse();
                            return;
                        } else {

                            courseIndex = courseMgr.readCourseIndexbyID(indexID);
                            if (courseIndex != null) {

                                dateAndTime = this.dateTimeSelection();
                                ArrayList<CourseCompo> courseCompos = courseIndex.getCourseCompos();
                                for (CourseCompo courseCompo : courseCompos) {
                                    courseIndex.removeCourseCompo(courseCompo);
                                }

                                courseIndex.addCourseCompo(new CourseCompo("LEC", dateAndTime.get(0), dateAndTime.get(1)));
                                if (courseType == 2) {

                                    System.out.println("\nPlease enter the date and time for tutorial:\n");
                                    dateAndTime = this.dateTimeSelection();
                                    courseIndex.addCourseCompo(new CourseCompo("TUT", dateAndTime.get(0), dateAndTime.get(1)));
                                } else if (courseType == 3) {
                                    System.out.println("\nPlease enter the date and time for tutorial:\n");
                                    dateAndTime = this.dateTimeSelection();
                                    courseIndex.addCourseCompo(new CourseCompo("TUT", dateAndTime.get(0), dateAndTime.get(1)));

                                    System.out.println("\nPlease enter the date and time for lab:\n");
                                    dateAndTime = this.dateTimeSelection();
                                    courseIndex.addCourseCompo(new CourseCompo("LAB", dateAndTime.get(0), dateAndTime.get(1)));

                                    //course.addCourseIndex(courseIndex);

                                    //CourseCompo courseComponent = this.addCourseComponent(courseType);
                                } else
                                    return;

                                this.adminManager.updateCourseIndex(courseIndex);
                                //courseMgr.updateCourseIndexInCourseIndexAndCourseCompoDB(courseIndex);
                            }


                        }

                    }
                } while ((choice > 0) && (choice < 3));

                return;
            }
        }
        else
            courseIndex = this.addCourseIndex(courseID);
            course = this.courseMgr.readCourseByID(courseID);
            courseCompoList = this.addCourseComp(course.getCourseType());
            //courseIndex.setCourseID(courseID);
            for(CourseCompo courseComp:courseCompoList)
                courseIndex.addCourseCompo(courseComp);
            //..System.out.println(courseIndex.getCourseCompos().get[0]);
                this.adminManager.addCourseIndex(courseIndex);

            course.addCourseIndex(courseIndex);
            this.adminManager.updateCourse(course);
        //CourseManager courseManager = new CourseManager();
        //courseManager.addCourseToDB(course);
        System.out.println("\nThe course index have been updated successfully.");

    }

    private void checkVacancy(){

    }

    private ArrayList<String> dateTimeSelection(){
        int choice;
        String weekDay=null,startTime=null,endTime=null;
        Calendar startCal=null, endCal = null;
        String[] dateTimeSplit;
        ArrayList<String> dateAndTime = new ArrayList<String>();
        Scanner sc = new Scanner(System.in);

        System.out.println("*****************Please choose a date*****************");
        System.out.println("                        Weekdays         ");
        System.out.println("______________________________________________________\n");
        System.out.println("|1. Monday            ||2.Tuesday                    |");
        System.out.println("|3. Wednesday         ||4.Thursday                   |");
        System.out.println("|5. Friday            ||                             |");
        System.out.println("______________________________________________________");



        do {
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input! Please enter your choice:");
                sc.next();
            }
            choice = sc.nextInt();
            if((choice < 1) || (choice > 5))
                System.out.println("Invalid input! Please enter your choice:");
        } while ((choice < 1) || (choice > 5));

        switch(choice){
            case 1:
                weekDay = "MON";
                break;
            case 2:
                weekDay = "TUE";
                break;
            case 3:
                weekDay = "WED";
                break;
            case 4:
                weekDay = "THU";
                break;
            case 5:
                weekDay = "FRI";
                break;
        }

        dateAndTime.add(weekDay);
        
        sc.nextLine();
        do {
            
            System.out.println("\nPlease input start time (HH mm)");
            startTime = sc.nextLine();
            while(!startTime.matches("^\\d{2} \\d{2}$"))
            {
        	    System.out.println("\nInvalid input! Please enter start time again!");
        	    System.out.println("\nPlease enter start time (HH mm):");
                startTime = sc.nextLine();
            }
            dateTimeSplit = startTime.split(" ");
            startTime = dateTimeSplit[0]+":"+dateTimeSplit[1];
            //System.out.println("////////"+startDateTime);
            startCal = DateTimeManager.convertCourseCompoStrToCalendar(weekDay + " " + startTime);
            // System.out.println("////////"+startCal);
            if (startCal == null) {
                System.out.println("\nInvalid input! Please enter start time again!");
                continue;
            }

            System.out.println("\nPlease enter end time (HH mm):");
            endTime = sc.nextLine();
            while (!endTime.matches("^\\d{2} \\d{2}$")) {
                System.out.println("Invalid input! Please enter end time again!");
                System.out.println("\nPlease enter end time (HH mm):");
                endTime = sc.nextLine();
            }
            dateTimeSplit = endTime.split(" ");
            endTime = dateTimeSplit[0] + ":" + dateTimeSplit[1];
            endCal = DateTimeManager.convertCourseCompoStrToCalendar(weekDay + " " + endTime);
            if (endCal==null){
                System.out.println("Invalid input! Please enter end time again!!");
                //continue;
            }

            else if((endCal.before(startCal))||(endCal.equals(startCal)))
                System.out.println("Start time can't be later than equal to end time!");
            }while((endCal == null)||(startCal == null)||endCal.equals(startCal)||endCal.before(startCal));
        
        dateAndTime.add(startTime+"-"+endTime);
        
        return dateAndTime;

    }

    private void printStudListByIndex() throws Exception {
        String courseIndex = null;
        ArrayList studentUserNameList = new ArrayList();
        
        Scanner sc = new Scanner(System.in);

        System.out.println("\nPlease enter course index");
        courseIndex = sc.nextLine();
        while(!courseIndex.matches("^[0-9]+")){
            System.out.println("Invalid input! Please enter a valid index number!");
            courseIndex = sc.nextLine();
        }

        studentUserNameList= adminManager.getStudentByIndex(courseIndex);
        if (studentUserNameList.isEmpty()){
            System.out.println("\nThere is no student registered for this course index.\n\n");
            return;
        }
        else{
            System.out.println("Hang on a moment while we load database.\n\n");
            System.out.println("------------------------------------------------------");
            System.out.println("STUDENT NAME            GENDER             NATIONALITY");
            System.out.println("------------------------------------------------------");
            for(Object student:studentUserNameList){
                System.out.println(student);
            }
            //
            //System.out.println(String.format("%20s            %s             %s",name,gender,nationality ));
            //
            //print
            //print
            System.out.println("------------------------------------------------------");
        }        
    }

    private void printStudListByCourse() throws Exception {
        String courseID = null;
        
        Scanner sc = new Scanner(System.in);

        System.out.println("\nPlease enter course index");
        courseID = sc.nextLine();
        while(!courseID.matches("^[a-zA-Z]{2}[0-9]{4}$")){
            System.out.println("Invalid input! Please enter a valid index number!");
            courseID = sc.nextLine();
        }

        ArrayList studentUserNameList= adminManager.getStudentByCourse(courseID);
        if (studentUserNameList.isEmpty()){
            System.out.println("\nThere is no student registered for this course ID\n\n");
            return;
        }
        else{
            System.out.println("Hang on a moment while we load database.\n\n");
            System.out.println("------------------------------------------------------");
            System.out.println("STUDENT NAME            GENDER             NATIONALITY");
            System.out.println("------------------------------------------------------");
            //
            //System.out.println(String.format("%20s            %s             %s",name,gender,nationality ));
            //
            //print
            //print
            System.out.println("------------------------------------------------------");
        }  
    }


}
