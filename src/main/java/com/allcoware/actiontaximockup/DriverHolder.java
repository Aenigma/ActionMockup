package com.allcoware.actiontaximockup;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;

/**
 * this class was created to hold the collection of drivers, not sure whether
 * it's doing its job well enough
 *
 * @author alfred
 */
public class DriverHolder {

    private static ArrayList<Driver> driver = new ArrayList<Driver>();

    public DriverHolder() {
    }

    public void test() {
        Driver bob = new Driver();
        bob.setPhone("445");
        bob.setLastName("Bob");
        bob.setNumber(456);

        //Transaction trans=new Transaction();
        // trans.setInstant(Instant.MIN);
        //CustomMoney mon=new CustomMoney("345");
        //trans.setAmount(mon);  
        //System.out.println(trans.getAmount());
        //bob.addTransaction(trans);
        driver.add(bob);

        Driver sally = new Driver();
        sally.setPhone("123");
        sally.setLastName("Sally");
        sally.setNumber(897);
        // sally.addTransaction(trans);
        driver.add(sally);

    }

    public void addNewDriver(Driver d) {
        driver.add(d);
    }

    public static ArrayList getList() {
        return driver;
    }

    public void addDriver(Driver dri) {
        driver.add(dri);
    }

}
