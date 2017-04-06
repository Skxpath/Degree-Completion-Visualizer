package com.eli.landa.cmpt213.UI.Elements;

import javax.swing.*;
import java.awt.*;
import java.util.List;
/**
 * Created by Eli on 2017-04-03.
 */
public class GenderDistributionRectangle extends JPanel{
    final int height = 20;
    float boysPercent = 0;
    float girlsPercent = 0;
    float unknownPercent = 0;
    public GenderDistributionRectangle (float boysPercent, float girlsPercent, float unknownPercent, List<Integer> sizes) {
        this.boysPercent = boysPercent;
        this.girlsPercent = girlsPercent;
        this.unknownPercent = unknownPercent;
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createLineBorder(Color.black, 5));
        //System.out.println(boysPercent);
        setupGenderPanel(Color.blue, 0, sizes.get(0), boysPercent);
        setupGenderPanel(Color.gray, 1, sizes.get(2), unknownPercent);
        setupGenderPanel(Color.red, 2, sizes.get(1), girlsPercent);



       // setPreferredSize(new Dimension(100,30));
    }

    void setupGenderPanel (Color color, int gridX, int sizeVal, float percent){
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = gridX;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.weightx = percent;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.fill = 1;

        Rectangle genderRect = new Rectangle(color, sizeVal);
        if(sizeVal > 0) {
            add(genderRect, gridBagConstraints);
        }
        else {
    //        System.out.println("notadded");
        }
    }

}
