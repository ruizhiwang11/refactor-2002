package com.ntustars.controller;


import com.ntustars.entity.Student;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

public class DateTimeManager {



    private static ArrayList loadAdminInformationDB() throws IOException {
        ArrayList stringArray = (ArrayList)TextReaderWriter.readtxt("adminInformation.txt");
        return  stringArray;
    }

    private static ArrayList readAccessperiodFromDB() throws IOException{
        ArrayList adminInformation = loadAdminInformationDB();
        ArrayList <String> accessDateTimeStrList = new ArrayList<>();
        for(int i = 0; i< adminInformation.size(); i++){
            String st = (String) adminInformation.get(i);
                StringTokenizer star = new StringTokenizer(st, ",");
                star.nextToken().trim();
                star.nextToken().trim();
                accessDateTimeStrList.add(star.nextToken().trim());
                accessDateTimeStrList.add(star.nextToken().trim());
            }
        return  accessDateTimeStrList;
    }
    public static Calendar convertAccessStringToCalendar(String dateTimeString){
        Calendar cal = Calendar.getInstance();
        Date tempDate = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try{
            tempDate = dateFormat.parse(dateTimeString);
        }catch (ParseException e){
            e.printStackTrace();
        }
        cal.setTime(tempDate);
        return cal;
    }
    private static Calendar convertCourseCompoToCalendar(String courseCompoTime){
        Calendar cal = Calendar.getInstance();
        Date tempDate = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE hh:mm");
        try{
            tempDate = dateFormat.parse(courseCompoTime);
        }catch (ParseException e){
            e.printStackTrace();
        }
        cal.setTime(tempDate);
        return cal;
    }
    public static boolean isAccessible() throws IOException{
        ArrayList <String> accessDateTimeStrList = readAccessperiodFromDB();
        Calendar starting = convertAccessStringToCalendar(accessDateTimeStrList.get(0));
        Calendar ending = convertAccessStringToCalendar(accessDateTimeStrList.get(1));
        Date now = new Date();
        Calendar currentCal = Calendar.getInstance();
        currentCal.setTime(now);
        return  (starting.getTimeInMillis()<=currentCal.getTimeInMillis()) && (currentCal.getTimeInMillis()<= ending.getTimeInMillis());
    }

    public static void main(String[] args) throws IOException {
        System.out.println(isAccessible());
    }
}
