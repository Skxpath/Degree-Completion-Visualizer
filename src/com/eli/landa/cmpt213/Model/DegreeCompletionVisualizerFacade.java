package com.eli.landa.cmpt213.Model;

/**
 * Created by Eli on 2017-03-25.
 */
public class DegreeCompletionVisualizerFacade {

    //Singleton model of Facade. Contains a studentmanager which contains everything ele
    private static DegreeCompletionVisualizerFacade ourInstance = new DegreeCompletionVisualizerFacade();
    private static StudentManager studentManager;

    private DegreeCompletionVisualizerFacade() {
        studentManager = new StudentManager();
    }

    public static DegreeCompletionVisualizerFacade getInstance() {
        return ourInstance;
    }

    public static StudentManager getStudentManager() {
        return studentManager;
    }
}
