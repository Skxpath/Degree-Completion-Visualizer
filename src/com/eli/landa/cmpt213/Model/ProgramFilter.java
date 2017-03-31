package com.eli.landa.cmpt213.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aria on 3/28/2017.
 * <p>
 * To be completed. Will probably filter the list we get from SemesterFilter?
 */
public class ProgramFilter {

    private static List<Student> filteredStudents = new ArrayList<>();

    //Returns a list of students with a given program.
    //First Argument: List of students to filter.
    //Second Argument:
    public List generateList(List<Student> students, ProgramEnum program) {
        switch (program) {
            case CSMAJ:
                for (Student s : students) {
                    if (s.getAdmittedSemester().getAction().getProgram() == ProgramEnum.CSMAJ) {
                        filteredStudents.add(s);
                    }
                }
        }
        return filteredStudents;
    }

}
