package com.eli.landa.cmpt213.UI;

import javax.swing.*;
import java.awt.*;
import java.util.List;
/**
 * Created by QueenNadine on 2017-04-03.
 */
public class Container extends JPanel{

    public Container(List<JPanel> panels){
        setLayout(new GridBagLayout());
        for (JPanel panel: panels) {
            add(panel);
        }
    }


}
