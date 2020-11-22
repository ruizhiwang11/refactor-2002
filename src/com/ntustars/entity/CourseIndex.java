package com.ntustars.entity;

import java.util.ArrayList;
/**
 Represents the index for student to register
 @author WANG RUIZHI
 @version 1.0
 @since 2020-11-10
 */
public class CourseIndex {
    /**
     * The CourseID.
     */
    private String courseID;
    /**
     * The index number.
     */
    private String index;
    /**
     * The slot left of current course index
     */
    private int slot;
    /**
     * The au occupied of this index
     */
    private int au;
    /**
     * The student registered in this courseIndex
     */
    private ArrayList<String> studentUserNameList = new ArrayList();

    /**
     * The default constructor.
     */
    private ArrayList<CourseCompo> courseCompos;

    public CourseIndex() {
        courseCompos = new ArrayList<CourseCompo>();
    }
    /**
     * Creates a new CourseIndex with all the infomation
     * @param courseID The course ID.
     * @param index The index number.
     * @param slot The slot left of current course index
     * @param au The slot left of current course index
     */
    public CourseIndex(String courseID, String index, int slot, int au)
    {
        this.courseID = courseID;
        this.index = index;
        this.slot = slot;
        this.au = au;
        courseCompos = new ArrayList<CourseCompo>();
    }
    /**
     * Get the course index
     */
    public String getIndex() {
        return index;
    }
    /**
     * Set index
     * @param index course index.
     */
    public void setIndex(String index) {
        this.index = index;
    }
    /**
     * Get slot left
     */
    public int getSlot() {
        return slot;
    }
    /**
     * Set slot
     * @param slot course index slot.
     */
    public void setSlot(int slot) {
        this.slot = slot;
    }
    /**
     * Get au
     */
    public int getAu() {
        return au;
    }
    /**
     * set au
     * @param au setting au
     */
    public void setAu(int au) {
        this.au = au;
    }

    public String getCourseID() {
        return courseID;
    }

    public ArrayList<String> getStudentList() {
        return studentUserNameList;
    }

    public ArrayList<CourseCompo> getCourseCompos() {
        return courseCompos;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    /**
     * add student into the course index
     * @param student student
     */
    public void addStudent(String student) {
        studentUserNameList.add(student);
    }
    /**
     * drop a student from the course index
     * @param student student
     */
    public void dropStudent(String student){
        studentUserNameList.add(student);
    }
    public void addCourseCompo(CourseCompo courseCompo)
    {
        courseCompos.add(courseCompo);
    }
    public void removeCourseCompo(CourseCompo courseCompo)
    {
        courseCompos.remove(courseCompo);
    }
}
