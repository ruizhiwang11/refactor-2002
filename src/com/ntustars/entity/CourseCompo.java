package com.ntustars.entity;
/**
 Represents the componments in the course, can be lec, tutorial and lab
 with the representing time slot
 @author WANG RUIZHI
 @version 1.0
 @since 2020-11-10
 */
public class CourseCompo {
    /**
     * The component type, it can be LEC, TUT or LAB.
     */
    private String compoType;
    /**
     * The day, can be Monday, Tuesday, Wednesday, Thursday and Friday.
     */
    private String day;
    /**
     * The time slot, can be 1600-1700.
     */
    private String timeSlot;
    /**
     * Creates a new CourseCompo with all the infomation
     * @param compoType The component type, it can be LEC, TUT or LAB.
     * @param day The day, can be Monday, Tyesday, Wednesday, Thursday and Friday.
     * @param timeSlot The time slot, can be 1600-1700.
     */
    public CourseCompo(String compoType, String day, String timeSlot) {
        this.compoType = compoType;
        this.day = day;
        this.timeSlot = timeSlot;
    }
    /**
     * get compoType
     * @return compoType
     */
    public String getCompoType() {
        return compoType;
    }
    /**
     * Set courseType
     * @param compoType The componment type, it can be LEC, TUT or LAB.
     */
    public void setCompoType(String compoType) {
        this.compoType = compoType;
    }
    /**
     * get day
     * @return the day
     */
    public String getDay() {
        return day;
    }
    /**
     * Set courseType
     * @param day The day, can be Monday, Tuesday, Wednesday, Thursday and Friday.
     */
    public void setDay(String day) {
        this.day = day;
    }
    /**
     * get timeSlot
     * @return timeSlot of course
     */
    public String getTimeSlot() {
        return timeSlot;
    }
    /**
     * Set courseType
     * @param timeSlot The time slot, can be 1600-1700.
     */
    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }
}
