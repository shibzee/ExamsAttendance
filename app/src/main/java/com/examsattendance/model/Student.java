package com.examsattendance.model;

/**
 * Created by root on 13/08/18.
 */

public class Student {

    private String fname;
    private String lname;
    private String regno;
    private String level;
    private String department;
    private String faculty;
    private String image_url;

    public Student(String regno,String fname, String lname,  String level, String department, String faculty, String image_url) {
        this.fname = fname;
        this.lname = lname;
        this.regno = regno;
        this.level = level;
        this.department = department;
        this.faculty = faculty;
        this.image_url = image_url;
    }



    public Student() {
    }
    public Student( String regno, String fname, String lname, String level, String department, String faculty) {
        this.fname = fname;
        this.lname = lname;
        this.regno = regno;
        this.level = level;
        this.department = department;
        this.faculty = faculty;
    }



    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getRegno() {
        return regno;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }


    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
