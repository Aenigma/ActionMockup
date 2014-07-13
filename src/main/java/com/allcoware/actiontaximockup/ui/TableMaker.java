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

// this list of variables and imports is insanity and will be cleared up once the class is functioning sufficently 
import com.allcoware.actiontaximockup.Driver;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

/**
 * this class is (for the moment) the main class however I don't want that much
 * dependency on this class.. this classes only real function was/is to create
 * tables and associated buttons to those table not run the whole class...
 * please consider this class and well any class I worked on to just be a
 * collection of operations to be torn out and placed wherever as you guys see
 * fit
 *
 * @author alfred
 */
public class TableMaker {

    private DefaultTableModel model;
    private JTable table;
    private DriverForm driverForm;
    private CabForm cabForm;
    private JFrame frame;
    private JButton search;
    private JComboBox combo;
    private JTextField text;

    public TableMaker() {
    }

    /**
     * t
     * his method creates the main page frame and all however the main frame
     * should be implemented as a jscrollpane however i can't get it to work...
     * also the frame should refesh after a certain time (refresh tables and
     * all) and I also wanted to clear the frame and not have all these extra
     * pop out windows but I can't get that to work right either...
     *
     */
    public void createMainForm() {
        frame = new JFrame("Action Taxi");
        frame.setSize(900, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(createMainFormPanel());
        frame.setVisible(true);
    }

    /**
     *
     * @return returns the panel that contains the main page table and top row
     * of buttons
     */
    public JPanel createMainFormPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.add(addMainFormButtons());
        panel.add(createMainFormTable());
        return panel;
    }

    /**
     * this method creates the search box, textfield, and combo box on the main
     * table. This method is currently incomplete. when finished the user should
     * be able to type in there search, specify the category, and hit the search
     * button to bring up a new frame with a table build based on there search.
     *
     * @param label
     * @param combo
     * @return
     */
    public JPanel groupBuilderSearchBox() {

        List<Driver> driver = new ArrayList<>();

        Dimension dem = new Dimension();
        dem.setSize(150, 1);
        text = new JTextField();
        text.setMinimumSize(dem);
        combo = new JComboBox();
        combo.addItem("Driver Number");
        combo.addItem("First Name");
        combo.addItem("Middle Name");
        combo.addItem("Last Name");
        combo.addItem("Phone Number");
        combo.addItem("Cab Number");
        combo.setMinimumSize(dem);
        search = newSearch();

        JPanel temp = new JPanel();
        GroupLayout layout = new GroupLayout(temp);
        temp.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                .addComponent(text)
                .addComponent(combo)
                .addComponent(search)
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(text)
                .addComponent(combo)
                .addComponent(search)
        );

