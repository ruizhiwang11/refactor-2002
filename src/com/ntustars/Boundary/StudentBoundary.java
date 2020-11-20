package com.ntustars.Boundary;

import java.util.Scanner;

public class StudentBoundary {

    public StudentBoundary() {

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

    public int getSelection() {
        Scanner sc = new Scanner(System.in);
        String i = "";
        int select = -1;

        do {
            printStudentFunction();
            i = sc.next();
            try {
                if (Integer.parseInt(i) >= 0 && Integer.parseInt(i) <= 6)
                    select = Integer.parseInt(i);
                else {
                    System.out.println("Invalid selection! \nPlease key in a number from 0 to 6");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid selection! \nPlease key in a number from 0 to 6");
            }
        }
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
