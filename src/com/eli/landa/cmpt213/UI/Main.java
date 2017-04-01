package com.eli.landa.cmpt213.UI;

import com.eli.landa.cmpt213.Enums.GenderEnum;
import com.eli.landa.cmpt213.Enums.ProgramEnum;
import com.eli.landa.cmpt213.Enums.YearEnum;
import com.eli.landa.cmpt213.Model.CSVReader;
import com.eli.landa.cmpt213.Model.DegreeCompletionVisualizerFacade;
import com.eli.landa.cmpt213.Model.Student;
import com.eli.landa.cmpt213.Model.StudentManager;

import java.io.File;
import java.util.List;

public class Main {
    static DegreeCompletionVisualizerFacade model = DegreeCompletionVisualizerFacade.getInstance();

    //Files are populated from a local directory in CSVReader.java line 192. This must be changed to fit the directory of the user.
    public static void main(String[] args) {

        CSVReader reader = new CSVReader();
        List<File> files = reader.populateFiles();
        model.setCSVReader(reader);

        for (int i = files.size() - 1; i >= 0; i--) {
            reader.readCSVFile(files.get(i));
        }

        model.getStudentManager().populateProgramsInStudentSemesters();

        displayUI(ProgramEnum.CSMAJ);


    }

    private static void displayUI(ProgramEnum selectedProgram) {
        System.out.println("************************************\n" +
                "* Students in program at milestone *\n" +
                "************************************");

        studentsInProgramAtMilestone(YearEnum.ADMITTED, selectedProgram);
        studentsInProgramAtMilestone(YearEnum.FIRST_YEAR, selectedProgram);
        studentsInProgramAtMilestone(YearEnum.SECOND_YEAR, selectedProgram);
        studentsInProgramAtMilestone(YearEnum.THIRD_YEAR, selectedProgram);
        studentsInProgramAtMilestone(YearEnum.FOURTH_YEAR, selectedProgram);
        studentsInProgramAtMilestone(YearEnum.GRADUATED, selectedProgram);

        System.out.println("\n*************************************************\n" +
                "* Students joining program going into milestone *\n" +
                "*************************************************");

        studentsWhoJoinProgramAtMilestone(YearEnum.FIRST_YEAR, selectedProgram);
        studentsWhoJoinProgramAtMilestone(YearEnum.SECOND_YEAR, selectedProgram);
        studentsWhoJoinProgramAtMilestone(YearEnum.THIRD_YEAR, selectedProgram);
        studentsWhoJoinProgramAtMilestone(YearEnum.FOURTH_YEAR, selectedProgram);

        System.out.println("******************************************************\n" +
                "* LEAVING: Students leaving program before milestone *\n" +
                "******************************************************");

        studentsWhoLeftProgramAtMilestone(YearEnum.FIRST_YEAR, selectedProgram);
        studentsWhoLeftProgramAtMilestone(YearEnum.SECOND_YEAR, selectedProgram);
        studentsWhoLeftProgramAtMilestone(YearEnum.THIRD_YEAR, selectedProgram);
        studentsWhoLeftProgramAtMilestone(YearEnum.FOURTH_YEAR, selectedProgram);
    }

    static void studentsWhoJoinProgramAtMilestone(YearEnum yearEnum, ProgramEnum programEnum) {

        System.out.println("Year: " + yearEnum);

        int totalJoined = 0;

        totalJoined += joinedFromSpecificProgram(programEnum, ProgramEnum.CSMAJ, yearEnum);
        totalJoined += joinedFromSpecificProgram(programEnum, ProgramEnum.SOSY, yearEnum);
        totalJoined += joinedFromSpecificProgram(programEnum, ProgramEnum.CSMNR, yearEnum);
        totalJoined += joinedFromSpecificProgram(programEnum, ProgramEnum.CSJNT, yearEnum);
        totalJoined += joinedFromSpecificProgram(programEnum, ProgramEnum.OTHER, yearEnum);
        totalJoined += joinedFromSpecificProgram(programEnum, ProgramEnum.NO_PROGRAM, yearEnum);
        System.out.println("Total Joined: " + totalJoined + "\n");
    }

