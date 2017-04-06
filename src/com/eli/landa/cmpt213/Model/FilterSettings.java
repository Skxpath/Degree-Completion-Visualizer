package com.eli.landa.cmpt213.Model;

import com.eli.landa.cmpt213.Enums.ProgramEnum;

/**
 * Created by Eli on 2017-04-05.
 */
public class FilterSettings {
    ProgramEnum programEnum;
    boolean inSelectedProgramInYear;
    boolean graduatedSelectedProgramInYear;
    int year1;
    int year2;

    public FilterSettings(ProgramEnum programEnum, boolean inSelectedProgramInYear, boolean graduatedSelectedProgramInYear, int year1, int year2) {
        this.programEnum = programEnum;
        this.inSelectedProgramInYear = inSelectedProgramInYear;
        this.graduatedSelectedProgramInYear = graduatedSelectedProgramInYear;
        this.year1 = year1;
        this.year2 = year2;
    }

    public ProgramEnum getProgramEnum() {
        return programEnum;
    }

    public void setProgramEnum(ProgramEnum programEnum) {
        this.programEnum = programEnum;
    }

    public boolean isInSelectedProgramInYear() {
        return inSelectedProgramInYear;
    }

    public void setInSelectedProgramInYear(boolean inSelectedProgramInYear) {
        this.inSelectedProgramInYear = inSelectedProgramInYear;
    }

    public boolean isGraduatedSelectedProgramInYear() {
        return graduatedSelectedProgramInYear;
    }

    public void setGraduatedSelectedProgramInYear(boolean graduatedSelectedProgramInYear) {
        this.graduatedSelectedProgramInYear = graduatedSelectedProgramInYear;
    }

    public int getYear1() {
        return year1;
    }

    public void setYear1(int year1) {
        this.year1 = year1;
    }

    public int getYear2() {
        return year2;
    }

    public void setYear2(int year2) {
        this.year2 = year2;
    }
}
