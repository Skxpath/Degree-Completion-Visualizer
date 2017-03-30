package com.eli.landa.cmpt213.Model;

/**
 * Created by Eli on 2017-03-25.
 */
public class Action {
    private ActionEnum action; // admt, add, fin, dropout - see ActionEnum
    private ProgramEnum program; // program associated with the action - see ProgramEnum

    public Action(ActionEnum action, ProgramEnum program) {
        this.action = action;
        this.program = program;
    }

    public ActionEnum getAction() {
        return action;
    }

    public ProgramEnum getProgram() {
        return program;
    }

    @Override
    public String toString() {
        return action.toString();
    }
}