    static void studentsWhoLeftProgramAtMilestone(YearEnum yearEnum, ProgramEnum programEnum) {

        System.out.println("Year: " + yearEnum);

        int totalLeft = 0;

        totalLeft += leftToSpecificProgram(programEnum, ProgramEnum.CSMAJ, yearEnum);
        totalLeft += leftToSpecificProgram(programEnum, ProgramEnum.SOSY, yearEnum);
        totalLeft += leftToSpecificProgram(programEnum, ProgramEnum.CSMNR, yearEnum);
        totalLeft += leftToSpecificProgram(programEnum, ProgramEnum.CSJNT, yearEnum);
        totalLeft += leftToSpecificProgram(programEnum, ProgramEnum.OTHER, yearEnum);
        totalLeft += leftToSpecificProgram(programEnum, ProgramEnum.NO_PROGRAM, yearEnum);
        System.out.println("Total Left: " + totalLeft + "\n");
    }

    static int leftToSpecificProgram(ProgramEnum programEnum, ProgramEnum newProgram, YearEnum yearEnum) {
        DumpWithFilters dumpWithFilters = new DumpWithFilters();

        StudentManager studentManager = model.getStudentManager();
        List<Student> allStudent = studentManager.getStudents();

        List<Student> filteredStudntsMale;
        List<Student> filteredStudntsFemale;
        List<Student> filteredStudntsUnknown;

        List<Student> filteredStudntsMaleLeft;
        List<Student> filteredStudntsFemaleLeft;
        List<Student> filteredStudntsUnknownLeft;

        List<Student> filteredStudntsMaleDropped;
        List<Student> filteredStudntsFemaleDropped;
        List<Student> filteredStudntsUnknownDropped;

        filteredStudntsMale = dumpWithFilters.sortByProgramWithGivenYear(newProgram, yearEnum, allStudent);
        filteredStudntsMale = dumpWithFilters.sortByGender(GenderEnum.MALE, filteredStudntsMale);
        filteredStudntsFemale = dumpWithFilters.sortByProgramWithGivenYear(newProgram, yearEnum, allStudent);
        filteredStudntsFemale = dumpWithFilters.sortByGender(GenderEnum.FEMALE, filteredStudntsFemale);
        filteredStudntsUnknown = dumpWithFilters.sortByProgramWithGivenYear(newProgram, yearEnum, allStudent);
        filteredStudntsUnknown = dumpWithFilters.sortByGender(GenderEnum.UNKNOWN, filteredStudntsUnknown);

        filteredStudntsMaleLeft = dumpWithFilters.listOfStudentsLeavingToAGivenProgramAtAGivenYear(yearEnum, filteredStudntsMale, programEnum);
        filteredStudntsFemaleLeft = dumpWithFilters.listOfStudentsLeavingToAGivenProgramAtAGivenYear(yearEnum, filteredStudntsFemale, programEnum);
        filteredStudntsUnknownLeft = dumpWithFilters.listOfStudentsLeavingToAGivenProgramAtAGivenYear(yearEnum, filteredStudntsUnknown, programEnum);

        int totalLeft = filteredStudntsMaleLeft.size() + filteredStudntsFemaleLeft.size() + filteredStudntsUnknownLeft.size();

        if (newProgram == ProgramEnum.NO_PROGRAM) {
            filteredStudntsMale = dumpWithFilters.sortByProgramWithGivenYear(programEnum, yearEnum, allStudent);
            filteredStudntsMale = dumpWithFilters.sortByGender(GenderEnum.MALE, filteredStudntsMale);
            filteredStudntsFemale = dumpWithFilters.sortByProgramWithGivenYear(programEnum, yearEnum, allStudent);
            filteredStudntsFemale = dumpWithFilters.sortByGender(GenderEnum.FEMALE, filteredStudntsFemale);
            filteredStudntsUnknown = dumpWithFilters.sortByProgramWithGivenYear(programEnum, yearEnum, allStudent);
            filteredStudntsUnknown = dumpWithFilters.sortByGender(GenderEnum.UNKNOWN, filteredStudntsUnknown);

            filteredStudntsMaleDropped = dumpWithFilters.listOfStudentWhoDroppedOutInAGivenYear(yearEnum, filteredStudntsMale);
            filteredStudntsFemaleDropped = dumpWithFilters.listOfStudentWhoDroppedOutInAGivenYear(yearEnum, filteredStudntsFemale);
            filteredStudntsUnknownDropped = dumpWithFilters.listOfStudentWhoDroppedOutInAGivenYear(yearEnum, filteredStudntsUnknown);

            totalLeft = filteredStudntsMaleDropped.size()+filteredStudntsFemaleDropped.size()+filteredStudntsUnknownDropped.size();

            System.out.println("left: " + totalLeft
                    + " M=    " + filteredStudntsMaleDropped.size()
                    + " F=    " + filteredStudntsFemaleDropped.size() + " U=    " + filteredStudntsUnknownDropped.size() + " (reason for leaving " + newProgram + ")");

            return totalLeft;
        }

        System.out.println("left: " + totalLeft
                + " M=    " + filteredStudntsMaleLeft.size()
                + " F=    " + filteredStudntsFemaleLeft.size() + " U=    " + filteredStudntsUnknownLeft.size() + " (reason for leaving " + newProgram + ")");

        return totalLeft;
    }


