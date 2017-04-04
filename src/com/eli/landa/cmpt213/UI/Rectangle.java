package com.eli.landa.cmpt213.UI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by QueenNadine on 2017-04-03.
 */
public class Rectangle extends JPanel {
    private int width;
    private int height;
    private Color color;
    public  Rectangle (int width, int height, Color color) {
        setLayout(new GridLayout(1,1));
        this.width = width;
        this.height = height;
        this.color = color;
    }
    public void paint(Graphics g) {
        g.setColor(color);
        g.fillRect(0,0,width,height);
    }
}
