package com.eli.landa.cmpt213.UI.Containers;

import javax.swing.*;
import java.awt.*;
import java.util.List;
/**
 * Contains JPanels
 */
public class Container extends JPanel{

    public Container(List<JPanel> panels){
        setLayout(new GridBagLayout());
        for (JPanel panel: panels) {
            add(panel);
        }
    }


}
