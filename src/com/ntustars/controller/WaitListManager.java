package com.ntustars.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class WaitListManager {
    private static ArrayList loadDBWaitList(){
        ArrayList stringArray = (ArrayList)TextReaderWriter.readtxt("waitList.txt");
        return  stringArray;
    }
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
    public static void main(String[] args) {
        HashMap<String,ArrayList<String>> waitListMap = WaitListManager.readWaitList();
        WaitListManager.updateWaitList("13333","PUPU123");
        WaitListManager.updateWaitList("14444","ZUZU123");
        String str = WaitListManager.popWaitList("13333");
        String str1 = WaitListManager.popWaitList("13333");
        waitListMap = WaitListManager.readWaitList();
    }
}
