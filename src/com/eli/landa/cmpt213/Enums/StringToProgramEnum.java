package com.eli.landa.cmpt213.Enums;

/**
 * Created by Aria on 3/28/2017.
 * <p>
 * Converts a programVal into a ProgramEnum before inserting to Action
 */
public class StringToProgramEnum {

    public static ProgramEnum convert(String program) {
        switch (program) {
            case "CSMAJ":
                return ProgramEnum.CSMAJ;
            case "SOSY":
                return ProgramEnum.SOSY;
            case "CSMNR":
                return ProgramEnum.CSMNR;
            case "HIST":
                return ProgramEnum.HIST;
            case "ENSC":
                return ProgramEnum.ENSC;
            case "MSE":
                return ProgramEnum.MSE;
            case "STAT":
                return ProgramEnum.STAT;
            case "OTHER":
                return ProgramEnum.OTHER;
            default:
                return ProgramEnum.NO_PROGRAM; //This case occurs when the person drops out of the program typically. The csvReader reads a blank.
        }
    }
}
