package com.eli.landa.cmpt213.Model;

/**
 * Created by Eli on 2017-03-25.
 */
public class Semester {
    private int semesterCode; // four digit SFU semester number code of form ZZZS, where the year is1900 + ZZZ, and the semester S is one of {1=Spring, 4=Summer, 7=Fall}.
    private int yearVal; //Stores the level the student has currently completed.
    private Action action;

    private ProgramEnum lastMajor;
    private ProgramEnum newMajor;
    private boolean remFlag = false;

    public Semester(int semesterCodes, int yearVal) {
        this.semesterCode = semesterCodes;
        this.yearVal = yearVal;
        this.action = new Action(ActionEnum.NO_ACTION, ProgramEnum.NO_PROGRAM); //Sets default action of each semester to no action, and default no program.
    }

    public int getSemesterCode() {
        return semesterCode;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        if (action.getSemesterAction() == ActionEnum.REM) {
            this.lastMajor = action.getProgram();
        }

        if (action.getSemesterAction() == ActionEnum.ADD) {
            if (this.action.getSemesterAction() == ActionEnum.REM) {
                remFlag = true;
            }
            this.newMajor = action.getProgram();
        }
        this.action = action;
    }

    public int getYearVal() {
        return yearVal;
    }

    public void setYearVal(int yearVal) {
        this.yearVal = yearVal;
    }

    public ProgramEnum getLastMajor() {
        return lastMajor;
    }

    public ProgramEnum getNewMajor() {
        return newMajor;
    }

    @Override
    public String toString() {
        return ("semester code: " + semesterCode + " year val: " + yearVal + " action: " + action.getSemesterAction());
    }

    public boolean isRemFlag() { //Toggles if semester has had a major removed before this add.
        return remFlag;
    }
}
