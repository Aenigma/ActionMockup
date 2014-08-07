package com.allcoware.actiontaximockup.main;

import com.allcoware.actiontaximockup.ui.resources.DriverMenu;
import com.allcoware.actiontaximockup.ui.resourceholders.CabFormFactory;
import com.allcoware.actiontaximockup.ui.resourceholders.DriverFormFactory;
import com.allcoware.actiontaximockup.ui.resourceholders.RecurringTransactionFormFactory;
import com.allcoware.actiontaximockup.ui.resourceholders.TransactionFormFactory;
import java.awt.Container;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 ** please consider this class and well any class I worked on to just be a
 * collection of operations to be torn out and placed wherever as you guys see
 * fit.
 *
 * @author alfred
 */
public class Main {

    public static void main(String[] args) {
 
        DriverFormFactory driverForm = new DriverFormFactory();
        CabFormFactory cabForm = new CabFormFactory();

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = frame.getContentPane();

        container.add(new DriverMenu(frame, container, driverForm, cabForm));
        
        //*/
    }
}
