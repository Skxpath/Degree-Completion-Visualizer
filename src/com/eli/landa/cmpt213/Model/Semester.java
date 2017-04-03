package com.eli.landa.cmpt213.Model;

import com.eli.landa.cmpt213.Enums.ActionEnum;
import com.eli.landa.cmpt213.Enums.ProgramEnum;
import com.eli.landa.cmpt213.Enums.YearEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eli on 2017-03-25
 * <p>
 * Semester class to store different semesters in the system
 */
public class Semester {
    private int semesterCode; // four digit SFU semester number code of form ZZZS, where the year is1900 + ZZZ, and the semester S is one of {1=Spring, 4=Summer, 7=Fall}.
    private YearEnum yearEnum; //Stores the level the student has currently completed.

    private List<Action> actions = new ArrayList<>();

    private ProgramEnum program;

    public Semester(int semesterCodes, YearEnum yearEnum) {
        this.semesterCode = semesterCodes;
        this.yearEnum = yearEnum;
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

    public YearEnum getYearEnum() {
        return yearEnum;
    }

    public void setYearValue(YearEnum yearVal) {
        this.yearEnum = yearVal;
    }

    public boolean hasSemesterAction(ActionEnum actionEnum) {
        for (Action action : actions) {
            if (action.getSemesterAction().equals(actionEnum)) {
                return true;
            }
        }
        return false;
    }

    public ProgramEnum getProgram() {
        return program;
    }

    public void setProgram(ProgramEnum program) {
        this.program = program;
    }

    @Override
    public String toString() {
        if (!actions.isEmpty()) {
            return ("semester code: " + semesterCode + " year val: " + yearEnum + " action: " + actions.get(0).getSemesterAction());
        } else
            return ("semester code: " + semesterCode + " year val: " + yearEnum + " action: " + ActionEnum.NO_ACTION);
    }
}
