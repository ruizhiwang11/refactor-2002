package com.ntustars.entity;

import java.util.ArrayList;
/**
 Represents the course in the school, can be different course name, index
 
 @author WANG RUIZHI
 @version 1.0
 @since 2020-11-10
 */

public class Course {
    /**
     * The courseID.
     */
    private String courseID = "";
    /**
     * The courseIndices.
     */
    private ArrayList<CourseIndex> courseIndices;
    /**
     * The courseType.
     */
    private int courseType;
    /**
     * The school.
     */
    private String school;
    /**
     * The default course constructor.
     */
    public Course(){
        courseIndices = new ArrayList<CourseIndex>();
    }
    /**
     * Creates a new Course with all the infomation
     * @param courseID The course ID of one course.
     * @param courseType The course Type of the course, can be LEC only,TUT and LEC only or lab,TUT and LEC .
     * @param school school which the course belongs to..
     */
    public Course(String courseID,String school, int courseType)
    {
        this.courseID = courseID;
        this.school = school;
        this.courseType = courseType;
        courseIndices = new ArrayList<CourseIndex>();
    }

    /**
     * get CourseID
     * @return courseID
     */
    public String getCourseID() {
        return courseID;
    }
    /**
     * get CourseID
     * @return the courseID in the class
     */
    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }
    /**
     * get Course Type
     * @return courseType
     */
    public int getCourseType() {
        return courseType;
    }
    /**
     * set CourseType
     * @param  courseType The course type of the course.
     */
    public void setCourseType(int courseType) {
        this.courseType = courseType;
    }
    /**
     * get school
     * @return school
     */
    public String getSchool() {
        return school;
    }
    /**
     * set school
     * @param  school
     */
    public void setSchool(String school) {
        this.school = school;
    }
    /**
     * get CourseIndices
     * @return courseIndices
     */
    public ArrayList<CourseIndex> getCourseIndices() {
        return courseIndices;
    }
    /**
     * Add CourseIndex
     * @param courseIndex add course index to course
     */
    public void addCourseIndex(CourseIndex courseIndex)
    {
        courseIndices.add(courseIndex);
    }
    /**
     * Remove courseIndex
     * @param courseIndex remove course index
     */
    public void removeCourseIndex(CourseIndex courseIndex) { courseIndices.remove(courseIndex); }
}
