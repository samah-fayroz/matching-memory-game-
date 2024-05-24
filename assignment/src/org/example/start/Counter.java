package org.example.start;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.time.LocalTime;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Counter {
    private final JButton button;
    private final JFormattedTextField hours;
    private final JFormattedTextField minutes;
    private final JFormattedTextField seconds;

    private final Timer timer;
    private final int delay = 1000;
    private final ActionListener taskPerformer = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            time = time.minusSeconds(1);
            if (time.equals(LocalTime.MIN)) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "Game over!", "Ended", JOptionPane.INFORMATION_MESSAGE);
                timer.stop(); 
                System.exit(0);      ///////////////end the game 
            }
            seconds.setText(String.valueOf(time.getSecond()));
            minutes.setText(String.valueOf(time.getMinute()));
            hours.setText(String.valueOf(time.getHour()));
        }
    };

    private LocalTime time = LocalTime.of(0, 0, 0);

    public static void main(String[] args) {

        SwingUtilities.invokeLater(Counter::new);
    }

    public Counter() {
        timer = new Timer(delay,taskPerformer);
        JFrame frame = new JFrame();
        frame.setSize(180, 140);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel panel = new JPanel(new BorderLayout());
        JPanel subPanel1 = new JPanel(new GridLayout(2, 3));

        /*
         * The following lines ensure that the user can
         * only enter numbers.
         */

        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);

        //"labeling"

        JTextField text1 = new JTextField();
        text1.setText("hours:");
        text1.setEditable(false);

        JTextField text2 = new JTextField();
        text2.setText("minutes:");
        text2.setEditable(false);


        JTextField text3 = new JTextField();
        text3.setText("seconds:");
        text3.setEditable(false);

        //fields for minutes and seconds
        hours = new JFormattedTextField(formatter);
        minutes = new JFormattedTextField(formatter);
        seconds = new JFormattedTextField(formatter);
        hours.setText("0");
        minutes.setText("0");
        seconds.setText("0");

        JPanel subPanel2 = new JPanel();

        /*
         * When the user presses the OK-button, the program will
         * start to count down.
         */

        button = new JButton("OK");
        button.addActionListener(actionEvent -> {
            time = LocalTime.of(Integer.parseInt(hours.getText()), Integer.parseInt(minutes.getText()), Integer.parseInt(seconds.getText()));
            button.setEnabled(false);
            //Timer for one second delay
            timer.start();
        });

        //Reset-button
        JButton button2 = new JButton("Reset");
        button2.addActionListener(actionEvent -> {
            hours.setText("0");
            minutes.setText("0");
            seconds.setText("0");
            button.setEnabled(true);
            time = LocalTime.of(0, 0, 0);
            timer.stop();
        });

        subPanel1.add(text1);
        subPanel1.add(text2);
        subPanel1.add(text3);
        subPanel1.add(hours);
        subPanel1.add(minutes);
        subPanel1.add(seconds);
        subPanel2.add(button);
        subPanel2.add(button2);
        panel.add(subPanel1, BorderLayout.CENTER);
        panel.add(subPanel2, BorderLayout.PAGE_END);
        frame.add(panel);
        frame.setVisible(true);
    }
}