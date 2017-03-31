package com.eli.landa.cmpt213.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;

/**
 * Created by Aria on 3/28/2017.
 * <p>
 * Returns students at the end of a certain year. Either L1, right before L3, L6, L8. (Admit, end of first, second, third, and fourth years respectively)
 */
public class SemesterFilter {

    private final int ADMITTED_YEAR = 1; //Admitted, end of first year, etc.
    private final int FIRST_YEAR = 3; //End of first, beginning of second boundary
    private final int SECOND_YEAR = 5;
    private final int THIRD_YEAR = 7;
    private final int FOURTH_YEAR = 8;
    private final int GRADUATED_YEAR = 9;

    SemesterEnum SemesterYearEnum;

    private NavigableMap<Integer, Semester> studentSemesterList;
    private int CURRENT_YEAR = 0; //Holds the variable for the year we want to filter by.

    //Method to generate a filtered list of students. Passes in a list of students to filter, and the end of year to filter by.
    //Returns a list of students who had participated in this year, and sets their "critical semesters" for later iterating.

    public List generateList(List<Student> students, SemesterEnum year) {

        this.SemesterYearEnum = year;

        List<Student> filteredStudentList = new ArrayList<>();

        switch (year) {
            case ADMITTED: //Sets the year before this one, to allow us to find the boundary if they dropped mid way.
                CURRENT_YEAR = 0;
                //List of students to pass in, list of students to return, upper bound. In Admit and fourth year, we have boundary case issues.
                return getList(students, filteredStudentList, ADMITTED_YEAR);
            case FIRST_YEAR:
                CURRENT_YEAR = FIRST_YEAR;
                return getList(students, filteredStudentList, SECOND_YEAR);
            case SECOND_YEAR:
                CURRENT_YEAR = SECOND_YEAR;
                return getList(students, filteredStudentList, THIRD_YEAR);
            case THIRD_YEAR:
                CURRENT_YEAR = THIRD_YEAR;
                return getList(students, filteredStudentList, FOURTH_YEAR);
            case FOURTH_YEAR:
                CURRENT_YEAR = FOURTH_YEAR;
                return getList(students, filteredStudentList, FOURTH_YEAR);
            default:
                return filteredStudentList; //Should never occur
        }

    }

    //Function to filter students by current semester - We bound them from lower to upper.
    //Admitted year (Beginning of first year): Only for L1 and action ADMT.
    //End of first year, beginning of second year: For everyone below L3 (31+ credits);
    //End of second year: For everyone below L5 (61+);
    //End of third year: For everyone below L7;
    //End of fourth year: People who graduated (L8 and action FIN);
    private List getList(List<Student> students, List<Student> filteredStudentList, int year) { //list of students to iterate over, student list to return, year we are filtering by

        //First loop adds all students who belong to their respective semesters.
        //For each loop over every student in the student list.
        for (Student currentStudent : students) {

            //Get the current iterated student's semester TreeMap.
            studentSemesterList = currentStudent.getSemesters();

            //For each semester in the TreeMap...
            for (Map.Entry<Integer, Semester> semester : studentSemesterList.entrySet()) { //iterate through the treemap

                Map.Entry<Integer, Semester> nextSemester = studentSemesterList.higherEntry(semester.getKey()); // next semester on the map
                Map.Entry<Integer, Semester> prevSemester = studentSemesterList.lowerEntry(semester.getKey());  // previous semester on the map

                Semester currentSemester = semester.getValue(); //Current semester we are iterating
                int currentSemestersYearVal = currentSemester.getYearVal(); //Current semesters year value
                ActionEnum currentSemesterAction = currentSemester.getAction().getSemesterAction(); //Current semesters action

                int nextSemestersYearValue = -1;
                //Next semesters year value. In the case where there is a next semester.
                if (nextSemester != null) {
                    nextSemestersYearValue = nextSemester.getValue().getYearVal();
                }
                // Case where we are adding every student who was admitted to the school.
                if (currentSemestersYearVal == ADMITTED_YEAR && currentSemesterAction == ActionEnum.ADMT && year == ADMITTED_YEAR) {
                    filteredStudentList.add(currentStudent);
                    break;
                } else
                    //Edge case when we are in the fourth year to add graduates.
                    if (currentSemestersYearVal == FOURTH_YEAR && currentSemesterAction == ActionEnum.FIN && year == FOURTH_YEAR) {
                        filteredStudentList.add(currentStudent);
                        break;
                    }
                //Edge case if we have a new enrollment that drops out right afterward.
                if (currentSemestersYearVal == CURRENT_YEAR && currentSemesterAction == ActionEnum.DROPOUT) {
                    filteredStudentList.add(currentStudent);
                    break;
                } else
                    //Case where the semester is within the desired level range but theres no semester ahead of it because they dropped out.
                    if (CURRENT_YEAR < currentSemestersYearVal && currentSemestersYearVal < year && currentSemesterAction == ActionEnum.DROPOUT) {
                        filteredStudentList.add(currentStudent);
                        break;
                    } else
                        //Base case.
                        if (currentSemestersYearVal < year && year == nextSemestersYearValue) {
                            filteredStudentList.add(currentStudent);
                            break;
                        }
            }
        }
        return filteredStudentList;

        //Student #:359491818 - dropped out in L8, still exists in year 4 list. Everything else seems okay.
    }
}
