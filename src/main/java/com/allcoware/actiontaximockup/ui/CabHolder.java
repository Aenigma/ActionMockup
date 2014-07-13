package com.allcoware.actiontaximockup.ui;

import com.allcoware.actiontaximockup.Cab;
import java.util.ArrayList;

/**
 * this class was created to hold the collection of cabs, not sure whether it's
 * doing its job well enough
 *
 * @author alfred
 */
public class CabHolder {

    private static ArrayList<Cab> cab = new ArrayList<Cab>();

    public CabHolder() {

    }

    public void test() {
        Cab tim = new Cab();
        tim.setCabID(123);
        cab.add(tim);
    }

    public static ArrayList getList() {
        return cab;
    }

    public void addDriver(Cab ca) {
        cab.add(ca);
    }
}
