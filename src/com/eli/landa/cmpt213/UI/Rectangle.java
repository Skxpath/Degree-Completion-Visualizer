package com.eli.landa.cmpt213.UI;

import com.eli.landa.cmpt213.Model.DegreeCompletionVisualizerFacade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by QueenNadine on 2017-04-03.
 */
public class Rectangle extends JPanel {
    public  Rectangle () {
    }
    public void paint(Graphics g) {
        g.setColor(Color.red);
        g.drawRect(0,0,100,100);
    }
}
