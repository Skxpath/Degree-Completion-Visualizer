package com.eli.landa.cmpt213.Model;

import java.util.List;

/**
 * Created by Eli on 2017-03-25.
 */
public class Student {
    private long studentNumber;
    private char gender; // M, F, U
    private int yearsOfStudy;
    private List<Semester> semesters;

    public Student(long studentNumber, char gender, int yearsOfStudy, Semester semester) {
        this.studentNumber = studentNumber;
        this.gender = gender;
        this.yearsOfStudy = yearsOfStudy;
        this.semesters.add(semester);
    }
    public Student(long studentNumber, char gender) {
        this.studentNumber = studentNumber;
        this.gender = gender;
    }

    public Student(long studentNumber, Semester semester) {
        this.studentNumber = studentNumber;
        this.semesters.add(semester);
    }

    public Student(long studentNumber, int yearsOfStudy, Semester semester) {
        this.studentNumber = studentNumber;
        this.yearsOfStudy = yearsOfStudy;
        this.semesters.add(semester);
    }
    public Student(long studentNumber) {
        this.studentNumber = studentNumber;
    }

    public long getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(long studentNumber) {
        this.studentNumber = studentNumber;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public int getYearsOfStudy() {
        return yearsOfStudy;
    }

    public void setYearsOfStudy(int yearsOfStudy) {
        this.yearsOfStudy = yearsOfStudy;
    }

    public List<Semester> getSemesters() {
        return semesters;
    }

    public  boolean hasSemester (int semesterVal){
        for (Semester semester: semesters) {
            if(semesterVal == semester.getSemesterCode()){
                return true;
            }
        }
        return false;
    }
    public  Semester getSemester (int semesterVal){
        for (Semester semester: semesters) {
            if(semesterVal == semester.getSemesterCode()){
                return semester;
            }
        }
        return null;
    }
    public void addSemester (Semester semester){
        semesters.add(semester);
    }
}
