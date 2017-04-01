package com.eli.landa.cmpt213.Model;

import com.eli.landa.cmpt213.Enums.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;

/**
 * Created by Eli on 2017-03-25.
 *
 * CSVreader class
 */
public class CSVReader {
    public static final int noYearValue = 0;
    public static final int admittedYearValue = 1;
    public static final int graduatedYearValue = 8;
    private BufferedReader br = null;
    private String line = "";
    private String cvsSplitBy = ",";
    private FileEnum fileEnum;
    //Instance of our facade class.
    private static DegreeCompletionVisualizerFacade facade = DegreeCompletionVisualizerFacade.getInstance();

    public void readCSVFile(File csvFile) {//got help from -  https://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/

        try {
            //read in from file until its empty
            br = new BufferedReader(new FileReader(csvFile));
            line = br.readLine();
            String[] csvInfo = line.split(cvsSplitBy);
            //check the size of the FIRST row which has the column names
            //use this size to determine what file we are looking ar
            if (csvInfo.length == 2) {
                fileEnum = FileEnum.TEST_STUDENTS;
            } else if (csvInfo.length == 3) {
                fileEnum = FileEnum.TEST_SEMESTERS;
            } else if (csvInfo.length == 4) {
                fileEnum = FileEnum.TEST_PROGRAMS;
            }
            line = br.readLine();
            while (line != null) {
                // use comma as separator
                csvInfo = line.split(cvsSplitBy);
                if (csvInfo.length == 2) {

                }
                //this will be some kind of function for actually placing csv info into students or semester classes
                storeCSVInfoToStudentManager(csvInfo, fileEnum);
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
    private void storeCSVInfoToStudentManager(String[] csvInfo, FileEnum fileEnum) {
        //student# is always in the 0 column and all csv files have it
        int studentNumber = Integer.parseInt(csvInfo[0]);

        //check what kind of file we are reading
        if (fileEnum.equals(FileEnum.TEST_STUDENTS)) { //length of 2 indicated test_student.csv
            char gender = csvInfo[1].charAt(1);

            GenderEnum genderEnum = GenderEnum.UNKNOWN;

            switch (gender) {
                case 'M':
                    genderEnum = GenderEnum.MALE;
                    break;
                case 'F':
                    genderEnum = GenderEnum.FEMALE;
                    break;
                case 'U':
                    genderEnum = GenderEnum.UNKNOWN;
                    break;
                default:
                    System.out.println("gender enum failed to populate in csv");
                    //This should never happe
            }

            Student newStudent = new Student(studentNumber, genderEnum);
            //Add new student to facade. For a student to exist in any other csv file, it must exist in this one first.
            addStudentToFacade(newStudent);

        } else if (fileEnum.equals(FileEnum.TEST_SEMESTERS)) {//length of 3 indicates test_semester.csv

            int semesterVal = Integer.parseInt(csvInfo[1]);

            int yearVal = Integer.parseInt(String.valueOf(csvInfo[2].charAt(1)));

            YearEnum yearEnum = YearEnum.ADMITTED;

            switch (yearVal) {
                case 1:
                    yearEnum = YearEnum.FIRST_YEAR;
                    break;
                case 2:
                    yearEnum = YearEnum.FIRST_YEAR;
                    break;
                case 3:
                    yearEnum = YearEnum.SECOND_YEAR;
                    break;
                case 4:
                    yearEnum = YearEnum.SECOND_YEAR;
                    break;
                case 5:
                    yearEnum = YearEnum.THIRD_YEAR;
                    break;
                case 6:
                    yearEnum = YearEnum.THIRD_YEAR;
                    break;
                case 7:
                    yearEnum = YearEnum.FOURTH_YEAR;
                    break;
                case 8:
                    yearEnum = YearEnum.FOURTH_YEAR;
                    break;
                default:
                    break; //should never happen
            }

            Semester semester = new Semester(semesterVal, yearEnum);

            //Try catch block in case we cannot add a semester to student
            try {
                addSemesterToExistingStudent(studentNumber, semester); //Adds semester to existing student
            } catch (Exception e) {
                System.out.println("Failed to add action to semester with semester number: " + semesterVal + " Student Number: " + studentNumber + " yearVal: " + yearVal);
            }

        } else if (fileEnum.equals(FileEnum.TEST_PROGRAMS)) {// length of 4 indications test_program.csv

            //Parses a semester value from csv into integer.
            int semesterVal = Integer.parseInt(csvInfo[1]);

            //Parses an action from csv, which is originally retrieved as a string
            //And converts it to an ActionEnum using StringToActionEnum class, before creating an Action.
            ActionEnum actionVal = StringToActionEnum.convert(csvInfo[2]);

            //Same logic as above for program.
            ProgramEnum program;
            if (actionVal.equals(ActionEnum.DROPOUT)) {
                program = ProgramEnum.NO_PROGRAM;
            } else {
                program = StringToProgramEnum.convert(csvInfo[3]);
            }


            Action action = new Action(actionVal, program);

            //Try catch block in case we cannot add action to semester
            try {
                addActionToExistingSemester(studentNumber, semesterVal, action); //Adds action to existing semester pertaining to existing student.
            } catch (Exception e) {
                System.out.println("Failed to add action to semester with semester number: " + semesterVal + " Student Number: " + studentNumber + " action: " + action);
            }
        }
    }

    //Adds an action to an existing semester
    private void addActionToExistingSemester(int studentNumber, int semesterVal, Action action) {
        //check if semester exists. if yes, change action this.
        if (facade.getStudentManager().getStudent(studentNumber).getSemester(semesterVal) == null) {


            Semester semester = new Semester(semesterVal, YearEnum.ADMITTED);
            facade.getStudentManager().getStudent(studentNumber).addSemester(semester);

            NavigableMap list = facade.getStudentManager().getStudent(studentNumber).getSemesters();
            Map.Entry<Integer, Semester> prev = list.lowerEntry(semesterVal);

            switch (action.getSemesterAction()) {

                case ADMT:
                    semester.setYearValue(YearEnum.ADMITTED);
                    semester.addAction(action);
                    break;
                case ADD:
                    semester.setYearValue(prev.getValue().getYearEnum()); //Gets the year value of the semester right before this one
                    semester.addAction(action);
                    break;
                case FIN:
                    semester.setYearValue(YearEnum.GRADUATED);
                    semester.addAction(action);
                    break;
                case DROPOUT:
                    semester.setYearValue(prev.getValue().getYearEnum());
                    semester.addAction(action);
                    break;
                case NO_ACTION:
                    System.out.println("No this should never happen - switchstatenment addactiontoexistjhgfdjgdf");
                    break;
            }
            // in the case where action = add/drop. set to 8 if fin, 1 if admt
        } else {
            //else make a new semester, put into treemap
            //depending on the action, set level accordingly

            Semester existingSemester =   getExistingSemester(studentNumber, semesterVal);
          existingSemester.addAction(action);

          if (action.getSemesterAction().equals(ActionEnum.ADMT)) {
                existingSemester.setYearValue(YearEnum.ADMITTED);
            } else if (action.getSemesterAction().equals(ActionEnum.FIN)) {
              existingSemester.setYearValue(YearEnum.GRADUATED);
          }

        }
    }

    //Adds a semester to an existing student
    private void addSemesterToExistingStudent(int studentNumber, Semester semester) {
        Student student = getExistingStudent(studentNumber);
        student.addSemester(semester);
    }

    //Function to add new student to the facade
    private void addStudentToFacade(Student newStudent) {
        facade.getStudentManager().addStudent(newStudent);
    }

    //Returns an existing student by student number
    private Student getExistingStudent(int studentNumber) {
        DegreeCompletionVisualizerFacade model = DegreeCompletionVisualizerFacade.getInstance();

        Student student = facade.getStudentManager().getStudent(studentNumber);

        return student;
    }

    //Returns an existing semester by student number and semesterVal
    private Semester getExistingSemester(int studentNumber, int semesterVal) {

        Semester semester = getExistingStudent(studentNumber).getSemester(semesterVal);

        return semester;
    }

    public List<File> populateFiles() {
        List<File> files = new ArrayList<>();
        File directory = new File("C:\\Users\\Eli\\IdeaProjects\\Degree-Completion-Visualizer-use-this\\src\\com\\eli\\landa\\cmpt213\\Data");
        int AMOUNT_OF_FILES_IN_DIRECTORY = directory.listFiles().length;
        for (int i = 0; i < AMOUNT_OF_FILES_IN_DIRECTORY; i++) {
            files.add(directory.listFiles()[i]);
        }
        return files;
    }
}




