package com.ntustars.controller;

import com.ntustars.entity.Course;
import com.ntustars.entity.CourseCompo;
import com.ntustars.entity.CourseIndex;
import com.ntustars.entity.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class AdminManager {

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

}

