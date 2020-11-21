package com.ntustars.controller;

import com.ntustars.entity.Course;
import com.ntustars.entity.CourseCompo;
import com.ntustars.entity.CourseIndex;
import com.ntustars.entity.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;

public class AdminManager {

    private static ArrayList loadAdminInformationDB() throws IOException {
        ArrayList stringArray = (ArrayList)TextReaderWriter.readtxt("adminInformation.txt");
        return  stringArray;
    }
    private CourseManager courseManager = new CourseManager();

    public int addCourseIndex(CourseIndex courseIndex) throws IOException{
        int code = 0;
        code += courseManager.addCourseIndexInCourseAndCourseIndexDB(courseIndex);
        code += courseManager.addCourseIndexInfoCompoDB(courseIndex);
        code += courseManager.addCourseIndexInCourseIndexAndCourseCompoDB(courseIndex);
        return code;
    }

    public int addCourse(Course course) throws IOException {
        return courseManager.addCourseToDB(course);
    }
    public int updateCourseIndex(CourseIndex courseIndex) throws IOException{
        int code = 0;
        code += courseManager.updateCourseIndexInfoCompoDB(courseIndex);
        code += courseManager.updateCourseIndexInCourseIndexAndCourseCompoDB(courseIndex);
        return code;
    }
    public int updateCourse(Course course) throws IOException{
        return courseManager.updateCoursetoDB(course);
    }
    public int addAccessPeriod(String startingDay, String endingDay) throws IOException{
        ArrayList adminInformation = loadAdminInformationDB();
        String st = (String) adminInformation.get(0);
        StringTokenizer star = new StringTokenizer(st, ",");
        String adminUserName = star.nextToken().trim();
        String adminPassowrd = star.nextToken().trim();
        StringBuilder builder = new StringBuilder();
        builder.append(adminUserName);
        builder.append(",");
        builder.append(adminPassowrd);
        builder.append(",");
        builder.append(startingDay);
        builder.append(",");
        builder.append(endingDay);
        adminInformation.set(0,builder.toString());
        TextReaderWriter.writetxt("adminInformation",adminInformation);
        return 0;
    }
    public ArrayList getStudentByIndex(String index) throws IOException{
        CourseIndex courseIndex = courseManager.readCourseIndexbyID(index);
        ArrayList<String> studentUserNameList = new ArrayList<>();
        for(Student student : courseIndex.getStudentList()){
            studentUserNameList.add(student.getUsername());
        }
        return studentUserNameList;
    }
    public ArrayList getStudentByCourse(String courseID) throws IOException{
        Course course = courseManager.readCourseByID(courseID);
        ArrayList<String> studentUserNameList = new ArrayList<>();
        for(CourseIndex courseIndex : course.getCourseIndices()){
            studentUserNameList.addAll(getStudentByIndex(courseIndex.getIndex()));
        }
        return studentUserNameList;
    }

}

