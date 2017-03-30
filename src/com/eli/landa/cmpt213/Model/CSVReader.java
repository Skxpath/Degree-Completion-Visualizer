package com.eli.landa.cmpt213.Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;

/**
 * Created by Eli on 2017-03-25.
 */
public class CSVReader {
    private BufferedReader br = null;
    private String line = "";
    private String cvsSplitBy = ",";

    //Instance of our facade class.
    private static DegreeCompletionVisualizerFacade facade = DegreeCompletionVisualizerFacade.getInstance();

    public void readCSVFile(File csvFile) {//got help from -  https://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/

        try {
            //read in from file until its empty
            br = new BufferedReader(new FileReader(csvFile));
            line = br.readLine();
            line = br.readLine();
            while (line != null) {
                // System.out.println("test");
                // use comma as separator
                String[] csvInfo = line.split(cvsSplitBy);
                //this will be some kind of function for actually placing csv info into students or semester classes
                storeCSVInfoToStudentManager(csvInfo);
                line = br.readLine();

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
            char gender = csvInfo[1].charAt(1);
            Student newStudent = new Student(studentNumber, gender);
            //Add new student to facade. For a student to exist in any other csv file, it must exist in this one first.
            addStudentToFacade(newStudent);

        } else if (csvInfo.length == 3) {//length of 3 indicates test_semester.csv

            int semesterVal = Integer.parseInt(csvInfo[1]);
            int yearVal = Integer.parseInt(String.valueOf(csvInfo[2].charAt(1)));
            Semester semester = new Semester(semesterVal, yearVal);

            //Try catch block in case we cannot add a semester to student
            try {
                addSemesterToExistingStudent(studentNumber, semester); //Adds semester to existing student
            } catch (Exception e) {
                System.out.println("Failed to add action to semester with semester number: " + semesterVal + " Student Number: " + studentNumber + " yearVal: " + yearVal);
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
                // System.out.println("current student: " + semesterVal + " Student Number: " + studentNumber + " action: " + action);

                addActionToExistingSemester(studentNumber, semesterVal, action); //Adds action to existing semester pertaining to existing student.
            } catch (Exception e) {
                System.out.println("Failed to add action to semester with semester number: " + semesterVal + " Student Number: " + studentNumber + " action: " + action);
            }
        }
    }

    //Adds an action to an existing semester
    private void addActionToExistingSemester(int studentNumber, int semesterVal, Action action) {
        //System.out.println("added action to existing semester");
        //check if semester exists. if yes, change action this.
        if (facade.getStudentManager().getStudent(studentNumber).getSemester(semesterVal) == null) {

            Semester test = new Semester(semesterVal, 0);
            facade.getStudentManager().getStudent(studentNumber).addSemester(test);

            NavigableMap list = facade.getStudentManager().getStudent(studentNumber).getSemesters();
            Map.Entry<Integer, Semester> prev = list.lowerEntry(semesterVal);

            switch (action.getSemesterAction()) {

                case ADMT:
                    test.setYearVal(1);
                    test.setAction(action);
                    break;
                case ADD:
                    test.setYearVal(prev.getValue().getYearVal());
                    test.setAction(action);
                    break;
                case FIN:
                    test.setYearVal(8);
                    test.setAction(action);
                    break;
                case DROPOUT:
                    test.setYearVal(prev.getValue().getYearVal());
                    test.setAction(action);
                    break;
                case NO_ACTION:
                    System.out.println("No this should never happen - switchstatenment addactiontoexistjhgfdjgdf");
                    break;
            }
            // in the case where action = add/drop. set to 8 if fin, 1 if admt
        } else {
            //else make a new semester, put into treemap
            //depending on the action, set level accordingly

            getExistingSemester(studentNumber, semesterVal).setAction(action);
        }
    }

    //Adds a semester to an existing student
    private void addSemesterToExistingStudent(int studentNumber, Semester semester) {
        Student student = getExistingStudent(studentNumber);
        if (student == null) {
            System.out.println("Student is null");
        }
        // System.out.println(semester.toString());
        student.addSemester(semester);
    }

    //Function to add new student to the facade
    private void addStudentToFacade(Student newStudent) {
        facade.getStudentManager().addStudent(newStudent);
    }

    //Returns an existing student by student number
    private Student getExistingStudent(int studentNumber) {
        //System.out.println("got existing student");
        DegreeCompletionVisualizerFacade model = DegreeCompletionVisualizerFacade.getInstance();
        if (facade.getStudentManager() == null) {
            System.out.println("manager is null!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
        Student student = facade.getStudentManager().getStudent(studentNumber);
        if (student == null) {
            // System.out.println("student is null at the source look at student code");
        }
        return student;
    }

    //Returns an existing semester by student number and semesterVal
    private Semester getExistingSemester(int studentNumber, int semesterVal) {
        // System.out.println("got existing semester");
        Semester semester = getExistingStudent(studentNumber).getSemester(semesterVal);
        System.out.println(semester.getYearVal() + "");
        return semester;
    }

    public List<File> populateFiles() {
        List<File> files = new ArrayList<>();
        File directory = new File("C:\\Users\\Aria\\IdeaProjects\\Degree-Completion-Visualizer\\src\\com\\eli\\landa\\cmpt213\\Data");
        int AMOUNT_OF_FILES_IN_DIRECTORY = directory.listFiles().length;
        for (int i = 0; i < AMOUNT_OF_FILES_IN_DIRECTORY; i++) {
            files.add(directory.listFiles()[i]);
        }
        return files;
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