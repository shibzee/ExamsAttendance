package com.examsattendance.model;

/**
 * Created by root on 16/08/18.
 */

public class Course {

    private int course_id;
    private String course_code;
    private String title;

    public Course(int course_id, String course_code, String title) {
        this.course_id = course_id;
        this.course_code = course_code;
        this.title = title;
    }

    public Course() {
    }

    public Course(String course_code, String title) {
        this.course_code = course_code;
        this.title = title;
    }

    public Course(String course_code) {
        this.course_code = course_code;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }
}
