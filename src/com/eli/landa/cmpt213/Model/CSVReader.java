package com.eli.landa.cmpt213.Model;

import java.io.*;
import java.util.HashSet;

/**
 * Created by Eli on 2017-03-25.
 */
public class CSVReader {
    private BufferedReader br = null;
    private String line = "";
    private String cvsSplitBy = ",";
    private void readCSVFile(File csvFile) {//got help from -  https://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/

        //made a hash set, not sure what for yet, but i think we will need it
        HashSet hashSet = new HashSet();
        try {
            //read in from file until its empty
            br = new BufferedReader(new FileReader(csvFile));
            line = br.readLine();
            while (line != null) {

                // use comma as separator
                String[] csvInfo = line.split(cvsSplitBy);
                //this will be some kind of function for actually placing csv info into students or semester classes
                storeCSVInfoToStudentManager(csvInfo, hashSet);

            }

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
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
    private void storeCSVInfoToStudentManager(String[] csvInfo, HashSet hashSet){
        //student# is always in the 0 column and all csv files have it
        int studentNumber = Integer.parseInt(csvInfo[0]);

        //check if student is already in out directory based on their student number, if they are not, do the following
        if(!StudentManager.hasStudent(studentNumber)){

            //create new student
            Student newStudent = new Student(studentNumber);

            //check what kind of file we are reading
            if (csvInfo.length == 2){ //length of 2 indicated test_student.csv
                char gender = csvInfo[1].charAt(0);
                newStudent = new Student(studentNumber,gender);
            }
            else if (csvInfo.length == 3){//length of 3 indicates test_semester.csv
                int semesterVal = Integer.parseInt(csvInfo[1]);
                int yearVal = Integer.parseInt(csvInfo[2]);
                Semester semester = new Semester(semesterVal);
                newStudent = new Student(studentNumber, yearVal, semester);
            }
            else if (csvInfo.length == 4){// length of 4 indications test_program.csv
                int semesterVal = Integer.parseInt(csvInfo[1]);
                String actionVal = csvInfo[2];
                String program = csvInfo[3];
                Action action = new Action(actionVal, program);
                Semester semester = new Semester(semesterVal, action);
                newStudent = new Student(studentNumber, semester);
            }
            StudentManager.addStudent(newStudent); //add the student we generated to our list of students
        }
        else {//if the student is already in our directories, do the following
            Student student = StudentManager.getStudent(studentNumber); //grab that student from the manager
            if (csvInfo.length == 2){//set students gender
                char gender = csvInfo[1].charAt(0);
                student.setGender(gender);
            }
            else if (csvInfo.length == 3){//set year of study and add a semester if there isnt already one
                int semesterVal = Integer.parseInt(csvInfo[1]);
                int yearVal = Integer.parseInt(csvInfo[2]);
                student.setYearsOfStudy(yearVal);
                if(!student.hasSemester(semesterVal)){//checks if there is already a semester with this semester value
                    Semester semester = new Semester(semesterVal);
                    student.addSemester(semester);
                }

            }
            else if (csvInfo.length == 4){//either add a new semester to the student or edit an existing one by adding an action to it
                int semesterVal = Integer.parseInt(csvInfo[1]);
                String actionVal = csvInfo[2];
                String program = csvInfo[3];
                Action action = new Action(actionVal, program);
                Semester semester;
                if(student.hasSemester(semesterVal)){
                    semester = student.getSemester(semesterVal);
                    semester.setAction(action);
                }
                else {
                    semester = new Semester(semesterVal, action);
                }
            }
        }

    }
}
