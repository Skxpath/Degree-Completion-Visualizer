package com.eli.landa.cmpt213.UI;

import javax.swing.*;
import java.awt.*;
/**
 * Created by Eli on 2017-04-03.
 */
public class FlowRectangle extends JPanel{
    public FlowRectangle() {
        setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        setBorder(BorderFactory.createLineBorder(Color.black, 5));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.weightx = .1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.fill = 1;
        add(new GenderDistributionRectangle(), gridBagConstraints);
        gridBagConstraints.gridy = 1;
        add(new GenderDistributionRectangle(), gridBagConstraints);
        gridBagConstraints.gridy = 2;
        add(new GenderDistributionRectangle(), gridBagConstraints);
    }
}
