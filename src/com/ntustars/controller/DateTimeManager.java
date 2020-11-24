package com.ntustars.controller;

import com.ntustars.entity.Student;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
/**
 To handle the string and date time conversion
 @author WANG RUIZHI
 @version 1.0
 @since 2020-11-10
 */
public class DateTimeManager {
    /**
    load DB
     */
    private static ArrayList loadAdminInformationDB(){
        ArrayList stringArray = (ArrayList)TextReaderWriter.readtxt("adminInformation.txt");
        return  stringArray;
    }
    /**
     load DB
     */
    public static ArrayList readAccessPeriodFromDB(){
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
    /**
     To convert string to calendar object
     @param dateTimeString
     @return calendar
     */
    public static Calendar convertAccessStringToCalendar(String dateTimeString){
        Calendar cal = Calendar.getInstance();
        Date tempDate = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try{
            tempDate = dateFormat.parse(dateTimeString);
        }catch (ParseException e){
            e.printStackTrace();
        }
        cal.setTime(tempDate);
        return cal;
    }
    /**
     To convert string to calendar object
     @param courseCompoTime
     @return calendar
     */
    public static Calendar convertCourseCompoStrToCalendar(String courseCompoTime) {
        Calendar cal = Calendar.getInstance();
        Date tempDate = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE HH:mm");
        try {
            tempDate = dateFormat.parse(courseCompoTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.setTime(tempDate);
        return cal;
    }
    /**
    read the datebase to check whether the current time is accessible
     */
    public static boolean isAccessible(){
        ArrayList <String> accessDateTimeStrList = readAccessPeriodFromDB();
        Calendar starting = convertAccessStringToCalendar(accessDateTimeStrList.get(0));
        Calendar ending = convertAccessStringToCalendar(accessDateTimeStrList.get(1));
        Date now = new Date();
        Calendar currentCal = Calendar.getInstance();
        currentCal.setTime(now);
        return  (starting.getTimeInMillis()<=currentCal.getTimeInMillis()) && (currentCal.getTimeInMillis()<= ending.getTimeInMillis());
    }
    /**
     check whether the time is collision
     */
    public static boolean isTimeCollision(Calendar firstStartingTime,Calendar firstEndingTime,Calendar secondStartingTime,Calendar secondEndingTime ){
        int firstCompareResult =  firstEndingTime.compareTo(secondEndingTime);
        int secondCompareResult =  firstEndingTime.compareTo(secondStartingTime);
        int thirdCompareResult =  firstStartingTime.compareTo(secondEndingTime);
        int forthCompareResult =  firstStartingTime.compareTo(secondStartingTime);
        boolean tempResult1 = !(firstCompareResult == secondCompareResult);
        boolean tempResult2 = !(thirdCompareResult == forthCompareResult);
        return tempResult1 || tempResult2;
    }

}
