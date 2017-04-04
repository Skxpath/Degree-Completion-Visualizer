package com.eli.landa.cmpt213.UI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by QueenNadine on 2017-04-03.
 */
public class Containter extends JPanel{
    public Containter (){
        JPanel dropdownMenu = new DropdownMenu();
        JPanel rect = new Rectangle();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(dropdownMenu, Component.TOP_ALIGNMENT);
        add(rect, Component.TOP_ALIGNMENT);
    }

}
