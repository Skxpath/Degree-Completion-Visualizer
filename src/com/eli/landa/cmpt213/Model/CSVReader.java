package com.eli.landa.cmpt213.Model;

import java.io.*;

/**
 * Created by Eli on 2017-03-25.
 */
public class CSVReader {
    private BufferedReader br = null;
    private String line = "";
    private String cvsSplitBy = ",";

    //Instance of our facade class.
    private DegreeCompletionVisualizerFacade facade = DegreeCompletionVisualizerFacade.getInstance();

    private void readCSVFile(File csvFile) {//got help from -  https://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/

        try {
            //read in from file until its empty
            br = new BufferedReader(new FileReader(csvFile));
            line = br.readLine();
            while (line != null) {

                // use comma as separator
                String[] csvInfo = line.split(cvsSplitBy);
                //this will be some kind of function for actually placing csv info into students or semester classes
                storeCSVInfoToStudentManager(csvInfo);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //closes the file
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //this will be some kind of function for actually placing csv info into students or semester classes
    //You would parse students.csv first, followed by semesters, and finally programs.
    private void storeCSVInfoToStudentManager(String[] csvInfo) {
        //student# is always in the 0 column and all csv files have it
        int studentNumber = Integer.parseInt(csvInfo[0]);

        //check what kind of file we are reading
        if (csvInfo.length == 2) { //length of 2 indicated test_student.csv
            char gender = csvInfo[1].charAt(0);
            Student newStudent = new Student(studentNumber, gender);

            //Add new student to facade. For a student to exist in any other csv file, it must exist in this one first.
            addStudentToFacade(newStudent);

        } else if (csvInfo.length == 3) {//length of 3 indicates test_semester.csv

            int semesterVal = Integer.parseInt(csvInfo[1]);
            int yearVal = Integer.parseInt(csvInfo[2]);
            Semester semester = new Semester(semesterVal, yearVal);

            //Try catch block in case we cannot add a semester to student
            try {
                addSemesterToExistingStudent(studentNumber, semester); //Adds semester to existing student
            } catch (Exception e) {
                System.out.println("Failed to add semester to student with student number: " + studentNumber);
            }

        } else if (csvInfo.length == 4) {// length of 4 indications test_program.csv

            //Parses a semester value from csv into integer.
            int semesterVal = Integer.parseInt(csvInfo[1]);

            //Parses an action from csv, which is originally retrieved as a string
            //And converts it to an ActionEnum using StringToActionEnum class, before creating an Action.
            ActionEnum actionVal = StringToActionEnum.convert(csvInfo[2]);

            //Same logic as above for program.
            ProgramEnum program = StringToProgramEnum.convert(csvInfo[3]);

            Action action = new Action(actionVal, program);

            //Try catch block in case we cannot add action to semester
            try {
                addActionToExistingSemester(studentNumber, semesterVal, action); //Adds action to existing semester pertaining to existing student.
            } catch (Exception e) {
                System.out.println("Failed to add action to semester with semester number: " + semesterVal);
            }
        }
    }

    //Adds an action to an existing semester
    private void addActionToExistingSemester(int studentNumber, int semesterVal, Action action) {
        getExistingSemester(studentNumber, semesterVal).setAction(action);
    }

    //Adds a semester to an existing student
    private void addSemesterToExistingStudent(int studentNumber, Semester semester) {
        getExistingStudent(studentNumber).addSemester(semester);
    }

    //Function to add new student to the facade
    private void addStudentToFacade(Student newStudent) {
        facade.getStudentManager().addStudent(newStudent);
    }

    //Returns an existing student by student number
    private Student getExistingStudent(int studentNumber) {
        return facade.getStudentManager().getStudent(studentNumber);
    }

    //Returns an existing semester by student number and semesterVal
    private Semester getExistingSemester(int studentNumber, int semesterVal) {
        return getExistingStudent(studentNumber).getSemester(semesterVal);
    }
}




/*else {//if the student is already in our directories, do the following
            Student student = StudentManager.getStudent(studentNumber); //grab that student from the manager
            if (csvInfo.length == 2) {//set students gender
                char gender = csvInfo[1].charAt(0);
                student.setGender(gender);
            } else if (csvInfo.length == 3) {//set year of study and add a semester if there isnt already one
                int semesterVal = Integer.parseInt(csvInfo[1]);
                int yearVal = Integer.parseInt(csvInfo[2]);
                student.setYearsOfStudy(yearVal);
                if (!student.hasSemester(semesterVal)) {//checks if there is already a semester with this semester value
                    Semester semester = new Semester(semesterVal);
                    student.addSemester(semester);
                }

            } else if (csvInfo.length == 4) {//either add a new semester to the student or edit an existing one by adding an action to it
                int semesterVal = Integer.parseInt(csvInfo[1]);
                String actionVal = csvInfo[2];
                String program = csvInfo[3];
                Action action = new Action(actionVal, program);
                Semester semester;
                if (student.hasSemester(semesterVal)) {
                    semester = student.getSemester(semesterVal);
                    semester.setAction(action);
                } else {
                    semester = new Semester(semesterVal, action);
                }
            }
        }*/