    static int joinedFromSpecificProgram(ProgramEnum programEnum, ProgramEnum newProgram, YearEnum yearEnum) {

        DumpWithFilters dumpWithFilters = new DumpWithFilters();

        StudentManager studentManager = model.getStudentManager();
        List<Student> allStudent = studentManager.getStudents();

        List<Student> filteredStudntsMale;
        List<Student> filteredStudntsFemale;
        List<Student> filteredStudntsUnknown;

        List<Student> filteredStudntsMaleLeft;
        List<Student> filteredStudntsFemaleLeft;
        List<Student> filteredStudntsUnknownLeft;

        filteredStudntsMale = dumpWithFilters.sortByProgramWithGivenYear(programEnum, yearEnum, allStudent);
        filteredStudntsMale = dumpWithFilters.sortByGender(GenderEnum.MALE, filteredStudntsMale);
        filteredStudntsFemale = dumpWithFilters.sortByProgramWithGivenYear(programEnum, yearEnum, allStudent);
        filteredStudntsFemale = dumpWithFilters.sortByGender(GenderEnum.FEMALE, filteredStudntsFemale);
        filteredStudntsUnknown = dumpWithFilters.sortByProgramWithGivenYear(programEnum, yearEnum, allStudent);
        filteredStudntsUnknown = dumpWithFilters.sortByGender(GenderEnum.UNKNOWN, filteredStudntsUnknown);

        filteredStudntsMaleLeft = dumpWithFilters.listOfStudentsLeavingToAGivenProgramAtAGivenYear(yearEnum, filteredStudntsMale, newProgram);
        filteredStudntsFemaleLeft = dumpWithFilters.listOfStudentsLeavingToAGivenProgramAtAGivenYear(yearEnum, filteredStudntsFemale, newProgram);
        filteredStudntsUnknownLeft = dumpWithFilters.listOfStudentsLeavingToAGivenProgramAtAGivenYear(yearEnum, filteredStudntsUnknown, newProgram);

        int totalJoined = filteredStudntsMaleLeft.size() + filteredStudntsFemaleLeft.size() + filteredStudntsUnknownLeft.size();

        System.out.println("joined: " + totalJoined
                + " M=    " + filteredStudntsMaleLeft.size()
                + " F=    " + filteredStudntsFemaleLeft.size() + " U=    " + filteredStudntsUnknownLeft.size() + " (previous program " + newProgram + ")");

        return totalJoined;
    }


