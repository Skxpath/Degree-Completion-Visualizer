package com.eli.landa.cmpt213.Model;

/**
 * Created by Eli on 2017-03-25.
 */
public class Semester {
    private int semesterCode; // four digit SFU semester number code of form ZZZS, where the year is1900 + ZZZ, and the semester S is one of {1=Spring, 4=Summer, 7=Fall}.
    private int yearVal; //Stores the level the student has currently completed.
    private Action action;


    public Semester(int semesterCodes, int yearVal) {
        this.semesterCode = semesterCodes;
        this.yearVal = yearVal;
    }

    public int getSemesterCode() {
        return semesterCode;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public int getYearVal() {
        return yearVal;
    }

    public void setYearVal(int yearVal) {
        this.yearVal = yearVal;
    }

    @Override
    public String toString() {
        return ("semester code: " + semesterCode + " year val: " + yearVal + " action: ");
    }
}

/*  public Semester(int semesterCodes, Action action) {
        this.semesterCode = semesterCodes;
        this.action = action;
    }*/