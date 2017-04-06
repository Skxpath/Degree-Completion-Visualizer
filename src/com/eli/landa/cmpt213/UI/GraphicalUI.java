package com.eli.landa.cmpt213.UI;

import com.eli.landa.cmpt213.Model.CSVReader;
import com.eli.landa.cmpt213.Model.DegreeCompletionVisualizerFacade;
import com.eli.landa.cmpt213.UI.Containers.FlowAndYearsContainer;
import com.eli.landa.cmpt213.UI.Elements.FilterStudentsCheckBoxAndEditTexts;
import com.eli.landa.cmpt213.UI.Elements.SelectAProgramDropdownMenu;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
/* GraphicalUI class to display data dump to the console
* */
public class GraphicalUI {
    private static DegreeCompletionVisualizerFacade model = DegreeCompletionVisualizerFacade.getInstance();
    public static void main(String[] args) {
        CSVReader reader = new CSVReader();
        List<File> files = reader.populateFiles();
        model.setCSVReader(reader);

        for (int i = files.size() - 1; i >= 0; i--) {
            reader.readCSVFile(files.get(i));
        }

        model.getStudentManager().populateProgramsInStudentSemesters();

     //   FilterList filterList = new FilterList();
        JFrame frame = new JFrame("Degree Completion Visualizer");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.setLocation(0, 0);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = 1;
        setupMiddlePanels(frame, gridBagConstraints);
        setupTopPanels(frame, gridBagConstraints);

        //setupBottomPanels(frame);


        frame.setVisible(true); // added code

    }

    private static void setupBottomPanels(JFrame frame, GridBagConstraints gridBagConstraints) {
        List<JPanel> bottomPanels = new ArrayList<>();
        bottomPanels.add(new FilterStudentsCheckBoxAndEditTexts());
        JPanel containerBottom = new com.eli.landa.cmpt213.UI.Containers.Container(bottomPanels);
        frame.add(containerBottom);
    }

    private static void setupMiddlePanels(JFrame frame, GridBagConstraints gridBagConstraints) {
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.gridy = 1;
        frame.add(new FlowAndYearsContainer(), gridBagConstraints);
    }

    private static void setupTopPanels(JFrame frame, GridBagConstraints gridBagConstraints) {
        List<JPanel> topPanels = new ArrayList<>();

        topPanels.add(new SelectAProgramDropdownMenu());
        topPanels.add(new FilterStudentsCheckBoxAndEditTexts());
        topPanels.get(0).setPreferredSize(new Dimension(130,130));
        topPanels.get(1).setPreferredSize(new Dimension(400,130));
        JPanel containerTop = new com.eli.landa.cmpt213.UI.Containers.Container(topPanels);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = .1;
        frame.add(containerTop, gridBagConstraints);
    }
    static void setupYearFlow(List<JPanel> panels, JFrame frame, GridBagConstraints gridBagConstraints){
        for (JPanel panel: panels) {
            frame.add(panel, gridBagConstraints);
            gridBagConstraints.gridx++;
        }
    }
}
