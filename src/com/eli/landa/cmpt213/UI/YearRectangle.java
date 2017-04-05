package com.eli.landa.cmpt213.UI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Eli on 2017-04-05.
 */
public class YearRectangle extends JPanel{
    public YearRectangle() {
        setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        setBorder(BorderFactory.createLineBorder(Color.black, 5));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.weightx = .1;
        gridBagConstraints.weighty = 1;

        JLabel lbl = new JLabel("CS Major");
        add(lbl, gridBagConstraints);

        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = 1;
        add(new GenderDistributionRectangle(), gridBagConstraints);

        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = 0;
        JLabel amount = new JLabel("150");
        add(amount, gridBagConstraints);

        gridBagConstraints.gridy = 3;
        JLabel year = new JLabel("Admitted");
        add(year, gridBagConstraints);
        setPreferredSize(new Dimension(100,200));
    }
}
