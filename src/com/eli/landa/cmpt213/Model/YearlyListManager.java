package com.eli.landa.cmpt213.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;

/**
 * Created by Aria on 3/30/2017.
 *
 * YearlyListManager to store a list of students in each year
 *
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
        RemProgramDeleter remProgramDeleter = new RemProgramDeleter();
        JoinProgramAdder joinProgramAdder = new JoinProgramAdder();


        List<Student> tempList = semesterFilter.generateList(list, SemesterEnum.ADMITTED);

        admittedStudentList = programFilter.generateList(tempList, program);

        endOfFirstYearStudentList = semesterFilter.generateList(admittedStudentList, SemesterEnum.FIRST_YEAR);
        // endOfFirstYearStudentList = remProgramDeleter.generateList(endOfFirstYearStudentList, program, SemesterEnum.FIRST_YEAR);
        //  endOfFirstYearStudentList = joinProgramAdder.generateList(endOfFirstYearStudentList, program, SemesterEnum.FIRST_YEAR);

        endOfSecondYearStudentList = semesterFilter.generateList(endOfFirstYearStudentList, SemesterEnum.SECOND_YEAR);
        //  endOfSecondYearStudentList = remProgramDeleter.generateList(endOfSecondYearStudentList, program, SemesterEnum.SECOND_YEAR);
        // endOfSecondYearStudentList = joinProgramAdder.generateList(endOfSecondYearStudentList, program, SemesterEnum.SECOND_YEAR);

        endOfThirdYearStudentList = semesterFilter.generateList(endOfSecondYearStudentList, SemesterEnum.THIRD_YEAR);
        // endOfThirdYearStudentList = remProgramDeleter.generateList(endOfThirdYearStudentList, program, SemesterEnum.THIRD_YEAR);
        // endOfThirdYearStudentList = joinProgramAdder.generateList(endOfThirdYearStudentList, program, SemesterEnum.THIRD_YEAR);

        endOfFourthYearStudentList = semesterFilter.generateList(endOfThirdYearStudentList, SemesterEnum.FOURTH_YEAR);

        for (Student s : endOfFourthYearStudentList) {

            NavigableMap<Integer, Semester> studentSemesterList;

            studentSemesterList = s.getSemesters();

            int i = 0;

            for (Map.Entry<Integer, Semester> semester : studentSemesterList.entrySet()) {

                Map.Entry<Integer, Semester> nextSemester = studentSemesterList.higherEntry(semester.getKey());

                if (nextSemester == null) {
                    ActionEnum action = ActionEnum.FIN;
                    if (semester.getValue().getAction().getSemesterAction() == action) {
                        i = 1;
                    }
                }
            }

            System.out.println(s.getStudentNumber() + " " + i);
i = 0;
        }
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
