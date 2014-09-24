package com.allcoware.actiontaximockup.main;

import java.awt.Container;
import javax.swing.JFrame;

/**
 *
 * @author alfred
 */
public class Main {

    public static void main(String[] args) {

        /*
         DriverFormFactory driverForm = new DriverFormFactory();
         CabFormFactory cabForm = new CabFormFactory();               
        //*/
        
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setVisible(true);

        Container container = frame.getContentPane();
        container.add(new MainHub());

    }
}
