package com.eli.landa.cmpt213.UI.Containers;

import com.eli.landa.cmpt213.Enums.ProgramEnum;
import com.eli.landa.cmpt213.Enums.YearEnum;
import com.eli.landa.cmpt213.Model.DegreeCompletionVisualizerFacade;
import com.eli.landa.cmpt213.Model.Observer;
import com.eli.landa.cmpt213.UI.Elements.FlowRectangle;
import com.eli.landa.cmpt213.UI.Elements.YearRectangle;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Eli on 2017-04-05.
 */
public class FlowAndYearsContainer extends JPanel implements Observer{
    public FlowAndYearsContainer() {
        DegreeCompletionVisualizerFacade.getInstance().registerObserver(this);
        setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        setBorder(BorderFactory.createLineBorder(Color.black, 1));
        int gridXVal = 0;
        YearEnum yearEnums[] = YearEnum.values();
        ProgramEnum programEnums[] = ProgramEnum.values();
        for (int i = 0; i < 4; i ++){
            gridBagConstraints.gridx = gridXVal;
            gridBagConstraints.gridy = 3;
            gridBagConstraints.weightx = 1;
            gridBagConstraints.weighty = 1;
            gridBagConstraints.fill = 1;
            add(new YearRectangle(yearEnums[i], DegreeCompletionVisualizerFacade.getInstance().getFilterSettings().getProgramEnum()), gridBagConstraints);
            gridXVal++;
            gridBagConstraints.weighty = .1;
            gridBagConstraints.gridx = gridXVal;
            gridBagConstraints.gridy = 2;
            add(new FlowRectangle(yearEnums[i+1], true), gridBagConstraints);

            gridXVal++;

            gridBagConstraints.gridx = gridXVal;
            gridBagConstraints.gridy = 4;
            add(new FlowRectangle(yearEnums[i+1], false), gridBagConstraints);

            gridXVal++;
        }
        gridBagConstraints.gridx = gridXVal;
        gridBagConstraints.gridy = 3;
        add(new YearRectangle(YearEnum.FOURTH_YEAR, DegreeCompletionVisualizerFacade.getInstance().getFilterSettings().getProgramEnum()), gridBagConstraints);
    }

    @Override
    public void update() {
        removeAll();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        int gridXVal = 1;
        YearEnum yearEnums[] = YearEnum.values();
        ProgramEnum programEnums[] = ProgramEnum.values();
        for (int i = 0; i < 4; i ++){
            gridBagConstraints.gridx = gridXVal;
            gridBagConstraints.gridy = 3;
            gridBagConstraints.weightx = 1;
            gridBagConstraints.weighty = 1;
            gridBagConstraints.fill = 1;
            add(new YearRectangle(yearEnums[i], DegreeCompletionVisualizerFacade.getInstance().getFilterSettings().getProgramEnum()), gridBagConstraints);
            gridXVal++;
            gridBagConstraints.weighty = .1;
            gridBagConstraints.gridx = gridXVal;
            gridBagConstraints.gridy = 2;
            add(new FlowRectangle(yearEnums[i+1], true), gridBagConstraints);

            gridXVal++;

            gridBagConstraints.gridx = gridXVal;
            gridBagConstraints.gridy = 4;
            add(new FlowRectangle(yearEnums[i+1], false), gridBagConstraints);

            gridXVal++;
        }
        gridBagConstraints.gridx = gridXVal;
        gridBagConstraints.gridy = 3;
        add(new YearRectangle(YearEnum.FOURTH_YEAR, DegreeCompletionVisualizerFacade.getInstance().getFilterSettings().getProgramEnum()), gridBagConstraints);
        updateUI();
    }
}
