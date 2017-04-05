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
        setLayout(new FlowLayout());
        this.width = width;
        this.height = height;
        this.color = color;
        setBackground(color);
       // setBorder(BorderFactory.createLineBorder(Color.black, 5));
    }
/*    public void paint(Graphics g) {
        g.setColor(color);
        g.fillRect(0,0,getWidth(),height);
    }*/
}