        return temp;
    }

    /**
     * this button specifies the functionality of the search button on the main
     * page, this method is currently incomplete
     *
     * @return
     */
    public JButton newSearch() {

        JButton newSearch = new JButton("Search");

        newSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                JFrame frame2 = new JFrame("Search");
                frame2.setSize(900, 400);
                JPanel panel2 = new JPanel(new GridLayout(2, 1));
                JPanel panel3 = new JPanel();

                panel3.add(groupBuilderSearchBox());

                panel2.add(panel3);

                panel2.add(createMainFormTable());

                frame2.add(panel2);
                frame2.setVisible(true);
            }
        });
        return newSearch;
    }

    /**
     *
     * @return this returns the main pages table which can be sen on the main
     * page
     */
    public JScrollPane createMainFormTable() {
        TitledBorder border = BorderFactory.createTitledBorder("Bookkeeping");
        model = new DefaultTableModel();
        table = new JTable(model) {
            public boolean isCellEditable(int data, int columns) {
                return false;
            }
        };
        addMainFormColumns();
        addMainFormRows();
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(border);
        return scroll;
    }

    /**
     *
     * @param location
     * @return this returns a table created based on the recurring transactions
     * of a driver
     */
    public JScrollPane createRecurringTransactionFormTable(int location) {
        TitledBorder border = BorderFactory.createTitledBorder("Recurring Transactions");
        model = new DefaultTableModel();
        table = new JTable(model) {
            public boolean isCellEditable(int data, int columns) {
                return false;
            }
        };
        addRecurringTransactionFormColumns();
        addRecurringTransactionFormRows(location);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(border);
        return scroll;
    }

    /**
     *
     * @param location
     * @return this returns a table created based on the transactions of a
     * driver
     */
    public JScrollPane createTransactionFormTable(int location) {
        TitledBorder border = BorderFactory.createTitledBorder("Transactions");
        model = new DefaultTableModel();
        table = new JTable(model) {
            public boolean isCellEditable(int data, int columns) {
                return false;
            }
        };
        addTransactionFormColumns();
        addTransactionFormRows(location);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(border);
        return scroll;
    }

    /**
     *
     * @return this creates the buttons seen on the main page
     */
    public JPanel addMainFormButtons() {
        JPanel panel = new JPanel();
        JPanel searchField = groupBuilderSearchBox();
        JButton addNewDriver = addNewDriver();
        JButton addNewCab = addNewCab();
        JButton editForm = editForm();

        panel.add(searchField);
        panel.add(addNewDriver);
        panel.add(addNewCab);
        panel.add(editForm);
        return panel;
    }

    /**
     *
     * @return this returns the add a new driver button and calls the DriverForm
     * method to create a new driver
     */
    public JButton addNewDriver() {
        JButton addNewDriver = new JButton("Add New Driver");

        addNewDriver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                driverForm = new DriverForm();
                //driverForm.addNewDriver(frame);
            }
        });
        return addNewDriver;
    }

    /**
     *
     * @return this returns the add a new cab button and calls the CabForm
     * method to create a new cab
     */
    public JButton addNewCab() {
        JButton addNewCab = new JButton("Add New Cab");

        addNewCab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                cabForm = new CabForm();
                //cabForm.addNewCab(frame);
            }
        });
        return addNewCab;
    }

    /**
     * This method is incomplete and need to be set to read highlighted row on
     * main table and search DriverHolder for the Driver form data for
     * DriverForm to use
     *
     * @return this returns the add a edit form button and calls the DriverForm
     * method to edit the selected form
     *
     */
    public JButton editForm() {
        JButton editForm = new JButton("Edit Form");

        editForm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                driverForm = new DriverForm();
                //driverForm.editForm(frame, "Edit Form");
            }
        });
        return editForm;
    }

    /**
     * this method adds the main columns/categories for the main site table
     */
    public void addMainFormColumns() {
        addColumn("Driver Number");
        addColumn("First Name");
        addColumn("Middle Name");
        addColumn("Last Name");
        addColumn("Phone Number");
        addColumn("Cab Number");
        addColumn("Money");
    }

    /**
     * this method adds the main columns/categories for the recurring
     * transaction table
     */
    public void addRecurringTransactionFormColumns() {
        addColumn("Time");
        addColumn("Amount");
        addColumn("Periodic Amount");
    }

    /**
     * this method adds the main columns/categories for the transaction table
     */
    public void addTransactionFormColumns() {
        addColumn("Time");
        addColumn("Amount");
    }

    /**
     *
     * @param name this method creates a column in the table based on the name
     * given
     */
    public void addColumn(String name) {
        model.addColumn(name);
    }

    /**
     * this method uses a current readout of the DriverHolder classes Driver
     * Collection to fill in the main site table rows
     */
    public void addMainFormRows() {
        List<Driver> driver = new ArrayList<>();
        Long cabID;

        for (int i = 0; i < driver.size(); i++) {
            if (driver.get(i).getCab() == null) {
                cabID = null;
            } else {
                cabID = driver.get(i).getCab().getCabID();
            }
            Object[] obj = new Object[]{
                driver.get(i).getNumber(), driver.get(i).getFirstName(),
                driver.get(i).getMiddleName(), driver.get(i).getLastName(),
                driver.get(i).getPhone(), cabID, driver.get(i).getMoney()
            };
            addRow(obj);
        }
    }

    /**
     * this method uses a current readout of the DriverHolder classes Driver
     * Collection, specifically one driver based on its found location on the
     * driver list (here labling that para as int location), and fills a table
     * based on that drivers recurring transactions
     *
     * @param location
     */
    public void addRecurringTransactionFormRows(int location) {
        List<Driver> driver = new ArrayList<>();
        if (driver.get(location).getReurringTransactions() != null) {
            ArrayList<com.allcoware.actiontaximockup.RecurringTransaction> retrans = (ArrayList<com.allcoware.actiontaximockup.RecurringTransaction>) driver.get(location).getReurringTransactions();

            for (int i = 0; i < retrans.size(); i++) {
                Object[] obj = new Object[]{
                    retrans.get(i).getStartingInstant(),
                    retrans.get(i).getAmount(),
                    retrans.get(i).getPeriodicAmount()
                };
                addRow(obj);
            }
        }
    }

    /**
     * this method uses a current readout of the DriverHolder classes Driver
     * Collection, specifically one driver based on its found location on the
     * driver list (here labling that para as int location), and fills a table
     * based on that drivers transactions
     *
     * @param location
     */
    public void addTransactionFormRows(int location) {
        List<Driver> driver = new ArrayList<>();
        if (driver.get(location).getTransaction() != null) {
            ArrayList<com.allcoware.actiontaximockup.Transaction> trans = (ArrayList<com.allcoware.actiontaximockup.Transaction>) driver.get(location).getTransaction();

            for (int i = 0; i < trans.size(); i++) {
                Object[] obj = new Object[]{
                    trans.get(i).getInstant(),
                    trans.get(i).getAmount()
                };
                addRow(obj);
            }
        }
    }

    /**
     * this method adds rows to the table
     *
     * @param obj
     */
    public void addRow(Object[] obj) {
        model.addRow(obj);
    }

}
