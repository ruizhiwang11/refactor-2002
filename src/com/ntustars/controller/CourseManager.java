package com.ntustars.controller;
import com.ntustars.Boundary.ErrorCodeBoundary;
import com.ntustars.entity.Course;
import com.ntustars.entity.CourseCompo;
import com.ntustars.entity.CourseIndex;
import com.ntustars.entity.Student;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.StringTokenizer;
/**
 Controller to control all the student related operation.
 @author WANG RUIZHI
 @author MA YIHENG
 @version 1.0
 @since 2020-11-10
 */
public class CourseManager {
    /**
     * seperator
     */
    private static final String SEPARATOR = ",";
    /**
     * to handle the error message
     */
    private ErrorCodeBoundary errorCodeBoundary;
    /**
     * default constructor
     */
    public CourseManager(){
        errorCodeBoundary = new ErrorCodeBoundary();
    }
    /**
     * load DB
     */
    private ArrayList loadDBCourseAndCourseIndex(){
        ArrayList stringArray = (ArrayList) TextReaderWriter.readtxt("courseAndCourseIndex.txt");
        return  stringArray;
    }
    /**
     * load DB
     */
    private ArrayList loadDBCourseIndexInfo(){
        ArrayList stringArray = (ArrayList) TextReaderWriter.readtxt("courseIndexInfo.txt");
        return  stringArray;
    }
    /**
     * load DB
     */
    private ArrayList loadDBStudentInformation(){
        ArrayList stringArray = (ArrayList) TextReaderWriter.readtxt("studentInformation.txt");
        return  stringArray;
    }
    /**
     * load DB
     */
    private ArrayList loadDBCourseIndexAndCourseCompo(){
        ArrayList stringArray = (ArrayList) TextReaderWriter.readtxt("courseIndexAndCourseCompo.txt");
        return  stringArray;
    }
    /**
     * read all course ID from db
     * @return course id array list
     */
    public ArrayList readAllCourseIDFromDB(){
        ArrayList courseAndCourseIndex = loadDBCourseAndCourseIndex();
        ArrayList<String> courseIdArrayList = new ArrayList<>();
        for(int i =0; i<courseAndCourseIndex.size();i++){
            String st = (String) courseAndCourseIndex.get(i);
            StringTokenizer star = new StringTokenizer(st, SEPARATOR);
            courseIdArrayList.add(star.nextToken().trim());
        }
        return courseIdArrayList;
    }
    /**
     * add course index into the CourseAndCourseIndexDB
     * @param courseIndex CourseIndex
     * @return int to tell whether the operation is successful
     */
    public int addCourseIndexInCourseAndCourseIndexDB(CourseIndex courseIndex){
        ArrayList courseAndCourseIndex = loadDBCourseAndCourseIndex();
        for(int i = 0; i< courseAndCourseIndex.size(); i++){
            String st = (String) courseAndCourseIndex.get(i);
            if(st.contains(courseIndex.getCourseID())){
                StringBuilder builder = new StringBuilder();
                builder.append(courseIndex.getIndex());
                builder.append(SEPARATOR);
                if(st.contains(courseIndex.getIndex())){
                    errorCodeBoundary.printErrorIndexAlreadyExist();
                    return 1; // error code for duplicated index under the same course
                }
                st+=builder.toString();
                courseAndCourseIndex.set(i,st);
            }

        }
        Collections.sort(courseAndCourseIndex);
        TextReaderWriter.writetxt("courseAndCourseIndex.txt",courseAndCourseIndex);
        return 0;
    }
    /**
     * add course index into the CourseIndexInfoCompoDB
     * @param courseIndex CourseIndex
     * @return int to tell whether the operation is successful
     */
    public int addCourseIndexInfoCompoDB(CourseIndex courseIndex){
        ArrayList courseIndexInfo = loadDBCourseIndexInfo();
        for(int i = 0; i< courseIndexInfo.size(); i++) {
            String st = (String) courseIndexInfo.get(i);
            if(st.contains(courseIndex.getIndex())){
                errorCodeBoundary.printErrorIndexAlreadyExist();
                return 1; // error code for duplicated index under the same course
            }
        }
        StringBuilder builder = new StringBuilder();
        builder.append(courseIndex.getIndex());
        builder.append(SEPARATOR);
        builder.append(courseIndex.getSlot());
        builder.append(SEPARATOR);
        builder.append(courseIndex.getAu());
        if(courseIndex.getStudentList().isEmpty()){
            builder.append(SEPARATOR);
            courseIndexInfo.add(builder.toString());
        }
        else{
            for(String student:courseIndex.getStudentList()){
                builder.append(SEPARATOR);
                builder.append(student);
                builder.append(SEPARATOR);
            }
            courseIndexInfo.add(builder.toString());
        }


        Collections.sort(courseIndexInfo);
        TextReaderWriter.writetxt("courseIndexInfo.txt",courseIndexInfo);
        return 0;
    }
    /**
     * add course index into the CourseIndexAndCourseCompoDB
     * @param courseIndex CourseIndex
     * @return int to tell whether the operation is successful
     */
    public int addCourseIndexInCourseIndexAndCourseCompoDB(CourseIndex courseIndex){
        ArrayList courseIndexAndCourseCompo = loadDBCourseIndexAndCourseCompo();
        for(int i = 0; i< courseIndexAndCourseCompo.size(); i++) {
            String st = (String) courseIndexAndCourseCompo.get(i);
            if(st.contains(courseIndex.getIndex())){
                errorCodeBoundary.printErrorIndexAlreadyExist();
                return 1; // error code for duplicated index under the same course
            }
        }
        StringBuilder builder = new StringBuilder();
        builder.append(courseIndex.getIndex());
        builder.append(SEPARATOR);
        for(CourseCompo compo : courseIndex.getCourseCompos()){
            builder.append(compo.getCompoType());
            builder.append(SEPARATOR);
            builder.append(compo.getDay());
            builder.append(SEPARATOR);
            builder.append(compo.getTimeSlot());
            builder.append(SEPARATOR);
        }
        courseIndexAndCourseCompo.add(builder.toString());
        Collections.sort(courseIndexAndCourseCompo);
        TextReaderWriter.writetxt("courseIndexAndCourseCompo.txt",courseIndexAndCourseCompo);
        return 0;
    }
    /**
     * update course index into the CourseIndexInfoCompoDB
     * @param courseIndex CourseIndex
     * @return int to tell whether the operation is successful
     */
    public int updateCourseIndexInfoCompoDB(CourseIndex courseIndex){
        ArrayList courseIndexInfo = loadDBCourseIndexInfo();
        for(int i = 0; i< courseIndexInfo.size(); i++) {
            String st = (String) courseIndexInfo.get(i);
            if(st.contains(courseIndex.getIndex())){
                StringBuilder builder = new StringBuilder();
                builder.append(courseIndex.getIndex());
                builder.append(SEPARATOR);
                builder.append(courseIndex.getSlot());
                builder.append(SEPARATOR);
                builder.append(courseIndex.getAu());
                builder.append(SEPARATOR);
                if(!courseIndex.getStudentList().isEmpty()){
                    for(String student:courseIndex.getStudentList()){
                        builder.append(student);
                        builder.append(SEPARATOR);
                    }
                }
                courseIndexInfo.set(i,builder.toString());
                Collections.sort(courseIndexInfo);
                TextReaderWriter.writetxt("courseIndexInfo.txt",courseIndexInfo);
                return 0;
            }
        }
        return addCourseIndexInfoCompoDB(courseIndex);
    }
    /**
     * update course index into the CourseIndexAndCourseCompoDB
     * @param courseIndex CourseIndex
     * @return int to tell whether the operation is successful
     */
    public int updateCourseIndexInCourseIndexAndCourseCompoDB(CourseIndex courseIndex){
        ArrayList courseIndexAndCourseCompo = loadDBCourseIndexAndCourseCompo();
        for(int i = 0; i< courseIndexAndCourseCompo.size(); i++) {
            String st = (String) courseIndexAndCourseCompo.get(i);
            if(st.contains(courseIndex.getIndex())){
                StringBuilder builder = new StringBuilder();
                builder.append(courseIndex.getIndex());
                builder.append(SEPARATOR);
                for(CourseCompo compo : courseIndex.getCourseCompos()){
                    builder.append(compo.getCompoType());
                    builder.append(SEPARATOR);
                    builder.append(compo.getDay());
                    builder.append(SEPARATOR);
                    builder.append(compo.getTimeSlot());
                    builder.append(SEPARATOR);
                }
                courseIndexAndCourseCompo.set(i, builder.toString());
                Collections.sort(courseIndexAndCourseCompo);
                TextReaderWriter.writetxt("courseIndexAndCourseCompo.txt",courseIndexAndCourseCompo);
                return 0;
            }
        }
        return addCourseIndexInCourseIndexAndCourseCompoDB(courseIndex);
    }
    /**
     * update course object into db
     * @param course course object
     * @return int to tell whether the operation is successful
     */
    public int updateCoursetoDB(Course course){
        ArrayList  courseAndCourseIndex= loadDBCourseAndCourseIndex();
        int returnCode = 0;
        for(int i = 0; i< courseAndCourseIndex.size(); i++){
            String st = (String) courseAndCourseIndex.get(i);
            if(st.contains(course.getCourseID())){
                StringBuilder builder = new StringBuilder();
                builder.append(course.getCourseID());
                builder.append(SEPARATOR);
                builder.append(course.getSchool());
                builder.append(SEPARATOR);
                builder.append(course.getCourseType());
                builder.append(SEPARATOR);
                courseAndCourseIndex.set(i,builder.toString());
                Collections.sort(courseAndCourseIndex);
                TextReaderWriter.writetxt("courseAndCourseIndex.txt",courseAndCourseIndex);
                for(CourseIndex courseIndex : course.getCourseIndices()){
                    returnCode += addCourseIndexInCourseAndCourseIndexDB(courseIndex);
                    returnCode += updateCourseIndexInfoCompoDB(courseIndex);
                    returnCode += updateCourseIndexInCourseIndexAndCourseCompoDB(courseIndex);
                }
                return returnCode;
            }
        }
        errorCodeBoundary.printErrorMissingCourse();
        return 1;
    }
    /**
     * add course object into db
     * @param course course object
     * @return int to tell whether the operation is successful
     */
    public int addCourseToDB(Course course){
        ArrayList courseAndCourseIndex = loadDBCourseAndCourseIndex();
        int code = 0;
        for(int i = 0; i< courseAndCourseIndex.size(); i++) {
            String st = (String) courseAndCourseIndex.get(i);
            if(st.contains(course.getCourseID())){
                errorCodeBoundary.printErrorCourseAlreadyExist();
                return 1; // error code for duplicated index under the same course
            }
        }
        StringBuilder builder = new StringBuilder();
        builder.append(course.getCourseID());
        builder.append(SEPARATOR);
        builder.append(course.getSchool());
        builder.append(SEPARATOR);
        builder.append(course.getCourseType());
        builder.append(SEPARATOR);
        courseAndCourseIndex.add(builder.toString());
        Collections.sort(courseAndCourseIndex);
        TextReaderWriter.writetxt("courseAndCourseIndex.txt",courseAndCourseIndex);
        for(CourseIndex courseIndex : course.getCourseIndices()){
            code += addCourseIndexInCourseAndCourseIndexDB(courseIndex);
            code += addCourseIndexInfoCompoDB(courseIndex);
            code += addCourseIndexInCourseIndexAndCourseCompoDB(courseIndex);
        }
        return code;
    }
    /**
     * get the course Type from course ID
     * @param courseID course ID
     * @return int the courseType 0 ONLYLEC 1 WITHTUT 2 WITHTUTANDLAB
     */
    public int getCourseTypebyCourseID(String courseID){
        ArrayList courseAndCourseIndex = loadDBCourseAndCourseIndex();
        for(int i =0; i <courseAndCourseIndex.size(); i++){
            String st = (String) courseAndCourseIndex.get(i);
            if(st.contains(courseID)){
                StringTokenizer star = new StringTokenizer(st, SEPARATOR);
                star.nextToken().trim();
                star.nextToken().trim();
                int courseType = Integer.parseInt(star.nextToken().trim());
                return  courseType;
            }
        }
        return -1;
    }
    /**
     * get the course Type from course ID
     * @param courseIndex course ID
     * @return int to tell whether the operation is successful
     */
    public String getCourseIDbyCourseIndex (String courseIndex){
        ArrayList courseAndCourseIndex = loadDBCourseAndCourseIndex();
        for(int i =0; i <courseAndCourseIndex.size(); i++){
            String st = (String) courseAndCourseIndex.get(i);
            if(st.contains(courseIndex)){
                StringTokenizer star = new StringTokenizer(st, SEPARATOR);
                return star.nextToken().trim();
            }
        }
        return null;
    }
    /**
     * read courseindex object from db
     * @param index course index
     * @return courseindex object
     */
    public CourseIndex readCourseIndexbyID(String index)
    {
        ArrayList courseIndexAndCourseCompo = loadDBCourseIndexAndCourseCompo();
        ArrayList courseIndexInfo = loadDBCourseIndexInfo();
        CourseIndex courseIndex = new CourseIndex();
        for(int i =0; i < courseIndexInfo.size(); i++){
            String st = (String) courseIndexInfo.get(i);
            if(st.contains(index)){
                StringTokenizer star = new StringTokenizer(st, SEPARATOR);
                star.nextToken().trim();
                courseIndex.setIndex(index);
                int slot = Integer.parseInt(star.nextToken().trim());
                courseIndex.setSlot(slot);
                int au = Integer.parseInt(star.nextToken().trim());
                courseIndex.setAu(au);
                while(star.hasMoreTokens()){
                    courseIndex.addStudent(star.nextToken().trim());
                }
                break;
            }
        }
        String CourseID = getCourseIDbyCourseIndex(index);
        if(CourseID == null)
        {
            return  null;
        }
        courseIndex.setCourseID(CourseID);
        int courseType = getCourseTypebyCourseID(CourseID);
        if(courseType == -1){
            return null;
        }

        for(int i = 0; i < courseIndexAndCourseCompo.size(); i++ ){
            String st = (String) courseIndexAndCourseCompo.get(i);
            if(st.contains(index)){
                StringTokenizer star = new StringTokenizer(st, SEPARATOR);
                star.nextToken().trim();
                for(int j = 0; j <= courseType; j++){
                    String courseCompo = star.nextToken().trim();
                    String day = star.nextToken().trim();
                    String time = star.nextToken().trim();
                    courseIndex.addCourseCompo(new CourseCompo(courseCompo, day, time));
                }
                return  courseIndex;
            }
        }
        return null;
    }
    /**
     * read course object
     * @param courseID course ID
     * @return course ID
     */
    public Course readCourseByID(String courseID)
    {
        ArrayList courseAndCourseIndex = loadDBCourseAndCourseIndex();
        for(int i = 0; i < courseAndCourseIndex.size(); i++ ){
            String st = (String) courseAndCourseIndex.get(i);
            if(st.contains(courseID)){
                StringTokenizer star = new StringTokenizer(st,SEPARATOR);
                star.nextToken().trim();
                String school = star.nextToken().trim();
                int courseType = Integer.parseInt(star.nextToken().trim());
                Course course = new Course(courseID, school, courseType);
                while(star.hasMoreTokens()){
                    String courseIndex = star.nextToken().trim();
                    course.addCourseIndex(readCourseIndexbyID(courseIndex));
                }
                return course;
            }
        }
        return null;
    }
    /**
     * check whether two course index are colission in the time slot
     * @param courseIndex1
     * @param courseIndex2
     * @return true(collision) false(not collision)
     */
    public boolean isCourseIndexCollision(CourseIndex courseIndex1, CourseIndex courseIndex2){
        for(CourseCompo courseCompo : courseIndex1.getCourseCompos()){
            String day = courseCompo.getDay();
            String timeSlot = courseCompo.getTimeSlot();
            StringTokenizer star = new StringTokenizer(timeSlot,"-");
            String firstStartingStr = day + " " + star.nextToken().trim();
            String firstEndingStr = day + " " + star.nextToken().trim();
            Calendar cal1 = DateTimeManager.convertCourseCompoStrToCalendar(firstStartingStr);
            Calendar cal2 = DateTimeManager.convertCourseCompoStrToCalendar(firstEndingStr);
            for(CourseCompo courseCompo1 : courseIndex2.getCourseCompos()){
                String day2 = courseCompo1.getDay();
                String timeSlot2 = courseCompo1.getTimeSlot();
                StringTokenizer star2 = new StringTokenizer(timeSlot2,"-");
                String secondStartingStr = day2 + " " + star2.nextToken().trim();
                String secondEndingStr = day2 + " " + star2.nextToken().trim();
                Calendar cal3 = DateTimeManager.convertCourseCompoStrToCalendar(secondStartingStr);
                Calendar cal4 = DateTimeManager.convertCourseCompoStrToCalendar(secondEndingStr);
                if(DateTimeManager.isTimeCollision(cal1, cal2,cal3, cal4)){
                    return true;
                }
            }
        }
        return false;
    }
}