    static void studentsInProgramAtMilestone(YearEnum yearEnum, ProgramEnum programEnum) {

        StudentManager studentManager = model.getStudentManager();
        List<Student> allStudent = studentManager.getStudents();
        DumpWithFilters dumpWithFilters = new DumpWithFilters();

        List<Student> filteredStudntsMale;
        List<Student> filteredStudntsFemale;
        List<Student> filteredStudntsUnknown;
        List<Student> filteredStudntsMaleLeft;
        List<Student> filteredStudntsFemaleLeft;
        List<Student> filteredStudntsUnknownLeft;
        List<Student> filteredStudntsMaleDropped;
        List<Student> filteredStudntsFemaleDropped;
        List<Student> filteredStudntsUnknownDropped;

        filteredStudntsMale = dumpWithFilters.sortByProgramWithGivenYear(programEnum, yearEnum, allStudent);
        filteredStudntsMale = dumpWithFilters.sortByGender(GenderEnum.MALE, filteredStudntsMale);
        filteredStudntsMaleLeft = dumpWithFilters.listOfStudentsLeavingToAGivenProgramAtAGivenYear(yearEnum, filteredStudntsMale, programEnum);
        filteredStudntsMaleDropped = dumpWithFilters.listOfStudentWhoDroppedOutInAGivenYear(yearEnum, filteredStudntsMale);


        filteredStudntsFemale = dumpWithFilters.sortByProgramWithGivenYear(programEnum, yearEnum, allStudent);
        filteredStudntsFemale = dumpWithFilters.sortByGender(GenderEnum.FEMALE, filteredStudntsFemale);
        filteredStudntsFemaleLeft = dumpWithFilters.listOfStudentsLeavingToAGivenProgramAtAGivenYear(yearEnum, filteredStudntsFemale, programEnum);
        filteredStudntsFemaleDropped = dumpWithFilters.listOfStudentWhoDroppedOutInAGivenYear(yearEnum, filteredStudntsFemale);

        filteredStudntsUnknown = dumpWithFilters.sortByProgramWithGivenYear(programEnum, yearEnum, allStudent);
        filteredStudntsUnknown = dumpWithFilters.sortByGender(GenderEnum.UNKNOWN, filteredStudntsUnknown);
        filteredStudntsUnknownLeft = dumpWithFilters.listOfStudentsLeavingToAGivenProgramAtAGivenYear(yearEnum, filteredStudntsUnknown, programEnum);
        filteredStudntsUnknownDropped = dumpWithFilters.listOfStudentWhoDroppedOutInAGivenYear(yearEnum, filteredStudntsUnknown);

       /* NavigableMap<Integer, Semester> semestersinAGivenYear = studentManager.getStudent(392960629).getSemesters();

        for (Map.Entry<Integer, Semester> semester : semestersinAGivenYear.entrySet()) {
            //  System.out.println(semester.getValue().getProgram());
        }*/


        // System.out.println("M Drop: " + filteredStudntsMaleLeft.size() + " F Drop: " + filteredStudntsFemaleLeft.size() + " U Drop: " + filteredStudntsUnknownLeft.size());
        int remainingMaleStudents = filteredStudntsMale.size() - filteredStudntsMaleLeft.size() - filteredStudntsMaleDropped.size();
        int renamingFemaleStudents = filteredStudntsFemale.size() - filteredStudntsFemaleLeft.size() - filteredStudntsFemaleDropped.size();
        int remainingUnknownStudents = filteredStudntsUnknown.size() - filteredStudntsUnknownLeft.size() - filteredStudntsUnknownDropped.size();
        int remainingTotalStudents = remainingMaleStudents + renamingFemaleStudents + remainingUnknownStudents;
        System.out.println(yearEnum + "     " + remainingTotalStudents + ":  M = " + remainingMaleStudents
                + " F = " + renamingFemaleStudents + " U = "
                + remainingUnknownStudents + " CS_MAJOR students at " + yearEnum);
    }

}
