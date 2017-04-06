package com.eli.landa.cmpt213.UI.Elements;

import javax.swing.*;
import java.awt.*;

/**
 * Created by QueenNadine on 2017-04-03.
 */
public class Rectangle extends JPanel {
    private int width;
    private int height;
    private Color color;
    public  Rectangle (Color color, int size) {
        setLayout(new FlowLayout());

        JLabel lbl = new JLabel(size + "");
        JPanel overlay = new JPanel();
        overlay.setLayout( new OverlayLayout(overlay) );
        overlay.setOpaque(false);
        overlay.setAlignmentX(Component.LEFT_ALIGNMENT);
        overlay.add(lbl);
        add(overlay);

        this.color = color;

        setBackground(color);
        setBorder(BorderFactory.createLineBorder(Color.black, 1));
    }
/*    public void paint(Graphics g) {
        g.setColor(color);
        g.fillRect(0,0,getWidth(),height);
    }*/
}
