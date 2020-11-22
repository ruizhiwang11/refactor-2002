package com.ntustars;

import com.ntustars.controller.AdminManager;
import com.ntustars.controller.CourseManager;
import com.ntustars.entity.Course;
import com.ntustars.entity.CourseCompo;
import com.ntustars.entity.CourseIndex;
import com.ntustars.entity.Student;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args){
        CourseManager courseManager = new CourseManager();
        AdminManager mgr = new AdminManager();
//       CourseIndex index = new CourseIndex("CZ2005","12345",9,3);
//       Student student = new Student("LOLO123","123456","WANG LOLO","U1920000G","male","China");
//       index.addStudent(student);
//       index.addCourseCompo(new CourseCompo("LEC","THU","15:00-16:00"));
//       index.addCourseCompo(new CourseCompo("TUT","THU","15:00-16:00"));
//       index.addCourseCompo(new CourseCompo("LAB","THU","15:00-16:00"));
//        Course course = new Course("CZ2005","SCSE",2);
//        course.addCourseIndex(index);
//        // mgr.addCourse(course);
//        CourseIndex index2 = new CourseIndex("CZ2005","12346",9,3);
//        Student student2 = new Student("PUPU123","123456","LEE PUPU","U1920000K","female","Singapore");
//        index2.addStudent(student2);
//        index2.addCourseCompo(new CourseCompo("LEC","THU","15:00-16:00"));
//        index2.addCourseCompo(new CourseCompo("TUT","THU","15:00-16:00"));
//        index2.addCourseCompo(new CourseCompo("LAB","THU","15:00-16:00"));
//
//
//       course.addCourseIndex(index2);
       // CourseIndex courseIndex = courseManager.readCourseIndexbyID("12222");
       // CourseIndex courseIndex1 = courseManager.readCourseIndexbyID("12223");

        ArrayList<String> studentArrayList = mgr.getStudentByCourse("12345");
        for(String str : studentArrayList){
            System.out.println(str);
        }
        Student student2 = new Student("GAGA123","123456","CHONG GAGA","U1920000Z","male","Singapore");
        mgr.addStudent(student2);
	// write your code here
    }
}
