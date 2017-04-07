package com.eli.landa.cmpt213.Model;

import com.eli.landa.cmpt213.Enums.ProgramEnum;

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
    private List<Observer> observers = new ArrayList<>();
    private CSVReader reader;
    private static List<Student> startingStudentList = new ArrayList<>();
    private FilterSettings filterSettings = new FilterSettings(ProgramEnum.CSMAJ, true, true, 2000, 2000);

    private DegreeCompletionVisualizerFacade() {
        studentManager = new StudentManager();
    }

    public FilterSettings getFilterSettings() {
        return filterSettings;
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

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void updateObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
