package com.ntustars.entity;

import java.util.ArrayList;
/**
 Represents a student enrolled in the school.
 A student can be enrolled in many coursesIndex.
 @author WANG RUIZHI
 @version 1.0
 @since 2020-11-10
 */
public class Student extends Person
{
    /**
     * The name of the Student.
     */
    private String name;
    /**
     * The matricNumber of the Student.
     */
    private String matricNumber;
    /**
     * The gender of the Student.
     */
    private String gender;
    /**
     * The nationality of the Student.
     */
    private String nationality;
    /**
     * The number of AUs taken of the Student.
     */
    private int auTaken;
    /**
     * The course index student registered of AUs taken of the Student.
     */
    private ArrayList<String> courseIndexList = new ArrayList<String>();
    /**
     * The default constructor.
     */
    public Student()
    {

    }
    /**
     * Creates a new student with all the infomation
     * @param username This student's username.
     * @param password This student's password.
     * @param name This student's name
     * @param matricNumber This student's matricNumber
     * @param gender This student's gender
     * @param nationality This student's nationality
     */
    public Student(String username, String password, String name, String matricNumber, String gender, String nationality)
    {
        super(username, password);
        this.name = name;
        this.matricNumber = matricNumber;
        this.gender = gender;
        this.nationality = nationality;
    }
    /**
     * Add student to a course index
     * @param courseIndex the courseindex student registed
     */
    public void addCourseIndex(String courseIndex)
    {
        courseIndexList.add(courseIndex);
    }
    /**
     * drop student from course index
     * @param courseIndex the courseindex student registed
     */
    public void dropCourseIndex(String courseIndex)
    {
        courseIndexList.remove(courseIndex);
    }

    /**
     * get courseIndexList
     * @return this courseIndexList.
     */
    public ArrayList<String> getCourseIndexList() {
        return courseIndexList;
    }

    /**
     * get student name
     * @return this student's name.
     */
    public String getName() {
        return name;
    }
    /**
     * Set student name
     * @param name this Student's name.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * get student matricNumber
     * @return this student's matricNumber.
     */
    public String getMatricNumber() {
        return matricNumber;
    }
    /**
     * Set student matricNumber
     * @param matricNumber This Student's matricNumber.
     */
    public void setMatricNumber(String matricNumber) {
        this.matricNumber = matricNumber;
    }
    /**
     * get student gender
     * @return this student gender
     */
    public String getGender() {
        return gender;
    }
    /**
     * Set student gender
     * @param gender This Student's gender.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }
    /**
     * get student nationality
     * @return this student nationality.
     */
    public String getNationality() {
        return nationality;
    }
    /**
     * Set student nationality
     * @param nationality This Student's nationality.
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    /**
     * get student auTaken
     * @return this student auTaken
     */
    public int getAuTaken() {
        return auTaken;
    }

    /**
     * Set student auTaken
     * @param auTaken This Student's auTaken.
     */
    public void setAuTaken(int auTaken) {
        this.auTaken = auTaken;
    }

}