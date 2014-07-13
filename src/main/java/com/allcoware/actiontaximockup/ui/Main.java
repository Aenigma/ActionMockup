package com.allcoware.actiontaximockup.ui;

/**
 ** please consider this class and well any class I worked on to just be a
 * collection of operations to be torn out and placed wherever as you guys see
 * fit.
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

        DriverFormFactory driverHolder = new DriverFormFactory();
        CabFormFactory cabHolder = new CabFormFactory();
        TableMaker tableMaker = new TableMaker();
        tableMaker.createMainForm();

        /*
         frame.add(tableMaker.createMainForm());
        
         frame.setVisible(true);*/
    }
}
