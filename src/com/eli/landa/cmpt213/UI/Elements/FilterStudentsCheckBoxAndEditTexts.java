package com.eli.landa.cmpt213.UI.Elements;

import com.eli.landa.cmpt213.Model.DegreeCompletionVisualizerFacade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Eli on 2017-04-03.
 */
public class FilterStudentsCheckBoxAndEditTexts extends JPanel{
    public static boolean inSelectedProgramInYear = true;
    public static boolean graduatedInSelectedProgramInYear = true;
    public static int year1 = 0;
    public static int year2 = 0;
    public FilterStudentsCheckBoxAndEditTexts () {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.black, 5));

        JLabel lbl = new JLabel("Filter Students:");
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(lbl);


        List<JPanel> topPanel = new ArrayList<>();

        CheckBox inSelectedProgramInYearBox = new CheckBox("In selected program in the year", inSelectedProgramInYear);
        topPanel.add(inSelectedProgramInYearBox);
        topPanel.get(0).setPreferredSize(new Dimension(300,30));

        topPanel.add(new TextBox());
        topPanel.get(1).setPreferredSize(new Dimension(80,30));

        add(new com.eli.landa.cmpt213.UI.Containers.Container(topPanel));

        List<JPanel> bottomPanel = new ArrayList<>();

        CheckBox graduatedSelectedProgramInYearBox = new CheckBox("Graduated selected program in the year", graduatedInSelectedProgramInYear);
        bottomPanel.add(graduatedSelectedProgramInYearBox);
        bottomPanel.get(0).setPreferredSize(new Dimension(300,30));

        bottomPanel.add(new TextBox());
        bottomPanel.get(1).setPreferredSize(new Dimension(80,30));

        add(new com.eli.landa.cmpt213.UI.Containers.Container(bottomPanel));

        JButton btn = new JButton("Apply");
        btn.setAlignmentX(Component.LEFT_ALIGNMENT); // added code
        add(btn);
        btn.getModel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DegreeCompletionVisualizerFacade.getInstance().getFilterSettings().setInSelectedProgramInYear(inSelectedProgramInYear);
                DegreeCompletionVisualizerFacade.getInstance().getFilterSettings().setGraduatedSelectedProgramInYear(graduatedInSelectedProgramInYear);
                DegreeCompletionVisualizerFacade.getInstance().updateObservers();
            }
        });
        add(btn);
    }
}
