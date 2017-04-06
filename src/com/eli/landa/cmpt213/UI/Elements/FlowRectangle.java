package com.eli.landa.cmpt213.UI.Elements;

import com.eli.landa.cmpt213.Enums.ProgramEnum;
import com.eli.landa.cmpt213.Enums.YearEnum;
import com.eli.landa.cmpt213.Model.DegreeCompletionVisualizerFacade;
import com.eli.landa.cmpt213.Model.Student;
import com.eli.landa.cmpt213.UI.FilterList;

import javax.swing.*;
import java.awt.*;
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
    boolean isComing;
    public FlowRectangle(YearEnum yearEnum, boolean isComing) {
        this.isComing = isComing;
        //DegreeCompletionVisualizerFacade.getInstance().registerObserver(this);
        this.yearEnum = yearEnum;

        setLayout(new GridBagLayout());


        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.fill = 1;

        setPreferredSize(new Dimension(400,400));
        setBorder(BorderFactory.createLineBorder(Color.black, 5));
        if(isComing) {
            gridBagConstraints.gridy = 0;
            gridBagConstraints.weighty = 1;
            JLabel title = new JLabel("Coming");
            add(title, gridBagConstraints);
            gridBagConstraints.weighty = 100;
            gridBagConstraints.gridy = 1;
            setupGenderPanelForComing(ProgramEnum.SOSY, DegreeCompletionVisualizerFacade.getInstance().getFilterSettings().getProgramEnum(), yearEnum);
            gridBagConstraints.gridy = 2;
            setupGenderPanelForComing(ProgramEnum.CSJNT, DegreeCompletionVisualizerFacade.getInstance().getFilterSettings().getProgramEnum(), yearEnum);
            gridBagConstraints.gridy = 3;
            setupGenderPanelForComing(ProgramEnum.CSMNR, DegreeCompletionVisualizerFacade.getInstance().getFilterSettings().getProgramEnum(), yearEnum);
            gridBagConstraints.gridy = 4;
            setupGenderPanelForComing(ProgramEnum.OTHER, DegreeCompletionVisualizerFacade.getInstance().getFilterSettings().getProgramEnum(), yearEnum);

        }
        else {
            gridBagConstraints.gridy = 0;
            gridBagConstraints.weighty = 1;
            JLabel title = new JLabel("Leaving");
            add(title, gridBagConstraints);
            gridBagConstraints.weighty = 100;
            gridBagConstraints.gridy = 1;
            setupGenderPanelForLeaving(DegreeCompletionVisualizerFacade.getInstance().getFilterSettings().getProgramEnum(), ProgramEnum.SOSY, yearEnum);
            gridBagConstraints.gridy = 2;
            setupGenderPanelForLeaving(DegreeCompletionVisualizerFacade.getInstance().getFilterSettings().getProgramEnum(), ProgramEnum.CSJNT, yearEnum);
            gridBagConstraints.gridy = 3;
            setupGenderPanelForLeaving(DegreeCompletionVisualizerFacade.getInstance().getFilterSettings().getProgramEnum(), ProgramEnum.CSMNR, yearEnum);
            gridBagConstraints.gridy = 4;
            setupGenderPanelForLeaving(DegreeCompletionVisualizerFacade.getInstance().getFilterSettings().getProgramEnum(), ProgramEnum.OTHER, yearEnum);
            gridBagConstraints.gridy = 5;
            setupGenderPanelForLeaving(DegreeCompletionVisualizerFacade.getInstance().getFilterSettings().getProgramEnum(), ProgramEnum.DROPOUT, yearEnum);

        }


       /* ProgramEnum programEnum = ProgramEnum.CSMAJ;
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
        add(new GenderDistributionRectangle(listOfPercents.get(0),listOfPercents.get(1),listOfPercents.get(2), listOfAmounts), gridBagConstraints);*/
    }
    void setupGenderPanelForComing (ProgramEnum programEnum, ProgramEnum newProgramEnum, YearEnum yearEnum){
        float total = FilterList.joinedFromSpecificProgram(programEnum,newProgramEnum,yearEnum).get(3);
        float male = FilterList.joinedFromSpecificProgram(programEnum,newProgramEnum,yearEnum).get(0);
        float female = FilterList.joinedFromSpecificProgram(programEnum,newProgramEnum,yearEnum).get(1);
        float unknown = FilterList.joinedFromSpecificProgram(programEnum,newProgramEnum,yearEnum).get(2);
      //  System.out.println(yearEnum);
        List<Integer> listOfAmounts = FilterList.joinedFromSpecificProgram(programEnum,newProgramEnum,yearEnum);
        JLabel other = new JLabel(programEnum.toString());
        gridBagConstraints.gridx = 0;
        gridBagConstraints.weightx = 1;
        add(other, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.weightx = 40;
        add(new GenderDistributionRectangle(male,female,unknown, listOfAmounts), gridBagConstraints);

    }
    void setupGenderPanelForLeaving (ProgramEnum programEnum, ProgramEnum newProgramEnum, YearEnum yearEnum){
        float total = FilterList.leftToSpecificProgram(programEnum,newProgramEnum,yearEnum).get(3);
        float male = FilterList.leftToSpecificProgram(programEnum,newProgramEnum,yearEnum).get(0);
        float female = FilterList.leftToSpecificProgram(programEnum,newProgramEnum,yearEnum).get(1);
        float unknown = FilterList.leftToSpecificProgram(programEnum,newProgramEnum,yearEnum).get(2);
        //  System.out.println(yearEnum);
        List<Integer> listOfAmounts = FilterList.joinedFromSpecificProgram(programEnum,newProgramEnum,yearEnum);
        JLabel other = new JLabel(newProgramEnum.toString());
        gridBagConstraints.gridx = 0;
        gridBagConstraints.weightx = 1;
        add(other, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.weightx = 40;
        add(new GenderDistributionRectangle(male,female,unknown, listOfAmounts), gridBagConstraints);

    }

    /*void setupFilters (ProgramEnum programEnum , YearEnum yearEnum){
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

   *//* @Override
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
