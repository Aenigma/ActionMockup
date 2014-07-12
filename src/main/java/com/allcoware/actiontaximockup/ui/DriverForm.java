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

import com.allcoware.actiontaximockup.Driver;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class retrieves driver main information, driver's transaction
 * information, and driver's recurring transaction. I edited this class for the
 * gui creation.
 *
 * @author alfred
 */
public class DriverForm extends JResourceForm<Driver> {

    private Driver driver;
    
    private TransactionFormFactory tff;
    private RecurringTransactionFormFactory rtff;
    private JFrame frame;
    private JPanel panel;
    private JTextField driNum;
    private JTextField firNam;
    private JTextField midNam;
    private JTextField lasNam;
    private JTextField phoNum;
    private JComboBox cab;

    public DriverForm(Driver driver) {
        this.driver = driver;
    }
    
    public DriverForm() {
    }

    public boolean isReady() {
        return false;
    }

    public Driver getResource() {
        return driver;
    }

    public void updateFields() {

    }

    /**
     * this method creates the pop out window containing basic/specified driver
     * data
     *
     * @param name
     * @param location
     * @param isNew
     */
    public void createFrame(String name, int location, boolean isNew) {
        frame = new JFrame(name);

        JPanel topPanel = new JPanel(new GridLayout(6, 1));

        JPanel centerPanel = new JPanel(new GridLayout(2, 1));
        JPanel bottomPanel = new JPanel();
        panel = new JPanel(); //new GridLayout(3, 1));
        frame.setSize(400, 600);

        Dimension dem = new Dimension();
        dem.setSize(150, 1);

        driNum = new JTextField();
        driNum.setMinimumSize(dem);
        firNam = new JTextField();
        firNam.setMinimumSize(dem);
        midNam = new JTextField();
        midNam.setMinimumSize(dem);
        lasNam = new JTextField();
        lasNam.setMinimumSize(dem);
        phoNum = new JTextField();
        phoNum.setMinimumSize(dem);
        cab = new JComboBox();
        cab.setMaximumSize(dem);

        ArrayList<Cab> ca = new ArrayList<Cab>();
        for (int i = 0; i < ca.size(); i++) {
            cab.addItem(ca.get(i).getCabID());
        }

        topPanel.add(groupBuilderTextBox(new JLabel("Driver Number"), driNum));
        topPanel.add(groupBuilderTextBox(new JLabel("First Name"), firNam));
        topPanel.add(groupBuilderTextBox(new JLabel("Middle Name"), midNam));
        topPanel.add(groupBuilderTextBox(new JLabel("Last Name"), lasNam));
        topPanel.add(groupBuilderTextBox(new JLabel("Phone Number"), phoNum));
        topPanel.add(groupBuilderComboBox(new JLabel("Cab Number"), cab));

        JButton accept = new JButton("Accept Changes");
        bottomPanel.add(accept);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);
        frame.add(panel);
        frame.setVisible(true);
    }

    /**
     * this class is used to create the gouplayout of jlables and jtextfields
     *
     * @param label
     * @param text
     * @return
     */
    public JPanel groupBuilderTextBox(JLabel label, JTextField text) {

        JPanel temp = new JPanel();
        GroupLayout layout = new GroupLayout(temp);
        temp.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                .addComponent(label)
                .addComponent(text)
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(label)
                .addComponent(text)
        );

        return temp;
    }

    /**
     * * this class is used to create the gouplayout of jlables and jcomboboxs
     *
     * @param label
     * @param combo
     * @return
     */
    public JPanel groupBuilderComboBox(JLabel label, JComboBox combo) {

        JPanel temp = new JPanel();
        GroupLayout layout = new GroupLayout(temp);
        temp.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                .addComponent(label)
                .addComponent(combo)
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(label)
                .addComponent(combo)
        );

        return temp;
    }

    /**
     * this method creates a version of the gui based on creating a new driver
     *
     * @param theFrame
     */
    public void addNewDriver(JFrame theFrame) {
        /*frame.removeAll();
         frame.revalidate();
         frame.repaint();
         frame.setTitle("New Driver");
         frame.setVisible(true);*/

        createFrame("New Driver", -1, true);
    }

    /**
     * this method is suppose to locate the specified driver's data and create
     * and editable gui based on that data
     *
     * @param theFrame
     * @param name
     */
    public void editForm(JFrame theFrame, String name) {
        createFrame("Edit Form", -1, true);
    }
}
