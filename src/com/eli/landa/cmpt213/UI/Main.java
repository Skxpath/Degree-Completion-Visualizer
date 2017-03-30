package com.eli.landa.cmpt213.UI;

import com.eli.landa.cmpt213.Model.*;
import com.sun.org.apache.bcel.internal.generic.MONITORENTER;

import java.io.File;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        DegreeCompletionVisualizerFacade model = DegreeCompletionVisualizerFacade.getInstance();
        CSVReader reader = new CSVReader();
        List<File> files = reader.populateFiles();
        model.setCSVReader(reader);
        //reader.readCSVFile(files.get(files.size()-1));
        for (int i = files.size()-1; i >=0; i--) {
              reader.readCSVFile(files.get(i));
        }

//       System.out.println(model.getStudentManager().getStudent(326209703).getSemesters().size());
        StudentManager studentManager = model.getStudentManager();
        List<Student> students = studentManager.getStudents();
        /*for (Student student: students) {
            System.out.println(student.toString());
        }*/

     //   System.out.println(studentManager.getStudent(326209703).getSemester(1117).getAction().toString());

        SemesterFilter testsemfilter = new SemesterFilter();

       List testlist = testsemfilter.generateList(model.getStudentManager().getStudents(), SemesterEnum.ADMITTED);

       System.out.println(testlist);
      System.out.println(testlist.size());

      /*  326209703,1117,admt,CSMAJ
        326209703,1154,fin,CSMAJ*/
        // write your code here
    }
}
