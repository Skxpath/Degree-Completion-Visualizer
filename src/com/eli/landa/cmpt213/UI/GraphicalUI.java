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

        setupTopPanels(frame);
        setupMiddlePanels(frame);
        setupBottomPanels(frame);


        frame.setVisible(true); // added code

    }

    private static void setupBottomPanels(JFrame frame) {
        List<JPanel> bottomPanels = new ArrayList<>();
        bottomPanels.add(new FilterStudentsCheckBoxAndEditTexts());
        JPanel containerBottom = new Container(bottomPanels);
        frame.add(containerBottom, BorderLayout.SOUTH);
    }

    private static void setupMiddlePanels(JFrame frame) {
        List<JPanel> midPanels = new ArrayList<>();
        midPanels.add(new GenderDistributionRectangle());
        JPanel containerMiddle = new Container(midPanels);
        frame.add(containerMiddle, BorderLayout.CENTER);
    }

    private static void setupTopPanels(JFrame frame) {
        List<JPanel> topPanels = new ArrayList<>();

        topPanels.add(new SelectAProgramDropdownMenu());
        topPanels.add(new FilterStudentsCheckBoxAndEditTexts());
        topPanels.add(new JPanel());

        JPanel containerTop = new Container(topPanels);
        frame.add(containerTop, BorderLayout.NORTH);
    }
}
