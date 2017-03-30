package com.eli.landa.cmpt213.Model;

import java.io.File;
import java.util.List;

/**
 * Created by Eli on 2017-03-25.
 */
public class DegreeCompletionVisualizerFacade {

    //Singleton model of Facade. Contains a studentmanager which contains everything ele
    private static DegreeCompletionVisualizerFacade ourInstance = new DegreeCompletionVisualizerFacade();
    private  StudentManager studentManager;
    private CSVReader reader;

    private DegreeCompletionVisualizerFacade() {
        studentManager = new StudentManager();
    }

    public static DegreeCompletionVisualizerFacade getInstance() {
        return ourInstance;
    }

    public  StudentManager getStudentManager() {
        return studentManager;
    }
    public void setCSVReader (CSVReader reader){
        this.reader = reader;
    }
}
