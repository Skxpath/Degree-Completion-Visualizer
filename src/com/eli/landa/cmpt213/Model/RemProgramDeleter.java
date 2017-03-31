package com.eli.landa.cmpt213.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;

/**
 * Created by Aria on 3/30/2017.
 *
 * Rem Program Deleter - Removes students in the list that left the program.
 *
 */
public class RemProgramDeleter {

    private final int ADMITTED_YEAR = 1; //Admitted, end of first year, etc.
    private final int FIRST_YEAR = 3;
    private final int SECOND_YEAR = 5;
    private final int THIRD_YEAR = 7;
    private final int FOURTH_YEAR = 8;

    DegreeCompletionVisualizerFacade model = DegreeCompletionVisualizerFacade.getInstance();

    private List<Student> filteredStudents = new ArrayList<>();

    private List<Student> allStudents = model.getAllStudentsList();

    public List generateList(List<Student> students, ProgramEnum program, SemesterEnum year) {

        filteredStudents = students;

        for (Student s : filteredStudents) {
            int milestoneSemester = 0;
            int previousMilestoneSemester = 0;

            switch (year) {
                case FIRST_YEAR:
                    previousMilestoneSemester = 0;
                    milestoneSemester = FIRST_YEAR;
                    break;
                case SECOND_YEAR:
                    previousMilestoneSemester = FIRST_YEAR;
                    milestoneSemester = SECOND_YEAR;
                    break;
                case THIRD_YEAR:
                    previousMilestoneSemester = SECOND_YEAR;
                    milestoneSemester = THIRD_YEAR;
                    break;
                case FOURTH_YEAR:
                    previousMilestoneSemester = THIRD_YEAR;
                    milestoneSemester = FOURTH_YEAR;
                    break;
            }

            NavigableMap<Integer, Semester> studentSemesterList = s.getSemesters();

            for (Map.Entry<Integer, Semester> semester : studentSemesterList.entrySet()) { //iterate through the treemap

                int currentSemesterYearValue = semester.getValue().getYearVal();

                if (currentSemesterYearValue > previousMilestoneSemester && currentSemesterYearValue <= milestoneSemester) {

                    ActionEnum currentSemesterAction = semester.getValue().getAction().getSemesterAction();
                    ProgramEnum currentSemesterProgram = semester.getValue().getAction().getProgram();

                    if (currentSemesterAction == ActionEnum.ADD
                            && currentSemesterProgram != program
                            && semester.getValue().getLastMajor() == program) {
                        filteredStudents.add(s);
                    }
                }
            }

        }
        return filteredStudents;
    }
}


