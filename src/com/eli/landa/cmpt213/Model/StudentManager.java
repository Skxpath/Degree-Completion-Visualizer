package com.eli.landa.cmpt213.Model;

import com.eli.landa.cmpt213.Enums.ActionEnum;
import com.eli.landa.cmpt213.Enums.ProgramEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;

/**
 * Created by Eli on 2017-03-25.
 * <p>
 * StudentManager class that manages a list of students.
 */

public class StudentManager {
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public List<Student> getStudents() {
        return students;
    }

    public boolean hasStudent(int studentNumber) {
        for (Student student : students) {
            if (studentNumber == student.getStudentNumber()) {
                return true;
            }
        }
        return false;
    }

    public Student getStudent(int studentNumber) {
        for (Student student : students) {

            if (studentNumber == student.getStudentNumber()) {

                return student;
            }
        }
        return null;
    }

    public void populateProgramsInStudentSemesters() {

        for (Student student : students) {
            ProgramEnum currentProgram = ProgramEnum.NO_PROGRAM;

            NavigableMap<Integer, Semester> studentSemesterList = student.getSemesters();

            //For each semester in the TreeMap...
            for (Map.Entry<Integer, Semester> semester : studentSemesterList.entrySet()) { //iterate through the treemap

                Semester currentSemester = semester.getValue();

                if (currentSemester.hasSemesterAction(ActionEnum.ADMT)
                        || currentSemester.hasSemesterAction(ActionEnum.ADD)) {
                    if(currentSemester.hasSemesterAction(ActionEnum.ADMT)) {
                        currentProgram = currentSemester.getListOfActions().get(0).getProgram();
                    } else {
                        currentProgram = currentSemester.getListOfActions().get(1).getProgram();
                    }
                }
                currentSemester.setProgram(currentProgram);
            }
        }
    }
}
