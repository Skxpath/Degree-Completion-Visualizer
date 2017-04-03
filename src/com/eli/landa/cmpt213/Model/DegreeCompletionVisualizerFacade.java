package com.eli.landa.cmpt213.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eli on 2017-03-25.
 * <p>
 * Facade class to access everything required by the system.
 */
public class DegreeCompletionVisualizerFacade {

    //Singleton model of Facade. Contains a studentmanager which contains everything else
    private static DegreeCompletionVisualizerFacade ourInstance = new DegreeCompletionVisualizerFacade();
    private StudentManager studentManager;
    private CSVReader reader;

    private static List<Student> startingStudentList = new ArrayList<>();

    private DegreeCompletionVisualizerFacade() {
        studentManager = new StudentManager();
    }

    public static DegreeCompletionVisualizerFacade getInstance() {
        return ourInstance;
    }

    public StudentManager getStudentManager() {
        return studentManager;
    }

    public List getAllStudentsList() {
        return studentManager.getStudents();
    }

    public void setCSVReader(CSVReader reader) {
        this.reader = reader;
    }
}
