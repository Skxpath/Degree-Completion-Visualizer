package com.eli.landa.cmpt213.UI.Elements;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Eli on 2017-04-03.
 */
public class GenderDistributionRectangle extends JPanel {
    final int height = 20;
    public GenderDistributionRectangle (float boysPercent, float girlsPercent, float unknownPercent) {
        setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        setBorder(BorderFactory.createLineBorder(Color.black, 5));

        Rectangle boysRect = new Rectangle(25, height, Color.blue);
        Rectangle girlsRect = new Rectangle(25, height, Color.RED);
        Rectangle unknownRect = new Rectangle(25, height, Color.GRAY);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.weightx = boysPercent;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.fill = 1;
        add(boysRect, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.weightx = unknownPercent;
        gridBagConstraints.weighty = 1;
        add(unknownRect, gridBagConstraints);

        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.weightx = girlsPercent;
        gridBagConstraints.weighty = 1;
        add(girlsRect, gridBagConstraints);
        setPreferredSize(new Dimension(100,30));
    }
}
