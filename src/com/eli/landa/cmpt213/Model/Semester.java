package com.eli.landa.cmpt213.Model;

/**
 * Created by Eli on 2017-03-25.
 */
public class Semester {
    private int semesterCode; // four digit SFU semester number code of form ZZZS, where the year is1900 + ZZZ, and the semester S is one of {1=Spring, 4=Summer, 7=Fall}.
    private Action action;

    public Semester(int semesterCodes, Action action) {
        this.semesterCode = semesterCodes;
        this.action = action;
    }
    public Semester(int semesterCodes) {
        this.semesterCode = semesterCodes;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public int getSemesterCode() {
        return semesterCode;
    }

    public Action getAction() {
        return action;
    }
}
