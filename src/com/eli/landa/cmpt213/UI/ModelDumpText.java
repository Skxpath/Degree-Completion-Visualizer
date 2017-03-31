package com.eli.landa.cmpt213.UI;

import com.eli.landa.cmpt213.Enums.ActionEnum;
import com.eli.landa.cmpt213.Enums.ProgramEnum;
import com.eli.landa.cmpt213.Enums.SemesterEnum;
import com.eli.landa.cmpt213.Model.*;

import java.util.List;
import java.util.Map;
import java.util.NavigableMap;

/**
 * Created by Aria on 3/30/2017.
 */
public class ModelDumpText { //UI is not done, just dummy data for some parts for the time being.

    String Major;
    ProgramEnum program;
    DegreeCompletionVisualizerFacade model = DegreeCompletionVisualizerFacade.getInstance();

    public ModelDumpText(ProgramEnum program) {

        this.program = program;
        model.generateListsForProgram(program);

        switch (program) {
            case CSMAJ:
                Major = "CS_MAJOR";
                break;
            case SOSY:
                Major = "SOSY";
                break;
            case CSMNR:
                Major = "CS_MINOR";
                break;
            case HIST:
                Major = "HISTORY";
                break;
            case ENSC:
                Major = "ENSC";
                break;
            case MSE:
                Major = "MSE";
                break;
            case STAT:
                Major = "STAT";
                break;
            case OTHER:
                Major = "OTHER";
                break;
            case NO_PROGRAM:
                break;
        }

    }

    public void print() {
        System.out.println("************************************\n" +
                "* Students in program at milestone *\n" +
                "************************************");

        printValuesAtYears(SemesterEnum.ADMITTED);
        printValuesAtYears(SemesterEnum.FIRST_YEAR);
        printValuesAtYears(SemesterEnum.SECOND_YEAR);
        printValuesAtYears(SemesterEnum.THIRD_YEAR);
        printValuesAtYears(SemesterEnum.FOURTH_YEAR);

        //

        System.out.println("\n*************************************************\n" +
                "* Students joining program going into milestone *\n" +
                "*************************************************");

        printJoiningMilestone(SemesterEnum.FIRST_YEAR);
        printJoiningMilestone(SemesterEnum.SECOND_YEAR);
        printJoiningMilestone(SemesterEnum.THIRD_YEAR);
        printJoiningMilestone(SemesterEnum.FOURTH_YEAR);

        System.out.println("\n******************************************************\n" +
                "* LEAVING: Students leaving program before milestone *\n" +
                "******************************************************");
        System.out.println("Milestone: YEAR1\n" +
                "     removed   0 : M=    0  F=    0  U=    0 (reason for leaving CS Major)\n" +
                "     removed   0 : M=    0  F=    0  U=    0 (reason for leaving CS Software Systems)\n" +
                "     removed   0 : M=    0  F=    0  U=    0 (reason for leaving CS Minor)\n" +
                "     removed   0 : M=    0  F=    0  U=    0 (reason for leaving CS Joint Major)\n" +
                "     removed   0 : M=    0  F=    0  U=    0 (reason for leaving Unknown)\n" +
                "     removed   5 : M=    5  F=    0  U=    0 (reason for leaving Dropped Out)\n" +
                "     Total   5\n" +
                "Milestone: YEAR2\n" +
                "     removed   0 : M=    0  F=    0  U=    0 (reason for leaving CS Major)\n" +
                "     removed   0 : M=    0  F=    0  U=    0 (reason for leaving CS Software Systems)\n" +
                "     removed   0 : M=    0  F=    0  U=    0 (reason for leaving CS Minor)\n" +
                "     removed   0 : M=    0  F=    0  U=    0 (reason for leaving CS Joint Major)\n" +
                "     removed   0 : M=    0  F=    0  U=    0 (reason for leaving Unknown)\n" +
                "     removed   4 : M=    3  F=    1  U=    0 (reason for leaving Dropped Out)\n" +
                "     Total   4\n" +
                "Milestone: YEAR3\n" +
                "     removed   0 : M=    0  F=    0  U=    0 (reason for leaving CS Major)\n" +
                "     removed   0 : M=    0  F=    0  U=    0 (reason for leaving CS Software Systems)\n" +
                "     removed   0 : M=    0  F=    0  U=    0 (reason for leaving CS Minor)\n" +
                "     removed   0 : M=    0  F=    0  U=    0 (reason for leaving CS Joint Major)\n" +
                "     removed   0 : M=    0  F=    0  U=    0 (reason for leaving Unknown)\n" +
                "     removed   3 : M=    1  F=    2  U=    0 (reason for leaving Dropped Out)\n" +
                "     Total   3\n" +
                "Milestone: YEAR4\n" +
                "     removed   0 : M=    0  F=    0  U=    0 (reason for leaving CS Major)\n" +
                "     removed   0 : M=    0  F=    0  U=    0 (reason for leaving CS Software Systems)\n" +
                "     removed   0 : M=    0  F=    0  U=    0 (reason for leaving CS Minor)\n" +
                "     removed   0 : M=    0  F=    0  U=    0 (reason for leaving CS Joint Major)\n" +
                "     removed   0 : M=    0  F=    0  U=    0 (reason for leaving Unknown)\n" +
                "     removed   2 : M=    2  F=    0  U=    0 (reason for leaving Dropped Out)\n" +
                "     Total   2");
    }


