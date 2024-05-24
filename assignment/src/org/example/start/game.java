package org.example.start;

import javax.swing.*;

public class game extends JFrame {
    static int width=1500;
    static int height=1000;
public game(){
    setSize(width,height);
    setLocationRelativeTo(null);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    add(new settings());
   
}
}
