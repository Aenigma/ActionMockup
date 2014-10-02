/*
 * Copyright (C) 2014 Jon Butler, Sam Morekas,
 *     Rushikesh Parekh, and Kevin Raoofi
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
        container.add(new com.allcoware.actiontaximockup.ui.maininterface.MainHub());

    }
}
