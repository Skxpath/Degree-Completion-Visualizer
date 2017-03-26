package com.eli.landa.cmpt213.Model;

/**
 * Created by Eli on 2017-03-25.
 */
public class Action {
    private String action; // admt, add, fin, dropout
    private String program; // program associated with the action

    public Action(String action, String program) {
        this.action = action;
        this.program = program;
    }

    public String getAction() {
        return action;
    }

    public String getProgram() {
        return program;
    }
}
