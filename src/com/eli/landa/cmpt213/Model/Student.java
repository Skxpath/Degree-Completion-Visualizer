package com.eli.landa.cmpt213.Model;

import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by Eli on 2017-03-25.
 */

public class Student {
    private long studentNumber;
    private char gender; // M, F, U
    private int yearsOfStudy;
    private TreeMap<Integer, Semester> semesters = new TreeMap<>(); //new tree map sorted by semester code. The natural order.

    public Student(long studentNumber, char gender) {
        this.studentNumber = studentNumber;
        this.gender = gender;
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

    public TreeMap getSemesters() { //Returns the semester TreeMap
        return semesters;
    }

    public Semester getSemester(int semesterVal) {
            if (semesters.containsKey(semesterVal)) { //Checks through the treemap if it contains a semester with the semester val. If true, return this.
                return semesters.get(semesterVal);
            }
        return null;
    }

    //Adds semester to the treemap, ordered naturally by semester code.
    public void addSemester(Semester semester) {
        semesters.put(semester.getSemesterCode(), semester);
    }
}

//Unnecessary constructors

/*    public Student(long studentNumber, char gender, int yearsOfStudy, Semester semester) {
        this.studentNumber = studentNumber;
        this.gender = gender;
        this.yearsOfStudy = yearsOfStudy;
        this.semesters.add(semester);
    }*/

/*    public Student(long studentNumber, Semester semester) {
        this.studentNumber = studentNumber;
        this.semesters.add(semester);
    }

    public Student(long studentNumber, int yearsOfStudy, Semester semester) {
        this.studentNumber = studentNumber;
        this.yearsOfStudy = yearsOfStudy;
        this.semesters.add(semester);
    }*/
/*    public Student(long studentNumber) {
        this.studentNumber = studentNumber;
    }*/

 /*   public boolean hasSemester(int semesterVal) {
        for (Semester semester : semesters) {
            if (semesterVal == semester.getSemesterCode()) {
                return true;
            }
        }
        return false;
    }*/