    public void printEachJoiningMilestoneYearSpecificMajor(SemesterEnum Year, List<Student> list, ProgramEnum program) {
        int maleStudents = 0;
        int femaleStudents = 0;
        int unknownStudents = 0;

        for (Student s : list) {
            Semester milestoneSemester = new Semester(0, 0); //Not added to any student
            Semester previousMilestoneSemester = new Semester(0, 0); //Not added to any student
            switch (Year) {
                case FIRST_YEAR:
                    previousMilestoneSemester = s.getAdmittedSemester();
                    milestoneSemester = s.getEndOfFirstYearSemester();
                    break;
                case SECOND_YEAR:
                    previousMilestoneSemester = s.getEndOfFirstYearSemester();
                    milestoneSemester = s.getEndOfSecondYearSemester();
                    break;
                case THIRD_YEAR:
                    previousMilestoneSemester = s.getEndOfSecondYearSemester();
                    milestoneSemester = s.getEndOfThirdYearSemester();
                    break;
                case FOURTH_YEAR:
                    previousMilestoneSemester = s.getEndOfThirdYearSemester();
                    milestoneSemester = s.getEndOfFourthYearSemester();
                    break;
            }

            ProgramEnum oldProgram;
            ProgramEnum newProgram;

            NavigableMap<Integer, Semester> studentSemesterList = s.getSemesters();

            for (Map.Entry<Integer, Semester> semester : studentSemesterList.entrySet()) { //iterate through the treemap

                if (semester.getKey() >= previousMilestoneSemester.getSemesterCode() && semester.getKey() <= milestoneSemester.getSemesterCode()) {

                    if (semester.getValue().getAction().getSemesterAction() == ActionEnum.ADD) {
                        oldProgram = semester.getValue().getLastMajor();
                        newProgram = semester.getValue().getNewMajor();
                    }
                }
            }
        }
    }

    public void printEachJoiningMilestoneYear(SemesterEnum Year) {

        SemesterFilter filter = new SemesterFilter();

        List<Student> currentYearStudentList = filter.generateList(model.getStudentManager().getStudents(), Year);

        switch (Year) {
            case FIRST_YEAR:
                break;
            case SECOND_YEAR:
                break;
            case THIRD_YEAR:
                break;
            case FOURTH_YEAR:
                break;

        }
        System.out.println("     added   0 : M=    0  F=    0  U=    0 (previous program CS Major)\n" +
                "     added   0 : M=    0  F=    0  U=    0 (previous program CS Software Systems)\n" +
                "     added   0 : M=    0  F=    0  U=    0 (previous program CS Minor)\n" +
                "     added   0 : M=    0  F=    0  U=    0 (previous program CS Joint Major)\n" +
                "     added   0 : M=    0  F=    0  U=    0 (previous program Unknown)\n" +
                "     added   0 : M=    0  F=    0  U=    0 (previous program Dropped Out)\n" +
                "     Total   0");
    }

