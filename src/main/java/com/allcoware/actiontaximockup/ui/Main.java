package com.allcoware.actiontaximockup.ui;

import com.allcoware.actiontaximockup.DriverHolder;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

/**
 ** please consider this class and well any class I worked on to just be a
 * collection of operations to be torn out and placed wherever as you guys see fit. 
 * 
 * @author alfred
 */
public class Main {

    public static void main(String[] args) {
/*
        //basic setup of a JFrame
        JFrame frame = new JFrame("Action Taxi");        
        frame.setSize(800, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setContentPane(new JScrollPane());*/

        DriverHolder driverHolder = new DriverHolder();
        CabHolder cabHolder=new CabHolder();
        TableMaker tableMaker = new TableMaker();
        driverHolder.test();
        cabHolder.test();
        
        tableMaker.createMainForm();
        
        /*
        frame.add(tableMaker.createMainForm());
        
        frame.setVisible(true);*/
    }
}
