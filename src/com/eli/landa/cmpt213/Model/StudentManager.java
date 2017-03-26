package com.eli.landa.cmpt213.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eli on 2017-03-25.
 */
public class StudentManager {
    private static List<Student> students = new ArrayList<>();
    public static void addStudent (Student student){
        students.add(student);
    }

    public static List<Student> getStudents() {
        return students;
    }
    public static boolean hasStudent (int studentNumber){
        for (Student student: students) {
            if(studentNumber == student.getStudentNumber()){
                return true;
            }
        }
        return false;
    }
    public static Student getStudent (int studentNumber){
        for (Student student: students) {
            if(studentNumber == student.getStudentNumber()){
                return student;
            }
        }
        return null;
    }
}