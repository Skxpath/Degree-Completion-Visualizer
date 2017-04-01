package com.eli.landa.cmpt213.Model;

import com.eli.landa.cmpt213.Enums.ActionEnum;
import com.eli.landa.cmpt213.Enums.ProgramEnum;

/**
 * Created by Eli on 2017-03-25.
 * <p>
 * Action class to represent an action in the system.
 */
public class Action {

    private ActionEnum semesterAction; // admt, add, fin, dropout - see ActionEnum
    private ProgramEnum program; // program associated with the semesterAction - see ProgramEnum

    public Action(ActionEnum action, ProgramEnum program) {
        this.semesterAction = action;
        this.program = program;
    }

    public void setSemesterAction(ActionEnum semesterAction) {
        this.semesterAction = semesterAction;
    }

    public ActionEnum getSemesterAction() {
        return semesterAction;
    }

    public ProgramEnum getProgram() {
        return program;
    }

    @Override
    public String toString() {
        return "Semester semesterAction: " + semesterAction.toString() + " Semester Program: " + program.toString();
    }
}
