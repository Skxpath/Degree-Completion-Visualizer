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
        int gridXVal = 1;
        YearEnum yearEnums[] = YearEnum.values();
        ProgramEnum programEnums[] = ProgramEnum.values();
        for (int i = 0; i < 5; i ++){
            gridBagConstraints.gridx = gridXVal;
            gridBagConstraints.gridy = 3;
            add(new YearRectangle(yearEnums[i], programEnums[0]), gridBagConstraints);
            gridXVal++;

            gridBagConstraints.gridx = gridXVal;
            gridBagConstraints.gridy = 2;
            add(new FlowRectangle(YearEnum.FIRST_YEAR, true), gridBagConstraints);

            gridXVal++;

            gridBagConstraints.gridx = gridXVal;
            gridBagConstraints.gridy = 4;
            add(new FlowRectangle(YearEnum.FIRST_YEAR, false), gridBagConstraints);

            gridXVal++;
        }
    }

    @Override
    public void update() {
        removeAll();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        int gridXVal = 1;
        YearEnum yearEnums[] = YearEnum.values();
        ProgramEnum programEnums[] = ProgramEnum.values();
        for (int i = 0; i < 5; i ++){
            gridBagConstraints.gridx = gridXVal;
            gridBagConstraints.gridy = 3;
            add(new YearRectangle(yearEnums[i], DegreeCompletionVisualizerFacade.getInstance().getFilterSettings().getProgramEnum()), gridBagConstraints);
            gridXVal++;

            gridBagConstraints.gridx = gridXVal;
            gridBagConstraints.gridy = 2;
            add(new FlowRectangle(yearEnums[i], true), gridBagConstraints);

            gridXVal++;

            gridBagConstraints.gridx = gridXVal;
            gridBagConstraints.gridy = 4;
            add(new FlowRectangle(yearEnums[i], false), gridBagConstraints);

            gridXVal++;
        }
        updateUI();
    }
}