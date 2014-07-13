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
package com.allcoware.actiontaximockup.ui;

/**
 * this class is completely garbage, and was used for testing and learning,
 * ignore it or delete it, I will clean it up later along with the other classes
 * when things start functioning correctly..
 *
 * @author alfred
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class JComponent {

    private JFrame frame;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JPanel panel;
    private JButton button;
    private JLabel lable;
    private TableMaker tab = new TableMaker();
    //create a grid bag contraint object
    GridBagConstraints con = new GridBagConstraints();

    public JComponent() {
        //gui();
        //gui2();
        //set pixel spacing between items on the grid, in this case 10 pixels of space in all directions
        con.insets = new Insets(10, 10, 10, 10);
        //createNew();
    }

    public JPanel createNew() {

        //adds search bar to the top of the frame
        JPanel searchPanel = new JPanel();
        searchPanel.add(searchField());
        searchPanel.add(unUsedCabs());
        searchPanel.add(newDriver());
        searchPanel.add(newCab());
        searchPanel.add(editForm());
        return searchPanel;
    }

    /**
     * returns "Search" field, but for the process of time I'm going to leave
     * this one blank for now, but my thought is that we could/should have a
     * search bar where this button is possibly with a drop-down arrow to
     * specify the search.
     *
     * @return
     */
    public JButton searchField() {
        JButton searchField = new JButton("Search Field");

        return searchField;
    }

    public JButton unUsedCabs() {
        JButton searchField = new JButton("Un-Used Cabs");

        return searchField;
    }

    public JButton editForm() {
        JButton searchField = new JButton("Edit Form");

        return searchField;
    }

    /**
     * Creates scroll table at the bottom of the frame
     *
     * @return
     */
    public JPanel DisplayPanel() {
        JPanel displayPanel = new JPanel(new GridBagLayout());

        String[] columns = {"Last Name", "ID Number", "Cab Number"};

        String[][] data = {
            {"Bob", "1234", "A4"},
            {"Abby", "3498", "R6"},
            {"Sally", "5694", "G9"}
        };

        JTable random = new JTable(data, columns) {
            public boolean isCellEditable(int data, int columns) {
                return false;
            }
        };

        JScrollPane scrollTable = new JScrollPane(random);
        displayPanel.add(scrollTable);
        return displayPanel;
    }

    /**
     * Returns "Add New Driver" Button
     *
     * @return
     */
    public JButton newDriver() {
        JButton newDriver = new JButton("Add New Driver");
        //add actionlistener to driver button
        newDriver.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {

                //this would create a new frame
                JFrame frame2 = new JFrame("Driver Menu");
                frame2.setVisible(true);
                frame2.setSize(600, 400);
                //need to change the close operation to something...
                // frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }

        });
        return newDriver;
    }

    /**
     * Returns "Add New Cab" Button
     *
     * @return
     */
    public JButton newCab() {
        JButton newCab = new JButton("Add New Cab");
        //add actionlistener to driver button
        newCab.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {

                //this would create a new frame
                JFrame frame2 = new JFrame("Cab Menu");
                frame2.setVisible(true);
                frame2.setSize(600, 400);
                //need to change the close operation to something...
                // frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }

        });
        return newCab;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////     
    public void gui() {

        //creates new frame with title arross the top
        frame = new JFrame("title of window");

        //sets frame visablity to true
        frame.setVisible(true);

        //sets frame default size
        frame.setSize(600, 400);

        //sets frame to exit on hitting close button
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //creatwes new panel
        panel = new JPanel();

        //changes panel color to yellow
        panel.setBackground(Color.YELLOW);

        //create button
        button = new JButton("button lable");

        //create label
        lable = new JLabel("label");

        //add button to panel
        panel.add(button);

        //add label to panel
        panel.add(lable);

        //add panel to frame
        frame.add(panel);

        //creates new panel
        panel = new JPanel();

        //creates new button
        button = new JButton("button 2");

        //sets new panel color to blue
        panel.setBackground(Color.BLUE);

        //adds new button to new panel
        panel.add(button);

        //adds new panel to the bottom of frame
        frame.add(panel, BorderLayout.SOUTH);
    }

    //aranging frame components by grid layout
    public void gui2() {

        //basic setup of a JFrame
        frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //declare grid layout when creating panel
        panel = new JPanel(new GridBagLayout());

        //create two buttons to test with
        JButton b1 = new JButton("button 1");
        JButton b2 = new JButton("button 2");

        /*
         //create a grid bag contraint object
         GridBagConstraints con = new GridBagConstraints();

         //set pixel spacing between items on the grid, in this case 10 pixels of space in all directions
         con.insets = new Insets(10, 10, 10, 10);
         */
        //set x and y for grid con for first button
        con.gridx = 0;
        con.gridy = 1;

        //add first button to panel with grid con
        panel.add(b1, con);

        //set x and y for grid con for second button
        con.gridx = 0;
        con.gridy = 2;

        //add secons button to panel with grid con
        panel.add(b2);

        //add panel to frame on the left hand side
        frame.add(panel, BorderLayout.WEST);
    }

    /*public static void main(String[] args) {
     new makeStuff();
     // new JComponent();
     new TableMaker();
     }*/
}
