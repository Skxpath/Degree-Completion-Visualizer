package com.eli.landa.cmpt213.UI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eli on 2017-04-03.
 */
public class GenderDistributionRectangle extends JPanel {
    final int height = 20;
    public GenderDistributionRectangle () {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.black, 5));

        List<JPanel> genderPanels = new ArrayList<>();

        Rectangle boysRect = new Rectangle(25, height, Color.blue);
        Rectangle girlsRect = new Rectangle(25, height, Color.RED);
        Rectangle unknownRect = new Rectangle(25, height, Color.GRAY);

        genderPanels.add(boysRect);
        genderPanels.add(unknownRect);
        genderPanels.add(girlsRect);
        Container container = new Container(genderPanels);
        add(container);
    }
}
