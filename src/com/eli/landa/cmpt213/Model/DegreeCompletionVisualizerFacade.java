package com.eli.landa.cmpt213.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eli on 2017-03-25.
 */
public class DegreeCompletionVisualizerFacade {

    //Singleton model of Facade. Contains a studentmanager which contains everything else
    private static DegreeCompletionVisualizerFacade ourInstance = new DegreeCompletionVisualizerFacade();
    private StudentManager studentManager;
    private YearlyListManager yearlyListManager;
    private CSVReader reader;

    private static List<Student> startingStudentList = new ArrayList<>();

    private DegreeCompletionVisualizerFacade() {
        studentManager = new StudentManager();
        yearlyListManager = new YearlyListManager();
    }

    public static DegreeCompletionVisualizerFacade getInstance() {
        return ourInstance;
    }

    public StudentManager getStudentManager() {
        return studentManager;
    }

    public void setCSVReader(CSVReader reader) {
        this.reader = reader;
    }

    public void generateListsForProgram(ProgramEnum Program) {
        yearlyListManager.generateLists(studentManager.getStudents(), Program);
    }


    public YearlyListManager getYearlyListManager() {
        return yearlyListManager;
    }
}
