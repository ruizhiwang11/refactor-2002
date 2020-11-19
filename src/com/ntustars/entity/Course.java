package com.ntustars.entity;

import java.util.ArrayList;

public class Course {
    private String courseID = "";
    private ArrayList<CourseIndex> courseIndices;
    private int courseType;
    private String school;

    public Course(){

    }
    public Course(String courseID,String school, int courseType)
    {
        this.courseID = courseID;
        this.courseType = courseType;
        this.school = school;
        courseIndices = new ArrayList<CourseIndex>();
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public int getCourseType() {
        return courseType;
    }

    public void setCourseType(int courseType) {
        this.courseType = courseType;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public ArrayList<CourseIndex> getCourseIndices() {
        return courseIndices;
    }

    public void addCourseIndex(CourseIndex courseIndex)
    {
        courseIndices.add(courseIndex);
    }
    public void removeCourseIndex(CourseIndex courseIndex)
    {
        courseIndices.remove(courseIndex);
    }
}
