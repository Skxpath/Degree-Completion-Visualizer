package com.eli.landa.cmpt213.UI;

import com.eli.landa.cmpt213.Enums.GenderEnum;
import com.eli.landa.cmpt213.Enums.ProgramEnum;
import com.eli.landa.cmpt213.Enums.YearEnum;
import com.eli.landa.cmpt213.Model.*;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;

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

        //System.out.println(model.getStudentManager().getStudent(302343785).getSemesters().toString());
        studentsInProgramAtMilestone(YearEnum.ADMITTED, ProgramEnum.CSMAJ);
        studentsInProgramAtMilestone(YearEnum.FIRST_YEAR, ProgramEnum.CSMAJ);
        studentsInProgramAtMilestone(YearEnum.SECOND_YEAR, ProgramEnum.CSMAJ);
        studentsInProgramAtMilestone(YearEnum.THIRD_YEAR, ProgramEnum.CSMAJ);
        //  studentsInProgramAtMilestone(YearEnum.FOURTH_YEAR, ProgramEnum.CSMAJ);


    }

    static void printSecondYearFemales() {
        StudentManager studentManager = model.getStudentManager();
        List<Student> allStudent = studentManager.getStudents();
        DumpWithFilters dumpWithFilters = new DumpWithFilters();
        List<Student> filteredStudnts;
        filteredStudnts = dumpWithFilters.sortByYear(YearEnum.SECOND_YEAR, allStudent);
        filteredStudnts = dumpWithFilters.sortByGender(GenderEnum.FEMALE, filteredStudnts);
        for (Student student : filteredStudnts) {
            System.out.println(student.toString());
        }

        NavigableMap<Integer, Semester> studentSemesterList;
        studentSemesterList = studentManager.getStudent(326209703).getSemesters();
        System.out.println(studentSemesterList.toString());

        System.out.println(studentSemesterList.size());

        for (Map.Entry<Integer, Semester> semester : studentSemesterList.entrySet()) {
            System.out.println(semester.getValue().toString());
        }

    }

    static void printSecondYearFemalesInCSMajor() {
        StudentManager studentManager = model.getStudentManager();
        List<Student> allStudent = studentManager.getStudents();
        DumpWithFilters dumpWithFilters = new DumpWithFilters();
        List<Student> filteredStudnts;
        filteredStudnts = dumpWithFilters.sortByGender(GenderEnum.FEMALE, allStudent);
        System.out.println("Size of filtered List " + filteredStudnts.size());
        filteredStudnts = dumpWithFilters.sortByProgramWithGivenYear(ProgramEnum.CSMAJ, YearEnum.SECOND_YEAR, filteredStudnts);
        System.out.println("Size of filtered List " + filteredStudnts.size());


        for (Student student : filteredStudnts) {
            System.out.println(student.toString());
        }

        // System.out.println(studentSemesterList.size());


    }

    static void printOutFlowOfStudentsIn2ndYearCSMajorThatAreAlsoFemale() {
        StudentManager studentManager = model.getStudentManager();
        List<Student> allStudent = studentManager.getStudents();
        DumpWithFilters dumpWithFilters = new DumpWithFilters();
        List<Student> filteredStudnts;
        filteredStudnts = dumpWithFilters.sortByGender(GenderEnum.MALE, allStudent);
        // System.out.println("Size of filtered List " + filteredStudnts.size());
        filteredStudnts = dumpWithFilters.listOfStudentsLeavingToAGivenProgramAtAGivenYear(YearEnum.THIRD_YEAR, filteredStudnts, ProgramEnum.CSMAJ);
        // filteredStudnts = dumpWithFilters.listOfStudentsLeavingAtAGivenYear(YearEnum.THIRD_YEAR, filteredStudnts);
        System.out.println("Size of filtered List " + filteredStudnts.size());
        for (Student student : filteredStudnts) {
            if (!student.getRemoveSemesters().isEmpty()) {
                System.out.println(student.getStudentNumber() + ": Leaving to " + student.lastRemoveInAGivenYear(YearEnum.THIRD_YEAR).getListOfActions().get(1).getProgram());
            } else {
                System.out.println(student.toString() + "no remove");
            }

        }

    }

    static void printInFlowOfStudentsIn2ndYearCSMajorThatAreAlsoFemale() {
        StudentManager studentManager = model.getStudentManager();
        List<Student> allStudent = studentManager.getStudents();
        DumpWithFilters dumpWithFilters = new DumpWithFilters();
        List<Student> filteredStudnts;
        filteredStudnts = dumpWithFilters.sortByGender(GenderEnum.MALE, allStudent);
        // System.out.println("Size of filtered List " + filteredStudnts.size());
        filteredStudnts = dumpWithFilters.listOfStudentsComingToAGivenProgramAtAGivenYear(YearEnum.THIRD_YEAR, filteredStudnts, ProgramEnum.SOSY);
        // filteredStudnts = dumpWithFilters.listOfStudentsLeavingAtAGivenYear(YearEnum.THIRD_YEAR, filteredStudnts);
        System.out.println("Size of filtered List " + filteredStudnts.size());
        for (Student student : filteredStudnts) {
            if (!student.getRemoveSemesters().isEmpty()) {
                System.out.println(student.getStudentNumber() + ": Coming from " + student.lastRemoveInAGivenYear(YearEnum.THIRD_YEAR).getListOfActions().get(0).getProgram());
            } else {
                System.out.println(student.toString() + "no remove");
            }

        }

    }

    static void studentsInProgramAtMilestone(YearEnum yearEnum, ProgramEnum programEnum) {
/*        ************************************
*       Students in program at milestone *
        ************************************
        ADMITTED    1: M=    0  F=    1  U=    0 (CS_MAJOR students at ADMITTED)
        YEAR1    1: M=    0  F=    1  U=    0 (CS_MAJOR students at YEAR1)
        YEAR2    1: M=    0  F=    1  U=    0 (CS_MAJOR students at YEAR2)
        YEAR3    1: M=    0  F=    1  U=    0 (CS_MAJOR students at YEAR3)
        YEAR4    1: M=    0  F=    1  U=    0 (CS_MAJOR students at YEAR4)
        GRADUATED    1: M=    0  F=    1  U=    0 (CS_MAJOR students at GRADUATED)*/



        StudentManager studentManager = model.getStudentManager();
        List<Student> allStudent = studentManager.getStudents();
        DumpWithFilters dumpWithFilters = new DumpWithFilters();

        //System.out.println(allStudent.size());

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