    public void printJoiningMilestone(SemesterEnum Year) {

        switch (Year) {
            case FIRST_YEAR:
                System.out.println("Milestone: YEAR1");
                printEachJoiningMilestoneYear(Year);
                break;
            case SECOND_YEAR:
                System.out.println("Milestone: YEAR2");
                printEachJoiningMilestoneYear(Year);
                break;
            case THIRD_YEAR:
                System.out.println("Milestone: YEAR3");
                printEachJoiningMilestoneYear(Year);
                break;
            case FOURTH_YEAR:
                System.out.println("Milestone: YEAR4");
                printEachJoiningMilestoneYear(Year);
                break;
        }
    }

    private List getCurrentYearList(SemesterEnum Year) {

        switch (Year) {
            case ADMITTED:
                return model.getYearlyListManager().getAdmittedStudentList();

            case FIRST_YEAR:
                return model.getYearlyListManager().getEndOfFirstYearStudentList();

            case SECOND_YEAR:
                return model.getYearlyListManager().getEndOfSecondYearStudentList();

            case THIRD_YEAR:
                return model.getYearlyListManager().getEndOfThirdYearStudentList();

            case FOURTH_YEAR:
                return model.getYearlyListManager().getEndOfFourthYearStudentList();

            default:
                return null; // should never happen
        }
    }

    public void printValuesAtYears(SemesterEnum Year) {

        List<Student> currentYearStudentList = getCurrentYearList(Year);

        int currentStudents = currentYearStudentList.size();
        int maleStudents = 0;
        int femaleStudents = 0;
        int unknownStudents = 0;

        for (Student s : currentYearStudentList) {
            switch (s.getGender()) {
                case 'M':
                    maleStudents++;
                    break;
                case 'F':
                    femaleStudents++;
                    break;
                case 'U':
                    unknownStudents++;
                    break;
            }
        }

        switch (Year) {

            case ADMITTED:
                System.out.println("  ADMITTED    " + currentStudents + ": M=   " + maleStudents + "   F=   "
                        + femaleStudents + "   U=    " + unknownStudents + " (" + Major + " students at ADMITTED)");
                break;
            case FIRST_YEAR:
                System.out.println("     YEAR1    " + currentStudents + ": M=   " + maleStudents + "   F=   "
                        + femaleStudents + "   U=    " + unknownStudents + " (" + Major + " students at YEAR1)");
                break;
            case SECOND_YEAR:
                System.out.println("     YEAR2    " + currentStudents + ": M=   " + maleStudents + "   F=   "
                        + femaleStudents + "   U=    " + unknownStudents + " (" + Major + " students at YEAR2)");
                break;
            case THIRD_YEAR:
                System.out.println("     YEAR3    " + currentStudents + ": M=   " + maleStudents + "   F=   "
                        + femaleStudents + "   U=    " + unknownStudents + " (" + Major + " students at YEAR3)");
                break;
            case FOURTH_YEAR:
                System.out.println("     YEAR4    " + currentStudents + ": M=   " + maleStudents + "   F=   "
                        + femaleStudents + "   U=    " + unknownStudents + " (" + Major + " students at YEAR4)");
                System.out.println(" GRADUATED    " + currentStudents + ": M=   " + maleStudents + "   F=   "
                        + femaleStudents + "   U=    " + unknownStudents + " (" + Major + " students at GRADUATED)");
                break;
        }
    }
}

