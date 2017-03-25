package com.eli.landa.cmpt213.Model;

import java.util.List;

/**
 * Created by Eli on 2017-03-25.
 */
public class Student {
    private long studentNumber;
    private char gender; // M, F, U
    private List<Integer> yearsOfStudy;


    //lets make this a class like Semester or something and each semester will have an action, semester number and program associated with it. this will make indexing easier i think
    private List<Integer> semesterCodes; // four digit SFU semester number code of form ZZZS, where the year is1900 + ZZZ, and the semester S is one of {1=Spring, 4=Summer, 7=Fall}.
    private List<String> actions; // admt, add, fin, dropout
    private List<String> program; // program associated with the action
}
