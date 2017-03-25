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
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] csvInfo = line.split(cvsSplitBy);

                //this will be some kind of function for actually placing csv info into students or semester classes
                //storeCSVInfoToStudentManager(csvInfo,  , hashSet);

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
    private void storeCSVInfoToStudentManager(String[] csvInfo, Student student, HashSet hashSet){

    }
}
