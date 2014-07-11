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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * I edited this class for the gui creation
 *
 * @author alfred
 */
public class CabForm implements ResourceBuilder {

    public Cab driver;
    private JFrame frame;
    private JPanel panel;

    public boolean isReady() {
        return false;
    }

    /**
     * Retrieves driver
     *
     * @return
     */
    public Cab getResource() {
        return driver;
    }

    public void updateFields() {

    }

    /**
     *
     * @param name
     * @param location
     * @param isNew
     */
    public void createFrame(String name, int location, boolean isNew) {
        frame = new JFrame(name);
        panel = new JPanel(); //new GridLayout(2,1));
        frame.setSize(400, 400);

        Dimension dem = new Dimension();
        dem.setSize(150, 1);

        JTextField cabNum = new JTextField();
        cabNum.setMinimumSize(dem);

        panel.add(groupBuilderTextBox(new JLabel("Cab Number"), cabNum), BorderLayout.NORTH);

        JButton accept = new JButton("Accept Changes");
        panel.add(accept, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);
    }

    /**
     * this class is used to create the gouplayout of jlables and
     * jtextfields/jcomboboxs
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
     * this methed creates a gui based on creating a new cab
     *
     * @param theFrame
     */
    public void addNewCab(JFrame theFrame) {

        /*frame.removeAll();
         frame.revalidate();
         frame.repaint();
         frame.setTitle("New Cab");
         frame.setVisible(true);*/
        createFrame("New Cab", -1, true);

        // JButton unUsedCabs = newLink("Un-Used Cabs");
        //panel.add(unUsedCabs);
    }

    /**
     * this method creates a gui to edit an exiting cab
     *
     * @param theFrame
     * @param name
     */
    public void editForm(JFrame theFrame, String name) {

    }
}
