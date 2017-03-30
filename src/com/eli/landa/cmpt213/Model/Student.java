package com.eli.landa.cmpt213.Model;

import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * Created by Eli on 2017-03-25.
 */

public class Student {
    private int studentNumber;
    private char gender; // M, F, U
    private NavigableMap<Integer, Semester> semesters = new TreeMap<>(); //new tree map sorted by semester code. The natural order.

    //Different semesters mapping to the students admitted semster, end of first, second, third, foruth semesters for easy retrieval later.
    private Semester admittedSemester;
    private Semester endOfFirstYearSemester;
    private Semester endOfSecondYearSemester;
    private Semester endOfThirdYearSemester;
    private Semester endOfFourthYearSemester;


    public Student(int studentNumber, char gender) {
        this.studentNumber = studentNumber;
        this.gender = gender;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public NavigableMap getSemesters() { //Returns the semester TreeMap
        return semesters;
    }

    public Semester getSemester(int semesterVal) {
        if (semesters.containsKey(semesterVal)) { //Checks through the treemap if it contains a semester with the semester val. If true, return this.
            System.out.println("got existing semester!");
            return semesters.get(semesterVal);

        }
        System.out.println("DID NOT GET existing semester! " + studentNumber);
        return null;
    }

    //Adds semester to the treemap, ordered naturally by semester code.
    public void addSemester(Semester semester) {
      /*  Semester test = new Semester(10, 2);
        System.out.println("Semester: " + test.getSemesterCode() + " yearVal: " + test.getYearVal());
        semesters.put(test.getSemesterCode(), test);
        System.out.println("Retrieved Semester: " + semesters.get(10).getSemesterCode() + " yearVal: " + semesters.get(10).getYearVal());*/

        // System.out.println("Added semester!" + semester.toString());
        semesters.put(semester.getSemesterCode(), semester);
        semesters.get(semester.getSemesterCode());
        System.out.println("semester size: " + semesters.size());
    }


    //Getters and setters for different key semesters of a student
    public Semester getAdmittedSemester() {
        return admittedSemester;
    }

    public void setAdmittedSemester(Semester admittedSemester) {
        this.admittedSemester = admittedSemester;
    }

    public Semester getEndOfFirstYearSemester() {
        return endOfFirstYearSemester;
    }

    public void setEndOfFirstYearSemester(Semester endOfFirstYearSemester) {
        this.endOfFirstYearSemester = endOfFirstYearSemester;
    }

    public Semester getEndOfSecondYearSemester() {
        return endOfSecondYearSemester;
    }

    public void setEndOfSecondYearSemester(Semester endOfSecondYearSemester) {
        this.endOfSecondYearSemester = endOfSecondYearSemester;
    }

    public Semester getEndOfThirdYearSemester() {
        return endOfThirdYearSemester;
    }

    public void setEndOfThirdYearSemester(Semester endOfThirdYearSemester) {
        this.endOfThirdYearSemester = endOfThirdYearSemester;
    }

    public Semester getEndOfFourthYearSemester() {
        return endOfFourthYearSemester;
    }

    public void setEndOfFourthYearSemester(Semester endOfFourthYearSemester) {
        this.endOfFourthYearSemester = endOfFourthYearSemester;
    }

    @Override
    public String toString() {
        return ("Student Number: " + studentNumber + " Gender: " + gender + " Semesters List Size: " + semesters.size());
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