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

/*
* TextUI class to display data dump to the console.
* */
public class TextUI {
    private static DegreeCompletionVisualizerFacade model = DegreeCompletionVisualizerFacade.getInstance();

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

        displayStudentsInProgramAtMilestone(selectedProgram);
        displayStudentsJoiningProgram(selectedProgram);
        displayStudentsLeavingProgram(selectedProgram);

    }

    private static void displayStudentsLeavingProgram(ProgramEnum selectedProgram) {
        System.out.println("******************************************************\n" +
                "* LEAVING: Students leaving program before milestone *\n" +
                "******************************************************");

        studentsWhoLeftProgramAtMilestone(YearEnum.FIRST_YEAR, selectedProgram);
        studentsWhoLeftProgramAtMilestone(YearEnum.SECOND_YEAR, selectedProgram);
        studentsWhoLeftProgramAtMilestone(YearEnum.THIRD_YEAR, selectedProgram);
        studentsWhoLeftProgramAtMilestone(YearEnum.FOURTH_YEAR, selectedProgram);
    }

    private static void displayStudentsJoiningProgram(ProgramEnum selectedProgram) {
        System.out.println("\n*************************************************\n" +
                "* Students joining program going into milestone *\n" +
                "*************************************************");

        studentsWhoJoinProgramAtMilestone(YearEnum.FIRST_YEAR, selectedProgram);
        studentsWhoJoinProgramAtMilestone(YearEnum.SECOND_YEAR, selectedProgram);
        studentsWhoJoinProgramAtMilestone(YearEnum.THIRD_YEAR, selectedProgram);
        studentsWhoJoinProgramAtMilestone(YearEnum.FOURTH_YEAR, selectedProgram);
    }

    private static void displayStudentsInProgramAtMilestone(ProgramEnum selectedProgram) {
        System.out.println("************************************\n" +
                "* Students in program at milestone *\n" +
                "************************************");

        studentsInProgramAtMilestone(YearEnum.ADMITTED, selectedProgram);
        studentsInProgramAtMilestone(YearEnum.FIRST_YEAR, selectedProgram);
        studentsInProgramAtMilestone(YearEnum.SECOND_YEAR, selectedProgram);
        studentsInProgramAtMilestone(YearEnum.THIRD_YEAR, selectedProgram);
        studentsInProgramAtMilestone(YearEnum.FOURTH_YEAR, selectedProgram);
        studentsInProgramAtMilestone(YearEnum.GRADUATED, selectedProgram);
    }

    private static void studentsInProgramAtMilestone(YearEnum yearEnum, ProgramEnum programEnum) {

        StudentManager studentManager = model.getStudentManager();
        List<Student> allStudentsList = studentManager.getStudents();
        FilterContainer filterContainer = new FilterContainer();

        List<Student> filteredStudentListMale;
        List<Student> filteredStudentListFemale;
        List<Student> filteredStudentListUnknown;

        List<Student> filteredStudentListWhoLeftProgramMale;
        List<Student> filteredStudentListWhoLeftProgramFemale;
        List<Student> filteredStudentListWhoLeftProgramUnknown;

        List<Student> filteredStudentsWhoDroppedMale;
        List<Student> filteredStudentsWhoDroppedFemale;
        List<Student> filteredStudentsWhoDroppedUnknown;

        filteredStudentListMale = filterContainer.sortByGender(GenderEnum.MALE, filterContainer.sortByProgramWithGivenYear(programEnum, yearEnum, allStudentsList));
        filteredStudentListWhoLeftProgramMale = filterContainer.listOfStudentsLeavingToAGivenProgramAtAGivenYear(yearEnum, filteredStudentListMale, programEnum);
        filteredStudentsWhoDroppedMale = filterContainer.listOfStudentWhoDroppedOutInAGivenYear(yearEnum, filteredStudentListMale);

        filteredStudentListFemale = filterContainer.sortByGender(GenderEnum.FEMALE, filterContainer.sortByProgramWithGivenYear(programEnum, yearEnum, allStudentsList));
        filteredStudentListWhoLeftProgramFemale = filterContainer.listOfStudentsLeavingToAGivenProgramAtAGivenYear(yearEnum, filteredStudentListFemale, programEnum);
        filteredStudentsWhoDroppedFemale = filterContainer.listOfStudentWhoDroppedOutInAGivenYear(yearEnum, filteredStudentListFemale);

        filteredStudentListUnknown = filterContainer.sortByGender(GenderEnum.UNKNOWN, filterContainer.sortByProgramWithGivenYear(programEnum, yearEnum, allStudentsList));
        filteredStudentListWhoLeftProgramUnknown = filterContainer.listOfStudentsLeavingToAGivenProgramAtAGivenYear(yearEnum, filteredStudentListUnknown, programEnum);
        filteredStudentsWhoDroppedUnknown = filterContainer.listOfStudentWhoDroppedOutInAGivenYear(yearEnum, filteredStudentListUnknown);

        int remainingMaleStudents = filteredStudentListMale.size() - filteredStudentListWhoLeftProgramMale.size() - filteredStudentsWhoDroppedMale.size();
        int renamingFemaleStudents = filteredStudentListFemale.size() - filteredStudentListWhoLeftProgramFemale.size() - filteredStudentsWhoDroppedFemale.size();
        int remainingUnknownStudents = filteredStudentListUnknown.size() - filteredStudentListWhoLeftProgramUnknown.size() - filteredStudentsWhoDroppedUnknown.size();

        int remainingTotalStudents = remainingMaleStudents + renamingFemaleStudents + remainingUnknownStudents;

        System.out.println(yearEnum + "     " + remainingTotalStudents + ":  M = " + remainingMaleStudents
                + " F = " + renamingFemaleStudents + " U = "
                + remainingUnknownStudents + " CS_MAJOR students at " + yearEnum);
    }

    private static void studentsWhoJoinProgramAtMilestone(YearEnum yearEnum, ProgramEnum programEnum) {

        System.out.println("Year: " + yearEnum);

        int totalJoined = 0;

        totalJoined += joinedFromSpecificProgram(programEnum, ProgramEnum.CSMAJ, yearEnum);
        totalJoined += joinedFromSpecificProgram(programEnum, ProgramEnum.SOSY, yearEnum);
        totalJoined += joinedFromSpecificProgram(programEnum, ProgramEnum.CSMNR, yearEnum);
        totalJoined += joinedFromSpecificProgram(programEnum, ProgramEnum.CSJNT, yearEnum);
        totalJoined += joinedFromSpecificProgram(programEnum, ProgramEnum.OTHER, yearEnum);
        totalJoined += joinedFromSpecificProgram(programEnum, ProgramEnum.DROPOUT, yearEnum);

        System.out.println("Total Joined: " + totalJoined + "\n");
    }

    private static void studentsWhoLeftProgramAtMilestone(YearEnum yearEnum, ProgramEnum programEnum) {

        System.out.println("Year: " + yearEnum);

        int totalLeft = 0;

        totalLeft += leftToSpecificProgram(programEnum, ProgramEnum.CSMAJ, yearEnum);
        totalLeft += leftToSpecificProgram(programEnum, ProgramEnum.SOSY, yearEnum);
        totalLeft += leftToSpecificProgram(programEnum, ProgramEnum.CSMNR, yearEnum);
        totalLeft += leftToSpecificProgram(programEnum, ProgramEnum.CSJNT, yearEnum);
        totalLeft += leftToSpecificProgram(programEnum, ProgramEnum.OTHER, yearEnum);
        totalLeft += leftToSpecificProgram(programEnum, ProgramEnum.DROPOUT, yearEnum);

        System.out.println("Total Left: " + totalLeft + "\n");
    }

    private static int leftToSpecificProgram(ProgramEnum programEnum, ProgramEnum newProgram, YearEnum yearEnum) {

        FilterContainer filterContainer = new FilterContainer();
        StudentManager studentManager = model.getStudentManager();
        List<Student> allStudentsList = studentManager.getStudents();

        List<Student> filteredStudentListMale;
        List<Student> filteredStudentListFemale;
        List<Student> filteredStudentListUnknown;

        List<Student> filteredStudentListWhoLeftProgramMale;
        List<Student> filteredStudentListWhoLeftProgramFemale;
        List<Student> filteredStudentListWhoLeftProgramUnknown;

        List<Student> filteredStudentsWhoDroppedMale;
        List<Student> filteredStudentsWhoDroppedFemale;
        List<Student> filteredStudentsWhoDroppedUnknown;

        filteredStudentListMale = filterContainer.sortByGender(GenderEnum.MALE, filterContainer.sortByProgramWithGivenYear(newProgram, yearEnum, allStudentsList));
        filteredStudentListFemale = filterContainer.sortByGender(GenderEnum.FEMALE, filterContainer.sortByProgramWithGivenYear(newProgram, yearEnum, allStudentsList));
        filteredStudentListUnknown = filterContainer.sortByGender(GenderEnum.UNKNOWN, filterContainer.sortByProgramWithGivenYear(newProgram, yearEnum, allStudentsList));

        filteredStudentListWhoLeftProgramMale = filterContainer.listOfStudentsLeavingToAGivenProgramAtAGivenYear(yearEnum, filteredStudentListMale, programEnum);
        filteredStudentListWhoLeftProgramFemale = filterContainer.listOfStudentsLeavingToAGivenProgramAtAGivenYear(yearEnum, filteredStudentListFemale, programEnum);
        filteredStudentListWhoLeftProgramUnknown = filterContainer.listOfStudentsLeavingToAGivenProgramAtAGivenYear(yearEnum, filteredStudentListUnknown, programEnum);

        int totalLeft = filteredStudentListWhoLeftProgramMale.size() + filteredStudentListWhoLeftProgramFemale.size() + filteredStudentListWhoLeftProgramUnknown.size();

        if (newProgram.equals(ProgramEnum.DROPOUT)) {

            filteredStudentListMale = filterContainer.sortByGender(GenderEnum.MALE, filterContainer.sortByProgramWithGivenYear(programEnum, yearEnum, allStudentsList));
            filteredStudentListFemale = filterContainer.sortByGender(GenderEnum.FEMALE, filterContainer.sortByProgramWithGivenYear(programEnum, yearEnum, allStudentsList));
            filteredStudentListUnknown = filterContainer.sortByGender(GenderEnum.UNKNOWN, filterContainer.sortByProgramWithGivenYear(programEnum, yearEnum, allStudentsList));

            filteredStudentsWhoDroppedMale = filterContainer.listOfStudentWhoDroppedOutInAGivenYear(yearEnum, filteredStudentListMale);
            filteredStudentsWhoDroppedFemale = filterContainer.listOfStudentWhoDroppedOutInAGivenYear(yearEnum, filteredStudentListFemale);
            filteredStudentsWhoDroppedUnknown = filterContainer.listOfStudentWhoDroppedOutInAGivenYear(yearEnum, filteredStudentListUnknown);

            totalLeft = filteredStudentsWhoDroppedMale.size() + filteredStudentsWhoDroppedFemale.size() + filteredStudentsWhoDroppedUnknown.size();

            System.out.println("left: " + totalLeft
                    + " M=    " + filteredStudentsWhoDroppedMale.size()
                    + " F=    " + filteredStudentsWhoDroppedFemale.size() + " U=    " + filteredStudentsWhoDroppedUnknown.size() + " (reason for leaving " + newProgram + ")");

            return totalLeft;
        } else {
            System.out.println("left: " + totalLeft
                    + " M=    " + filteredStudentListWhoLeftProgramMale.size()
                    + " F=    " + filteredStudentListWhoLeftProgramFemale.size() + " U=    " + filteredStudentListWhoLeftProgramUnknown.size() + " (reason for leaving " + newProgram + ")");
            return totalLeft;
        }
    }


    private static int joinedFromSpecificProgram(ProgramEnum programEnum, ProgramEnum newProgram, YearEnum yearEnum) {

        FilterContainer filterContainer = new FilterContainer();

        StudentManager studentManager = model.getStudentManager();
        List<Student> allStudentsList = studentManager.getStudents();

        List<Student> filteredStudentListMale;
        List<Student> filteredStudentListFemale;
        List<Student> filteredStudentListUnknown;

        List<Student> filteredStudentListWhoLeftProgramMale;
        List<Student> filteredStudentListWhoLeftProgramFemale;
        List<Student> filteredStudentListWhoLeftProgramUnknown;

        filteredStudentListMale = filterContainer.sortByGender(GenderEnum.MALE, filterContainer.sortByProgramWithGivenYear(programEnum, yearEnum, allStudentsList));
        filteredStudentListFemale = filterContainer.sortByGender(GenderEnum.FEMALE, filterContainer.sortByProgramWithGivenYear(programEnum, yearEnum, allStudentsList));
        filteredStudentListUnknown = filterContainer.sortByGender(GenderEnum.UNKNOWN, filterContainer.sortByProgramWithGivenYear(programEnum, yearEnum, allStudentsList));

        filteredStudentListWhoLeftProgramMale = filterContainer.listOfStudentsLeavingToAGivenProgramAtAGivenYear(yearEnum, filteredStudentListMale, newProgram);
        filteredStudentListWhoLeftProgramFemale = filterContainer.listOfStudentsLeavingToAGivenProgramAtAGivenYear(yearEnum, filteredStudentListFemale, newProgram);
        filteredStudentListWhoLeftProgramUnknown = filterContainer.listOfStudentsLeavingToAGivenProgramAtAGivenYear(yearEnum, filteredStudentListUnknown, newProgram);

        int totalJoined = filteredStudentListWhoLeftProgramMale.size() + filteredStudentListWhoLeftProgramFemale.size() + filteredStudentListWhoLeftProgramUnknown.size();

        System.out.println("joined: " + totalJoined
                + " M=    " + filteredStudentListWhoLeftProgramMale.size()
                + " F=    " + filteredStudentListWhoLeftProgramFemale.size() + " U=    " + filteredStudentListWhoLeftProgramUnknown.size() + " (previous program " + newProgram + ")");

        return totalJoined;
    }
}
