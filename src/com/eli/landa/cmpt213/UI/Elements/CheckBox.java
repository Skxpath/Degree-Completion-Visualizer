package com.eli.landa.cmpt213.UI.Elements;

import javax.swing.*;

/**
 * Can be ticked or unticked
 */
public class CheckBox extends JPanel {
    public CheckBox(String text, boolean isTicked) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JCheckBox inSelectedProgramCheckbox = new JCheckBox(text);
        inSelectedProgramCheckbox.setSelected(true);
        if(inSelectedProgramCheckbox.isSelected()) {
            isTicked = true;
        }
        else {
            isTicked = false;
        }
        add(inSelectedProgramCheckbox);
    }
}
