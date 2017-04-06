package com.eli.landa.cmpt213.UI.Elements;

import com.eli.landa.cmpt213.Model.Observer;

import javax.swing.*;

/**
 * Created by Eli on 2017-04-03.
 */
public class TextBox extends JPanel implements Observer{
    public  TextBox () {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JTextField textField = new JTextField();
        textField.setColumns(4);
        add(textField);
    }

    @Override
    public void update() {

    }
}
