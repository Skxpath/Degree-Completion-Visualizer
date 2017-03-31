package com.eli.landa.cmpt213.UI;

import com.eli.landa.cmpt213.Model.CSVReader;
import com.eli.landa.cmpt213.Model.DegreeCompletionVisualizerFacade;
import com.eli.landa.cmpt213.Model.ProgramEnum;

import java.io.File;
import java.util.List;

public class Main {

//Files are populated from a local directory in CSVReader.java line 192. This must be changed to fit the directory of the user.
    public static void main(String[] args) {

        DegreeCompletionVisualizerFacade model = DegreeCompletionVisualizerFacade.getInstance();
        CSVReader reader = new CSVReader();
        List<File> files = reader.populateFiles();
        model.setCSVReader(reader);

        for (int i = files.size() - 1; i >= 0; i--) {
            reader.readCSVFile(files.get(i));
        }


        ModelDumpText modelDumpText = new ModelDumpText(ProgramEnum.CSMAJ);

        modelDumpText.print();


    }
}
