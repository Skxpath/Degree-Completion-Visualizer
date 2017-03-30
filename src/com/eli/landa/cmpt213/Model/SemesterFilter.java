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
    private final int FIRST_YEAR = 2;
    private final int SECOND_YEAR = 5;
    private final int THIRD_YEAR = 7;
    private final int FOURTH_YEAR = 8;
    private NavigableMap<Integer, Semester> studentSemesterList;

    //Method to generate a filtered list of students. Passes in a list of students to filter, and the end of year to filter by.
    //Returns a list of students who had participated in this year, and sets their "critical semesters" for later iterating.

    public List generateList(List<Student> students, SemesterEnum year) {

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

        //For each loop over every student in the student list.
        for (Student s : students) {

            //Get the current iterated student's semester TreeMap.
            studentSemesterList = s.getSemesters();

            //For each semester in the treemap...
            for (Map.Entry<Integer, Semester> entry : studentSemesterList.entrySet()) { //iterate through the treemap

                Map.Entry<Integer, Semester> next = studentSemesterList.higherEntry(entry.getKey()); // next entry on the map
                Map.Entry<Integer, Semester> prev = studentSemesterList.lowerEntry(entry.getKey());  // previous entry on the map

                //If the semester has the same year value as the one we are trying to filter by...
                if (entry.getValue().getYearVal() == year) {
                    if (year == ADMITTED_YEAR) { //If we are filtering by the semester the student is admitted. We can break at the first semester we encounter.

                        s.setAdmittedSemester(entry.getValue()); //Add to personal students admitted list this semester and the following semester, for easy retrevial of information

                        filteredStudentList.add(s); //Add the student to the filtered student list
                        break;
                    } else {
                        if ((next.getValue().getYearVal() > year) || (next == null)) { //If this is the last semester before the student moves to the next level, or has no future semester
                            switch (year) {
                                case FIRST_YEAR:
                                    s.setEndOfFirstYearSemester(entry.getValue()); //Set the critical semester of the student relating to which year we are filtering by
                                    filteredStudentList.add(s); //Add to the filtered list
                                    break;
                                case SECOND_YEAR:
                                    s.setEndOfSecondYearSemester(entry.getValue());
                                    filteredStudentList.add(s);
                                    break;
                                case THIRD_YEAR:
                                    s.setEndOfThirdYearSemester(entry.getValue());
                                    filteredStudentList.add(s);
                                    break;
                                case FOURTH_YEAR:
                                    s.setEndOfFourthYearSemester(entry.getValue());
                                    filteredStudentList.add(s);
                                    break;

                            }
                        }
                    }
                }
            }

        }
        return filteredStudentList;
    }
}