package com.eli.landa.cmpt213.Model;

import java.util.*;

/**
 * Created by Aria on 3/28/2017.
 *
 * Returns students at the end of a certain year. Either L1, right before L3, L6, L8. (Admit, end of first, second, third, and fourth years respectively)
 *
 */
public class SemesterFilter {

    private TreeMap<Integer, Semester> studentSemesterList;

    private final int ADMITTED_YEAR = 1; //Admitted, end of first year, etc.
    private final int FIRST_YEAR = 2;
    private final int SECOND_YEAR = 5;
    private final int THIRD_YEAR = 7;
    private final int FOURTH_YEAR = 8;

    List generateList(List<Student> students, SemesterEnum year) {

        List<Student> filteredStudentList = new ArrayList<>();

        switch (year) {
            case ADMITTED:
                return getList(students, filteredStudentList, ADMITTED_YEAR);
            case FIRST_YEAR:
                return getList(students, filteredStudentList, FIRST_YEAR);
            case SECOND_YEAR:
                return getList(students, filteredStudentList, SECOND_YEAR);
            case THIRD_YEAR:
                return getList(students, filteredStudentList, THIRD_YEAR);
            case FOURTH_YEAR:
                return getList(students, filteredStudentList, FOURTH_YEAR);
            default:
                return filteredStudentList; //Should never occur
        }

    }

    //Function to filter students by current semester
    private List getList(List<Student> students, List<Student> filteredStudentList, int year) {

        for (Student s : students) {

            studentSemesterList = s.getSemesters();

            for (Map.Entry<Integer, Semester> entry : studentSemesterList.entrySet()) { //iterate through the treemap

                if (entry.getValue().getYearVal() == year) { //Gets students that have been in the year at least in one semester.
                    if (year == ADMITTED_YEAR) {
                        filteredStudentList.add(s);
                        break;
                    }
                }

            }
        }
        return filteredStudentList;
    }
}