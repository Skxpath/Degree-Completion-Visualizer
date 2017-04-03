package com.eli.landa.cmpt213.UI;
import javax.swing.*;
import java.awt.*;

/* GraphicalUI class to display data dump to the console
* */
public class GraphicalUI {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Degree Completion Visualizer");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 1500);
        frame.setLocation(0, 0);

        JPanel dropdownMenu = new DropdownMenu();

        frame.add(dropdownMenu, BorderLayout.WEST);







        frame.setVisible(true); // added code

    }
}
