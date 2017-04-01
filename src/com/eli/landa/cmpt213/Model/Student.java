package com.eli.landa.cmpt213.Model;

import com.eli.landa.cmpt213.Enums.ActionEnum;
import com.eli.landa.cmpt213.Enums.GenderEnum;
import com.eli.landa.cmpt213.Enums.YearEnum;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * Created by Eli on 2017-03-25.
 *
 * Student Class that stores an object for each student.
 *
 */

public class Student {
    private int studentNumber;
    private GenderEnum gender; // Male, Female, Unknown
    private NavigableMap<Integer, Semester> semesters = new TreeMap<>(); //new tree map sorted by semester code. The natural order.

    public Student(int studentNumber, GenderEnum gender) {
        this.studentNumber = studentNumber;
        this.gender = gender;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public NavigableMap getSemesters() { //Returns the semester TreeMap
        return semesters;
    }

    public Semester getSemester(int semesterVal) {
        if (semesters.containsKey(semesterVal)) { //Checks through the treemap if it contains a semester with the semester val. If true, return this.

            return semesters.get(semesterVal);

        }
        return null;
    }

    //Adds semester to the treemap, ordered naturally by semester code.
    public void addSemester(Semester semester) {

        semesters.put(semester.getSemesterCode(), semester);
        semesters.get(semester.getSemesterCode());

    }
    public Semester getLastSemesterInAYear (YearEnum yearEnum){
        //list of semesters in a given year
        if(hasSemesters()) {
            NavigableMap<Integer, Semester> semestersinAGivenYear = new TreeMap<>();

            boolean hasYear = false;

            for (Map.Entry<Integer, Semester> semester : semesters.entrySet()) {
                if (semester.getValue().getYearEnum().equals(yearEnum)) {
                    semestersinAGivenYear.put(semester.getKey(), semester.getValue());
                    hasYear = true;
                }
            }


            //checks to make sure there are years occuring after your final semester in the given year
            //if there are, then you didnt drop out mid year
            //if its true, return the final value in the semestersInAGivenYear (this semester is your last semester in a given year since the map is ordered)
           if (hasYear) {
                if (!semestersinAGivenYear.lastEntry().getValue().getListOfActions().isEmpty()) {
                    if (semestersinAGivenYear.lastEntry().getValue().getListOfActions().get(0).equals(ActionEnum.FIN) || semestersinAGivenYear.lastEntry().getValue().getListOfActions().get(0).equals(ActionEnum.DROPOUT)) {
                        return semestersinAGivenYear.lastEntry().getValue();
                    }
                }
             //  System.out.println(semestersinAGivenYear.lastEntry().getValue().getProgram());
               return semestersinAGivenYear.lastEntry().getValue();
           }
            else {
                return null;
            }
        }
        return null;
    }
    public boolean hasSemesters () {
        if(semesters.size() > 0){
            return true;
        }
        return false;
    }
    public NavigableMap getRemoveSemesters() { //Returns the semester TreeMap
        NavigableMap<Integer, Semester> removeSemesters = new TreeMap<>();
        if(semesters.size() > 0) {

            for (Map.Entry<Integer, Semester> semester : semesters.entrySet()) {

                    if (!semester.getValue().getListOfActions().isEmpty()) {

                        if (semester.getValue().getListOfActions().get(0).getSemesterAction().equals(ActionEnum.REM)) {
                            removeSemesters.put(semester.getKey(), semester.getValue());
                        }
                    }
                }
            }

        return removeSemesters;
    }
    public NavigableMap getAddSemesters() { //Returns the semester TreeMap
        NavigableMap<Integer, Semester> addSemesters = new TreeMap<>();
        for (Map.Entry<Integer, Semester> semester : semesters.entrySet()) {
            if (semester.getValue().getListOfActions().get(0).equals(ActionEnum.ADD)) {
                addSemesters.put(semester.getKey(), semester.getValue());
            }
        }
        return addSemesters;
    }
    public Semester lastRemoveInAGivenYear (YearEnum yearEnum){
        //list of all semesters with REM action
        NavigableMap<Integer, Semester> removeSemesters = new TreeMap<>();

        //list of all semesters with REM action that also happens to be on the given year
        NavigableMap<Integer, Semester> removeSemestersInTheGivenYear = new TreeMap<>();

        //sets it to list of all semesters with REM action
        removeSemesters = getRemoveSemesters();

        //iterate
        for (Map.Entry<Integer, Semester> semester : removeSemesters.entrySet()) {
            //if the list of all semesters with the REM action has a semester that happens on the given year, put that semester into the removeSemestersInTheGivenYear map
            if (semester.getValue().getYearEnum().equals(yearEnum)){
                removeSemestersInTheGivenYear.put(semester.getKey(),semester.getValue());
            }
        }
        //return the final entry in removeSemestersInTheGivenYear map
        if (!removeSemestersInTheGivenYear.isEmpty()) {
            return removeSemestersInTheGivenYear.lastEntry().getValue();
        } else {
            return null;
        }

    }
    @Override
    public String toString() {
        return ("Student Number: " + studentNumber + " Gender: " + gender + " Semesters List Size: " + semesters.size());
    }
}
