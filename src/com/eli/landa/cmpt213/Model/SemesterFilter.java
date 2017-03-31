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

    //Test code cause build is not pushing properly for some reason

    private final int ADMITTED_YEAR = 1; //Admitted, end of first year, etc.
    private final int FIRST_YEAR = 3;
    private final int SECOND_YEAR = 4;
    private final int THIRD_YEAR = 6;
    private final int FOURTH_YEAR = 8;

    private NavigableMap<Integer, Semester> studentSemesterList;

    private int YEAR_BEFORE = 0; //Holds the variable for the year before the year we want to filter by.

    SemesterEnum SemesterYearEnum;

    //Method to generate a filtered list of students. Passes in a list of students to filter, and the end of year to filter by.
    //Returns a list of students who had participated in this year, and sets their "critical semesters" for later iterating.

    public List generateList(List<Student> students, SemesterEnum year) {

        this.SemesterYearEnum = year;

        List<Student> filteredStudentList = new ArrayList<>();

        switch (year) {
            case ADMITTED: //Sets the year before this one, to allow us to find the boundary if they dropped mid way.
                YEAR_BEFORE = 0;
                return getList(students, filteredStudentList, ADMITTED_YEAR);
            case FIRST_YEAR:
                YEAR_BEFORE = ADMITTED_YEAR;
                return getList(students, filteredStudentList, FIRST_YEAR);
            case SECOND_YEAR:
                YEAR_BEFORE = FIRST_YEAR;
                return getList(students, filteredStudentList, SECOND_YEAR);
            case THIRD_YEAR:
                YEAR_BEFORE = SECOND_YEAR;
                return getList(students, filteredStudentList, THIRD_YEAR);
            case FOURTH_YEAR:
                YEAR_BEFORE = THIRD_YEAR;
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

            //For each semester in the TreeMap...
            for (Map.Entry<Integer, Semester> semester : studentSemesterList.entrySet()) { //iterate through the treemap

                Map.Entry<Integer, Semester> nextSemester = studentSemesterList.higherEntry(semester.getKey()); // next semester on the map
                Map.Entry<Integer, Semester> prevSemester = studentSemesterList.lowerEntry(semester.getKey());  // previous semester on the map

                Semester currentSemester = semester.getValue(); //Current semester we are iterating
                int currentSemestersYearVal = currentSemester.getYearVal(); //Current semesters year value

                boolean studentDroppedOrFinishedProgram = currentSemester.getAction().getSemesterAction() == ActionEnum.DROPOUT
                        || currentSemester.getAction().getSemesterAction() == ActionEnum.FIN; //Boolean of if we dropped or finished the program

                if (studentDroppedOrFinishedProgram) {

                    if ((YEAR_BEFORE < currentSemestersYearVal) && (currentSemestersYearVal <= year)) {

                        switch (year) {
                            case ADMITTED_YEAR:
                                s.setAdmittedSemester(currentSemester);
                                filteredStudentList.add(s);
                                break;
                            case FIRST_YEAR:
                                s.setEndOfFirstYearSemester(currentSemester); //Set the critical semester of the student relating to which year we are filtering by
                                filteredStudentList.add(s); //Add to the filtered list
                                break;
                            case SECOND_YEAR:
                                s.setEndOfSecondYearSemester(currentSemester);
                                filteredStudentList.add(s);
                                break;
                            case THIRD_YEAR:
                                s.setEndOfThirdYearSemester(currentSemester);
                                filteredStudentList.add(s);
                                break;
                            case FOURTH_YEAR:
                                s.setEndOfFourthYearSemester(currentSemester);
                                filteredStudentList.add(s);
                                break;
                        }
                    }
                } else if (currentSemestersYearVal == year) {  //If the semester has the same year value as the one we are trying to filter by...
                    if (currentSemester.getAction().getSemesterAction() == ActionEnum.ADMT) { //If we are filtering by the semester the student is admitted. We can break at the first semester we encounter.

                        s.setAdmittedSemester(currentSemester); //Add to personal students admitted list this semester and the following semester, for easy retrevial of information

                        filteredStudentList.add(s); //Add the student to the filtered student list
                        break;
                    } else if ((nextSemester.getValue().getYearVal() > year)) { //If this is the last semester before the student moves to the nextSemester level, or has no future semester

                            switch (year) {
                                case FIRST_YEAR:
                                    s.setEndOfFirstYearSemester(currentSemester); //Set the critical semester of the student relating to which year we are filtering by
                                    filteredStudentList.add(s); //Add to the filtered list
                                    break;
                                case SECOND_YEAR:
                                    s.setEndOfSecondYearSemester(currentSemester);
                                    filteredStudentList.add(s);
                                    break;
                                case THIRD_YEAR:
                                    s.setEndOfThirdYearSemester(currentSemester);
                                    filteredStudentList.add(s);
                                    break;
                                case FOURTH_YEAR:
                                    s.setEndOfFourthYearSemester(currentSemester);
                                    filteredStudentList.add(s);
                                    break;

                            }

                        }
                    }
                }
            }


        return filteredStudentList;
    }
}