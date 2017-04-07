package com.eli.landa.cmpt213.UI.Elements;

import com.eli.landa.cmpt213.Enums.ProgramEnum;
import com.eli.landa.cmpt213.Model.DegreeCompletionVisualizerFacade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * dropdown menu for sorting by programs
 */
public class SelectAProgramDropdownMenu extends JPanel {
    public SelectAProgramDropdownMenu() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.black,5));
        JLabel lbl = new JLabel("Select a Program");
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(lbl);

        String[] choices = { "CS Major", "CS SoSy", "CS Minor", "CS Joint Major",
                "Unknown"};

        final JComboBox<String> cb = new JComboBox<String>(choices);

        cb.setMaximumSize(cb.getPreferredSize());
        cb.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(cb);

        JButton btn = new JButton("Apply");
        btn.setAlignmentX(Component.LEFT_ALIGNMENT); // added code
        add(btn);
        btn.getModel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DegreeCompletionVisualizerFacade.getInstance().getFilterSettings().setProgramEnum(stringToProgramEnum(cb.getSelectedItem().toString()));
                DegreeCompletionVisualizerFacade.getInstance().updateObservers();
            }
        });
    }
    ProgramEnum stringToProgramEnum (String text){
        ProgramEnum programEnum = ProgramEnum.CSMAJ;
        switch (text){
            case "CS Major":
                programEnum = ProgramEnum.CSMAJ;
                break;
            case "CS SoSy":
                programEnum = ProgramEnum.SOSY;
                break;
            case "CS Minor":
                programEnum = ProgramEnum.CSMNR;
                break;
            case "CS Joint Major":
                programEnum = ProgramEnum.CSJNT;
                break;
            case "Unknown":
                programEnum = ProgramEnum.OTHER;
                break;
        }
      //  System.out.println(programEnum.toString());
        return programEnum;
    }




}
