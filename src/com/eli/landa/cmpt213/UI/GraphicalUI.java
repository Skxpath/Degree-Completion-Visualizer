package com.eli.landa.cmpt213.UI;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
/* GraphicalUI class to display data dump to the console
* */
public class GraphicalUI {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Degree Completion Visualizer");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 500);
        frame.setLocation(0, 0);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        setupTopPanels(frame, gridBagConstraints);
        setupMiddlePanels(frame, gridBagConstraints);
        //setupBottomPanels(frame);


        frame.setVisible(true); // added code

    }

    private static void setupBottomPanels(JFrame frame, GridBagConstraints gridBagConstraints) {
        List<JPanel> bottomPanels = new ArrayList<>();
        bottomPanels.add(new FilterStudentsCheckBoxAndEditTexts());
        JPanel containerBottom = new Container(bottomPanels);
        frame.add(containerBottom);
    }

    private static void setupMiddlePanels(JFrame frame, GridBagConstraints gridBagConstraints) {
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        frame.add(new FlowRectangle(), gridBagConstraints);
        gridBagConstraints.gridx = 1;
        frame.add(new YearRectangle(), gridBagConstraints);
    }

    private static void setupTopPanels(JFrame frame, GridBagConstraints gridBagConstraints) {
        List<JPanel> topPanels = new ArrayList<>();

        topPanels.add(new SelectAProgramDropdownMenu());
        topPanels.add(new FilterStudentsCheckBoxAndEditTexts());
        topPanels.get(0).setPreferredSize(new Dimension(130,130));
        topPanels.get(1).setPreferredSize(new Dimension(400,130));
        JPanel containerTop = new Container(topPanels);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        frame.add(containerTop, gridBagConstraints);
        frame.getComponent(0).setLocation(0,0);
    }
}
