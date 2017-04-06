package com.eli.landa.cmpt213.UI.Elements;

import com.eli.landa.cmpt213.Enums.ProgramEnum;
import com.eli.landa.cmpt213.Enums.YearEnum;
import com.eli.landa.cmpt213.UI.FilterList;

import javax.swing.*;
import java.awt.*;
import java.util.List;
/**
 * Created by Eli on 2017-04-05.
 */
public class YearRectangle extends JPanel{
    ProgramEnum programEnum;
    YearEnum yearEnum;
    public YearRectangle(YearEnum yearEnum, ProgramEnum programEnum) {
       // DegreeCompletionVisualizerFacade.getInstance().registerObserver(this);
        this.yearEnum = yearEnum;
        this.programEnum = programEnum;
        List<Integer> studentsInProgramAtMileStone = FilterList.studentsInProgramAtMilestone(yearEnum,programEnum);
        setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        setBorder(BorderFactory.createLineBorder(Color.black, 5));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
    gridBagConstraints.fill = 1;
        JLabel lbl = new JLabel(this.programEnum.toString());
        add(lbl, gridBagConstraints);

        gridBagConstraints.gridy = 1;

        float allStudents = studentsInProgramAtMileStone.get(3);
        float amountOfBoys = studentsInProgramAtMileStone.get(0);
        float amountOfGirls = studentsInProgramAtMileStone.get(1);
        float amountOfUnknown = studentsInProgramAtMileStone.get(2);
        float boysPercent = amountOfBoys/allStudents;
        float girlsPercent = amountOfGirls/allStudents;
        float unknownPercent = amountOfUnknown/allStudents;
        add(new GenderDistributionRectangle(boysPercent,girlsPercent,unknownPercent, studentsInProgramAtMileStone), gridBagConstraints);

        gridBagConstraints.gridy = 2;
        JLabel amount = new JLabel(studentsInProgramAtMileStone.get(3) + "");
        add(amount, gridBagConstraints);

        gridBagConstraints.gridy = 3;
        JLabel year = new JLabel(yearEnum.toString());
        add(year, gridBagConstraints);
        setPreferredSize(new Dimension(100,200));
    }

   /* @Override
    public void update() {
        JLabel label = new JLabel(DegreeCompletionVisualizerFacade.getInstance().getFilterSettings().getProgramEnum().toString());
        remove(0);
        add(label, 0);
        updateUI();
    }*/
}
