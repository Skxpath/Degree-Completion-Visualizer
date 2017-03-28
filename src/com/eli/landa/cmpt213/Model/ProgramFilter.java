package com.eli.landa.cmpt213.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aria on 3/28/2017.
 */
public class ProgramFilter implements StudentFilter {

    private static List<Student> filteredStudents = new ArrayList<>();

    //Returns a list of students with a given program.
    //First Argument: List of students to filter.
    //Second Argument:
    List generateList(List<Student> students, ProgramEnum program) {
        switch (program) {
            case CSMAJ:
                for (Student s : students) {
                }
}
return null;
    }

    public Student filter(Student student) {
return null;
    }
}
