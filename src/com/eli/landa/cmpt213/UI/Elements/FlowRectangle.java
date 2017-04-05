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
public class FlowRectangle extends JPanel{
    public FlowRectangle(YearEnum yearEnum, boolean isComing) {
        setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.weightx = .1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.fill = 1;

        setBorder(BorderFactory.createLineBorder(Color.black, 5));




        List<Student> filteredStudentListMale = null;
        List<Student> filteredStudentListFemale = null;
        List<Student> filteredStudentListUnknown = null;

        ProgramEnum programEnum = ProgramEnum.CSMAJ;
        setupFilters(filteredStudentListMale, filteredStudentListFemale, filteredStudentListUnknown, programEnum, yearEnum);

        List<Float> listOfPercents = new ArrayList<>();

        add(new GenderDistributionRectangle(.2f,.6f,.3f), gridBagConstraints);

        gridBagConstraints.gridy = 1;
        add(new GenderDistributionRectangle(.7f,.1f,.3f), gridBagConstraints);

        gridBagConstraints.gridy = 2;
        add(new GenderDistributionRectangle(.5f,.3f,.2f), gridBagConstraints);
    }

    void setupFilters (List<Student> filteredStudentListMale, List<Student> filteredStudentListFemale, List<Student> filteredStudentListUnknown, ProgramEnum programEnum , YearEnum yearEnum){
        FilterContainer filterContainer = new FilterContainer();
        StudentManager studentManager = DegreeCompletionVisualizerFacade.getInstance().getStudentManager();
        List<Student> allStudentsList = studentManager.getStudents();
        filteredStudentListMale = filterContainer.sortByGender(GenderEnum.MALE, filterContainer.sortByProgramWithGivenYear(programEnum, yearEnum, allStudentsList));
        filteredStudentListFemale = filterContainer.sortByGender(GenderEnum.FEMALE, filterContainer.sortByProgramWithGivenYear(programEnum, yearEnum, allStudentsList));
        filteredStudentListUnknown = filterContainer.sortByGender(GenderEnum.UNKNOWN, filterContainer.sortByProgramWithGivenYear(programEnum, yearEnum, allStudentsList));
    }

    float getPercent (int amount, int total){
        return (float)(amount/total);
    }

    List<Float> getListOfPercents (List<Student> filteredStudentListMale, List<Student> filteredStudentListFemale, List<Student> filteredStudentListUnknown) {
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
}
