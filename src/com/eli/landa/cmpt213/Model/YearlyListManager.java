package com.eli.landa.cmpt213.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aria on 3/30/2017.
 */
public class YearlyListManager { //Returns each year of students by program

    private static List<Student> admittedStudentList = new ArrayList<>();
    private static List<Student> endOfFirstYearStudentList = new ArrayList<>();
    private static List<Student> endOfSecondYearStudentList = new ArrayList<>();
    private static List<Student> endOfThirdYearStudentList = new ArrayList<>();
    private static List<Student> endOfFourthYearStudentList = new ArrayList<>();

    DegreeCompletionVisualizerFacade model = DegreeCompletionVisualizerFacade.getInstance();

    public void generateLists(List<Student> list, ProgramEnum program) {

        //Starting list of students independent of program.

        SemesterFilter semesterFilter = new SemesterFilter();
        ProgramFilter programFilter = new ProgramFilter();

        List<Student> tempList = semesterFilter.generateList(list, SemesterEnum.ADMITTED);
        admittedStudentList = programFilter.generateList(tempList, program);

        endOfFirstYearStudentList = semesterFilter.generateList(admittedStudentList, SemesterEnum.FIRST_YEAR);

        endOfSecondYearStudentList = semesterFilter.generateList(admittedStudentList, SemesterEnum.SECOND_YEAR);
        endOfThirdYearStudentList = semesterFilter.generateList(admittedStudentList, SemesterEnum.THIRD_YEAR);
        endOfFourthYearStudentList = semesterFilter.generateList(admittedStudentList, SemesterEnum.FOURTH_YEAR);

    }

    public static List<Student> getAdmittedStudentList() {
        return admittedStudentList;
    }

    public static List<Student> getEndOfFirstYearStudentList() {
        return endOfFirstYearStudentList;
    }

    public static List<Student> getEndOfSecondYearStudentList() {
        return endOfSecondYearStudentList;
    }

    public static List<Student> getEndOfThirdYearStudentList() {
        return endOfThirdYearStudentList;
    }

    public static List<Student> getEndOfFourthYearStudentList() {
        return endOfFourthYearStudentList;
    }
}
