package com.eli.landa.cmpt213.Model;

import com.eli.landa.cmpt213.Enums.ActionEnum;
import com.eli.landa.cmpt213.Enums.ProgramEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eli on 2017-03-25
 */
public class Semester {
    private int semesterCode; // four digit SFU semester number code of form ZZZS, where the year is1900 + ZZZ, and the semester S is one of {1=Spring, 4=Summer, 7=Fall}.
    private int yearVal; //Stores the level the student has currently completed.

    private List<Action> actions = new ArrayList<>();

    private ProgramEnum program;

    public Semester(int semesterCodes, int yearVal) {
        this.semesterCode = semesterCodes;
        this.yearVal = yearVal;
    }

    public int getSemesterCode() {
        return semesterCode;
    }

    public List<Action> getListOfActions() {
        return actions;
    }

    public void addAction(Action action) {
        actions.add(action);
    }

    public int getYearVal() {
        return yearVal;
    }

    public void setYearValue(int yearVal) {
        this.yearVal = yearVal;
    }

    public boolean hasSemesterAction(ActionEnum actionEnum) {
        for (Action action : actions) {
            if (action.getSemesterAction().equals(actionEnum)) {
                return true;
            }
        }
        return false;
    }

    public void setProgram(ProgramEnum program) {
        this.program = program;
    }
    @Override
    public String toString() {
        return ("semester code: " + semesterCode + " year val: " + yearVal + " action: ");
    }


}
