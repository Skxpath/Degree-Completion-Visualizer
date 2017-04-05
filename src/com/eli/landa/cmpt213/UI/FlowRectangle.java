package com.eli.landa.cmpt213.UI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Eli on 2017-04-03.
 */
public class FlowRectangle extends JPanel{
    public FlowRectangle() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.black, 5));
        List<JPanel> panels = new ArrayList<>();
        panels.add(new GenderDistributionRectangle());
        panels.add(new GenderDistributionRectangle());
        panels.add(new GenderDistributionRectangle());
        for (JPanel panel: panels) {
            add(panel);
        }
    }
}
