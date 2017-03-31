package com.eli.landa.cmpt213.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eli on 2017-03-25.
 *
 * StudentManager class that manages a list of students.
 *
 */

public class StudentManager {
    private static List<Student> students = new ArrayList<>();

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
}
