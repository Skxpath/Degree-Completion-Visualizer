package com.eli.landa.cmpt213.UI.Elements;

import com.eli.landa.cmpt213.Enums.GenderEnum;
import com.eli.landa.cmpt213.Enums.ProgramEnum;
import com.eli.landa.cmpt213.Enums.YearEnum;
import com.eli.landa.cmpt213.Model.DegreeCompletionVisualizerFacade;
import com.eli.landa.cmpt213.Model.Student;
import com.eli.landa.cmpt213.Model.StudentManager;
import com.eli.landa.cmpt213.UI.FilterContainer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Eli on 2017-04-03.
 */
public class FlowRectangle extends JPanel {
    List<Student> filteredStudentListMale = null;
    List<Student> filteredStudentListFemale = null;
    List<Student> filteredStudentListUnknown = null;
    YearEnum yearEnum;
    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    public FlowRectangle(YearEnum yearEnum, boolean isComing) {
        //DegreeCompletionVisualizerFacade.getInstance().registerObserver(this);
        this.yearEnum = yearEnum;

        setLayout(new GridBagLayout());


        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.weightx = .1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.fill = 1;

        setBorder(BorderFactory.createLineBorder(Color.black, 5));




        ProgramEnum programEnum = ProgramEnum.CSMAJ;
        setupFilters(programEnum, yearEnum);
        List<Float> listOfPercents = getListOfPercents();
        List<Integer> listOfAmounts = new ArrayList<>();
        listOfAmounts.add(4);
        listOfAmounts.add(12);
        listOfAmounts.add(3);
        add(new GenderDistributionRectangle(listOfPercents.get(0),listOfPercents.get(1),listOfPercents.get(2), listOfAmounts), gridBagConstraints);

        programEnum = ProgramEnum.CSJNT;
        setupFilters(programEnum, yearEnum);
        listOfPercents = getListOfPercents();
        gridBagConstraints.gridy = 1;
        add(new GenderDistributionRectangle(listOfPercents.get(0),listOfPercents.get(1),listOfPercents.get(2), listOfAmounts), gridBagConstraints);

        programEnum = ProgramEnum.SOSY;
        setupFilters(programEnum, yearEnum);
        listOfPercents = getListOfPercents();
        gridBagConstraints.gridy = 2;
        add(new GenderDistributionRectangle(listOfPercents.get(0),listOfPercents.get(1),listOfPercents.get(2), listOfAmounts), gridBagConstraints);
    }

    void setupFilters (ProgramEnum programEnum , YearEnum yearEnum){
        FilterContainer filterContainer = new FilterContainer();
        StudentManager studentManager = DegreeCompletionVisualizerFacade.getInstance().getStudentManager();
        List<Student> allStudentsList = studentManager.getStudents();
        filteredStudentListMale = filterContainer.sortByGender(GenderEnum.MALE, filterContainer.sortByProgramWithGivenYear(programEnum, yearEnum, allStudentsList));
        filteredStudentListFemale = filterContainer.sortByGender(GenderEnum.FEMALE, filterContainer.sortByProgramWithGivenYear(programEnum, yearEnum, allStudentsList));
        filteredStudentListUnknown = filterContainer.sortByGender(GenderEnum.UNKNOWN, filterContainer.sortByProgramWithGivenYear(programEnum, yearEnum, allStudentsList));
    }

    float getPercent (int amount, int total){
        float demnominator = total;
        float numerator = amount;
        return (numerator/demnominator);
    }

    List<Float> getListOfPercents () {
        List<Float> listOfPercents = new ArrayList<>();
        int sum = filteredStudentListFemale.size() + filteredStudentListMale.size() + filteredStudentListUnknown.size();
        float malePercent = getPercent(filteredStudentListMale.size(), sum);
        float femalePercent = getPercent(filteredStudentListFemale.size(), sum);
        float unknownPercent = getPercent(filteredStudentListUnknown.size(), sum);
        listOfPercents.add(malePercent);
        listOfPercents.add(femalePercent);
        listOfPercents.add(unknownPercent);
        return listOfPercents;
    }

   /* @Override
    public void update() {
        removeAll();
        ProgramEnum programEnum = ProgramEnum.CSMAJ;
        setupFilters(programEnum, yearEnum);
        List<Float> listOfPercents = getListOfPercents();
        gridBagConstraints.gridy = 0;
        add(new GenderDistributionRectangle(listOfPercents.get(0),listOfPercents.get(1),listOfPercents.get(2)), gridBagConstraints);

        programEnum = ProgramEnum.CSJNT;
        setupFilters(programEnum, yearEnum);
        listOfPercents = getListOfPercents();
        gridBagConstraints.gridy = 1;
        add(new GenderDistributionRectangle(listOfPercents.get(0),listOfPercents.get(1),listOfPercents.get(2)), gridBagConstraints);

        programEnum = ProgramEnum.SOSY;
        setupFilters(programEnum, yearEnum);
        listOfPercents = getListOfPercents();
        gridBagConstraints.gridy = 2;
        add(new GenderDistributionRectangle(listOfPercents.get(0),listOfPercents.get(1),listOfPercents.get(2)), gridBagConstraints);
        updateUI();
    }*/
}
