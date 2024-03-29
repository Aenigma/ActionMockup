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
package com.allcoware.actiontaximockup.resources;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import static java.time.temporal.ChronoUnit.*;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * This class holds the information on a driver's recurring transaction's.
 *
 * @author alfred
 */
@Entity
public class RecurringTransaction implements Serializable {

    // TODO: add suport for creating Transactions
    @Id
    @GeneratedValue
    private long id;
    
    private Instant startingInstant;
    private Duration timeToPay;
    private CustomMoney capAmount;
    private CustomMoney periodicAmount;

    /**
     * Creates an instance starting from now, with a duration of one day, $0 to
     * pay, and $0 paid periodically
     */
    public RecurringTransaction() {
        this(Instant.now(), Duration.of(1, DAYS), new CustomMoney(new BigDecimal(0)), new CustomMoney(new BigDecimal(0)));
    }

    /**
     * Creates an instance with the specified properties
     * 
     * @param startingInstant when payments should start
     * @param timeToPay time to wait until the next payment is demanded
     * @param capAmount maximum amount to pay
     * @param periodicAmount time to wait until the next payment is scheduled
     */
    public RecurringTransaction(Instant startingInstant, Duration timeToPay, CustomMoney capAmount, CustomMoney periodicAmount) {
        this.startingInstant = startingInstant;
        this.timeToPay = timeToPay;
        this.capAmount = capAmount;
        this.periodicAmount = periodicAmount;
    }

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
     * Gets time to wait until the next payment is demanded
     * @return time to wait until the next payment is demanded 
     */
    public Duration getTimeToPay() {
        return timeToPay;
    }

    /**
     * Sets time to wait until the next payment is demanded
     * 
     * @param timeToPay time to wait until the next payment is demanded
     */
    public void setTimeToPay(Duration timeToPay) {
        this.timeToPay = timeToPay;
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
     * @param amount
     */
    public void setAmount(CustomMoney amount) {
        this.capAmount = amount;
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

    @Override
    public String toString() {
        return "RecurringTransaction{" + "startingInstant=" + startingInstant +
                ", timeToPay=" + timeToPay + ", capAmount=" + capAmount +
                ", periodicAmount=" + periodicAmount + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.startingInstant);
        hash = 79 * hash + Objects.hashCode(this.timeToPay);
        hash = 79 * hash + Objects.hashCode(this.capAmount);
        hash = 79 * hash + Objects.hashCode(this.periodicAmount);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RecurringTransaction other = (RecurringTransaction) obj;
        if (!Objects.equals(this.startingInstant, other.startingInstant)) {
            return false;
        }
        if (!Objects.equals(this.timeToPay, other.timeToPay)) {
            return false;
        }
        if (!Objects.equals(this.capAmount, other.capAmount)) {
            return false;
        }
        if (!Objects.equals(this.periodicAmount, other.periodicAmount)) {
            return false;
        }
        return true;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    

}
