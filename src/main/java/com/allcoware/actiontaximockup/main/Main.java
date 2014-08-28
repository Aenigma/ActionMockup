package com.allcoware.actiontaximockup.main;

import com.allcoware.actiontaximockup.ui.resources.DriverMenu;
import com.allcoware.actiontaximockup.ui.resourceholders.CabFormFactory;
import com.allcoware.actiontaximockup.ui.resourceholders.DriverFormFactory;
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
        frame.setVisible(true);
        
        Container container = frame.getContentPane();
        container.add(new MainHub()); 
        
        
        
    }
}
