package com.eli.landa.cmpt213.UI;

import com.eli.landa.cmpt213.Enums.ActionEnum;
import com.eli.landa.cmpt213.Enums.GenderEnum;
import com.eli.landa.cmpt213.Enums.ProgramEnum;
import com.eli.landa.cmpt213.Enums.YearEnum;
import com.eli.landa.cmpt213.Model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;

/**
 * Created by Aria on 3/31/2017.
 * <p>
 * FilterContainer class that stores the different filters we will need for the system.
 */
public class FilterContainer {

    DegreeCompletionVisualizerFacade model = DegreeCompletionVisualizerFacade.getInstance();
    StudentManager studentManager = model.getStudentManager();
    List<Student> allStudent = studentManager.getStudents();

    List<Student> sortByYear(YearEnum yearEnum, List<Student> students) {
        List<Student> filteredStudents = new ArrayList<>();
        StudentFilter yearFilter = new StudentFilter() {
            @Override
            public boolean acceptStudent(Student student) {
                NavigableMap<Integer, Semester> studentSemesterList;
                studentSemesterList = student.getSemesters();
                for (Map.Entry<Integer, Semester> semester : studentSemesterList.entrySet()) {
                    if (semester.getValue().getYearEnum().equals(yearEnum)) {
                        return true;
                    }
                }
                return false;
            }
        };
        for (Student student : students) {
            if (yearFilter.acceptStudent(student)) {
                filteredStudents.add(student);
            }
        }
        return filteredStudents;
    }

    public List<Student> sortByGender(GenderEnum genderEnum, List<Student> students) {
        List<Student> filteredStudents = new ArrayList<>();
        StudentFilter genderFilter = new StudentFilter() {
            @Override
            public boolean acceptStudent(Student student) {
                if (student.getGender().equals(genderEnum)) {
                    return true;
                }
                return false;
            }
        };
        for (Student student : students) {
            if (genderFilter.acceptStudent(student)) {
                filteredStudents.add(student);
            }
        }
        return filteredStudents;
    }

    public List<Student> sortByProgramWithGivenYear(ProgramEnum programEnum, YearEnum yearEnum, List<Student> students) {
        List<Student> filteredStudents = new ArrayList<>();

        StudentFilter programFilter = new StudentFilter() {
            @Override
            public boolean acceptStudent(Student student) {
                //if the student in their last semester in the given year is in the given program, accept
                if (student.hasSemesters()) {

                    if (student.getLastSemesterInAYear(yearEnum) != null) {

                        if (student.getLastSemesterInAYear(yearEnum).getProgram() != null) {

                            if (student.getLastSemesterInAYear(yearEnum).getProgram().equals(programEnum)) {
                                return true;
                            }
                        }
                    }
                }
                return false;
            }
        };
        for (Student student : students) {
            if (programFilter.acceptStudent(student)) {
                filteredStudents.add(student);
            }
        }


        return filteredStudents;
    }

    List<Student> listOfStudentsLeavingAtAGivenYear(YearEnum yearEnum, List<Student> students) {
        List<Student> filteredStudents = new ArrayList<>();
        StudentFilter listOfStudentsLeavingToAGivenProgramAtAGivenYearFilter = new StudentFilter() {
            @Override
            public boolean acceptStudent(Student student) {
                //if the student in their last semester in the given year is in the given program, accept
                if (student.hasSemesters()) {
                    if (student.lastRemoveInAGivenYear(yearEnum) != null) {
                        return true;
                    }
                }
                return false;
            }
        };
        for (Student student : students) {
            if (listOfStudentsLeavingToAGivenProgramAtAGivenYearFilter.acceptStudent(student)) {
                filteredStudents.add(student);
            }
        }
        return filteredStudents;
    }

    public List<Student> listOfStudentWhoDroppedOutInAGivenYear(YearEnum yearEnum, List<Student> students) {
        List<Student> filteredStudents = new ArrayList<>();
        StudentFilter listOfStudentsLeavingToAGivenProgramAtAGivenYearFilter = new StudentFilter() {
            @Override
            public boolean acceptStudent(Student student) {
                //if the student in their last semester in the given year is in the given program, accept
                if (student.hasSemesters()) {
                    NavigableMap<Integer, Semester> semestersinAGivenYear = student.getSemesters();
                    for (Map.Entry<Integer, Semester> semester : semestersinAGivenYear.entrySet()) {
                        if (semester.getValue().getYearEnum().equals(yearEnum) && !semester.getValue().getListOfActions().isEmpty()) {
                            if (semester.getValue().getListOfActions().get(0).getSemesterAction().equals(ActionEnum.DROPOUT)) {
                                return true;
                            }
                        }
                    }
                }
                return false;
            }
        };
        for (Student student : students) {
            if (listOfStudentsLeavingToAGivenProgramAtAGivenYearFilter.acceptStudent(student)) {
                filteredStudents.add(student);
            }
        }
        return filteredStudents;
    }


    List<Student> listOfStudentsLeavingToAGivenProgramAtAGivenYear(YearEnum yearEnum, List<Student> students, ProgramEnum programEnum) {
        List<Student> filteredStudents = new ArrayList<>();
        StudentFilter listOfStudentsLeavingToAGivenProgramAtAGivenYearFilter = new StudentFilter() {
            @Override
            public boolean acceptStudent(Student student) {
                //if the student in their last semester in the given year is in the given program, accept
                if (student.hasSemesters()) {
                    if (student.lastRemoveInAGivenYear(yearEnum) != null) {
                        if (student.lastRemoveInAGivenYear(yearEnum).getListOfActions().get(0).getProgram().equals(programEnum)) {
                            return true;
                        }
                    }
                }
                return false;
            }
        };
        for (Student student : students) {
            if (listOfStudentsLeavingToAGivenProgramAtAGivenYearFilter.acceptStudent(student)) {
                filteredStudents.add(student);
            }
        }
        return filteredStudents;
    }

    List<Student> listOfStudentsComingToAGivenProgramAtAGivenYear(YearEnum yearEnum, List<Student> students, ProgramEnum programEnum) {
        List<Student> filteredStudents = new ArrayList<>();
        StudentFilter listOfStudentsLeavingToAGivenProgramAtAGivenYearFilter = new StudentFilter() {
            @Override
            public boolean acceptStudent(Student student) {
                //if the student in their last semester in the given year is in the given program, accept
                if (student.hasSemesters()) {
                    if (student.lastRemoveInAGivenYear(yearEnum) != null) {
                        if (student.lastRemoveInAGivenYear(yearEnum).getListOfActions().get(1).getProgram().equals(programEnum)) {
                            return true;
                        }
                    }
                }
                return false;
            }
        };
        for (Student student : students) {
            if (listOfStudentsLeavingToAGivenProgramAtAGivenYearFilter.acceptStudent(student)) {
                filteredStudents.add(student);
            }
        }
        return filteredStudents;
    }
}