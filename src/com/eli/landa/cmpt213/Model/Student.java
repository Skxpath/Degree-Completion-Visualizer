package com.eli.landa.cmpt213.Model;

import com.eli.landa.cmpt213.Enums.GenderEnum;

import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * Created by Eli on 2017-03-25.
 *
 * Student Class that stores an object for each student.
 *
 */

public class Student {
    private int studentNumber;
    private GenderEnum gender; // Male, Female, Unknown
    private NavigableMap<Integer, Semester> semesters = new TreeMap<>(); //new tree map sorted by semester code. The natural order.

    public Student(int studentNumber, GenderEnum gender) {
        this.studentNumber = studentNumber;
        this.gender = gender;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public NavigableMap getSemesters() { //Returns the semester TreeMap
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
        semesters.get(semester.getSemesterCode());

    }

    @Override
    public String toString() {
        return ("Student Number: " + studentNumber + " Gender: " + gender + " Semesters List Size: " + semesters.size());
    }
}
