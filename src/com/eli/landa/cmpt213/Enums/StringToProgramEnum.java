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
            case "CSJNT":
                return ProgramEnum.CSJNT;
            case "OTHER":
                return ProgramEnum.OTHER;
            case "Other":
                return ProgramEnum.OTHER;
            case "HIST":
                return ProgramEnum.OTHER;
            // return ProgramEnum.HIST;
            case "ENSC":
                return ProgramEnum.OTHER;
            // return ProgramEnum.ENSC;
            case "MSE":
                return ProgramEnum.OTHER;
            //return ProgramEnum.MSE;
            case "STAT":
                return ProgramEnum.OTHER;
            //return ProgramEnum.STAT;
            default:
                // return ProgramEnum.OTHER;
                return ProgramEnum.DROPOUT; //This case occurs when the person drops out of the program typically. The csvReader reads a blank.
        }
    }
}
