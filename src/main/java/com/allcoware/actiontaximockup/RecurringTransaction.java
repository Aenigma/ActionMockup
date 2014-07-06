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
package com.allcoware.actiontaximockup;

import java.time.Instant;

/**
 * This class holds the information on a driver's recurring transaction's.
 *
 * @author alfred
 */
public class RecurringTransaction {

    private java.time.Instant startingInstant;
    CustomMoney capAmount;
    CustomMoney periodicAmount;

    /**
     * Retrieves stating instant/time
     *
     * @return starting instant
     */
    public java.time.Instant getStartingInstant() {
        return startingInstant;
    }

    /**
     * Sets stating instant/time
     *
     * @param startingInstant
     */
    public void setStartingInstant(Instant startingInstant) {
        this.startingInstant = startingInstant;
    }

    /**
     * Retrieves cap amount
     *
     * @return cap amount
     */
    public CustomMoney getAmount() {
        return capAmount;
    }

    /**
     * Sets cap amount
     *
     * @param capAmount
     */
    public void setCapAmount(CustomMoney capAmount) {
        this.capAmount = capAmount;
    }

    /**
     * Retrieves periodic amount
     *
     * @return periodic amount
     */
    public CustomMoney getPeriodicAmount() {
        return periodicAmount;
    }

    /**
     * Sets periodic amount
     *
     * @param periodicAmount
     */
    public void setPeriodicAmount(CustomMoney periodicAmount) {
        this.periodicAmount = periodicAmount;
    }
}
