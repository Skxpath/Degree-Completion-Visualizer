package com.eli.landa.cmpt213.UI;

import com.eli.landa.cmpt213.Model.DegreeCompletionVisualizerFacade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Eli on 2017-04-03.
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
                DegreeCompletionVisualizerFacade.getInstance().updateObservers();
            }
        });
    }




}
