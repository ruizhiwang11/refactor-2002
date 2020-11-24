package com.ntustars.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
/**
 To handle the string and date time conversion
 @author WANG RUIZHI
 @version 1.0
 @since 2020-11-10
 */

public class WaitListManager {
    /**
     load WaitList database text file
     @return stringArray
     */
    private static ArrayList loadDBWaitList(){
        ArrayList stringArray = (ArrayList)TextReaderWriter.readtxt("waitList.txt");
        return  stringArray;
    }
    /**
     read WaitList from data base return as HashMap
     @return Wait List
     */
    public static HashMap<String,ArrayList<String>> readWaitList(){
        ArrayList waitList = loadDBWaitList();
        HashMap<String,ArrayList<String>> waitListMap = new HashMap<>();
        if(waitList.isEmpty()){
            return null;
        }
        for(int i = 0; i< waitList.size(); i++){
            String st = (String) waitList.get(i);
            StringTokenizer star = new StringTokenizer(st, ",");
            String courseIndexStr = star.nextToken();
            ArrayList<String> tmpStudentArrayList = new ArrayList<>();
            while(star.hasMoreTokens()){
                tmpStudentArrayList.add(star.nextToken().trim());
            }
            waitListMap.put(courseIndexStr,tmpStudentArrayList);
        }
        return waitListMap;
    }

    /**
     update courseIndex studentID to WaitList
     @param courseIndexStr
     @param studentID
     */
    public static void updateWaitList(String courseIndexStr, String studentID){
        ArrayList waitList = loadDBWaitList();
        for(int i = 0; i< waitList.size(); i++){
            String st = (String) waitList.get(i);
            if(st.contains(courseIndexStr)){
                if(st.contains(studentID)){
                    System.out.println("This student is already in the list");
                    return;
                }
                st+=(studentID+",");
                waitList.set(i,st);
                TextReaderWriter.writetxt("waitList.txt",waitList);
                return;
            }
        }
        StringBuilder builder = new StringBuilder();
        builder.append(courseIndexStr);
        builder.append(",");
        builder.append(studentID);
        builder.append(",");
        waitList.add(builder.toString());
        TextReaderWriter.writetxt("waitList.txt",waitList);
        return;
    }

    /**
     pop student information from WaitList with given courseIndex
     @param courseIndexStr
     @return studentStr
     */
    public static String popWaitList(String courseIndexStr){
        ArrayList waitList = loadDBWaitList();
        for(int i = 0; i< waitList.size(); i++){
            String st = (String) waitList.get(i);
            if(st.contains(courseIndexStr)){
                StringTokenizer star = new StringTokenizer(st, ",");
                star.nextToken().trim();
                String studentStr = star.nextToken().trim();
                if(!star.hasMoreTokens()){
                    waitList.remove(i);
                    TextReaderWriter.writetxt("waitList.txt",waitList);
                    return studentStr;
                }
                else{
                    StringBuilder builder = new StringBuilder();
                    builder.append(courseIndexStr);
                    builder.append(",");
                    while (star.hasMoreTokens()){
                        builder.append(star.nextToken().trim());
                        builder.append(",");
                    }
                    waitList.set(i,builder.toString());
                    TextReaderWriter.writetxt("waitList.txt",waitList);
                    return studentStr;
                }
            }
        }
        return null;

    }

}
