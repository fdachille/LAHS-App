package com.losaltosinfo.justin.losinfo;

/**
 * Created by justin on 5/4/2016.
 */
public class Course {
    private String courseName;
    private String teacherName;
    private String textbooks;
    private String description;
    private String department;

    public Course(String department, String courseName, String teacherName, String textbooks, String description) {
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.textbooks = textbooks;
        this.description = description;
        this.department = department;
    }

    public String getDepartment() { return department; }

    public void setDepartment(String department) {this.department = department;}

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTextbooks() {
        return textbooks;
    }

    public void setTextbooks(String textbooks) {
        this.textbooks = textbooks;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
