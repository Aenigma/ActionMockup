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

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import static java.time.temporal.ChronoUnit.*;
import java.util.Objects;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * This class holds the information on a driver's recurring transaction's.
 *
 * @author alfred
 */
@Entity
@Access(AccessType.FIELD)
public class RecurringTransaction implements Serializable {

    // TODO: add suport for creating Transactions
    @Id
    @GeneratedValue
    private long id;

    @Transient
    private Instant startingInstant;

    @Transient
    private Duration timeToPay;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "amount", column = @Column(name = "CAPAMOUNT"))})
    private CustomMoney capAmount;
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "amount", column = @Column(name = "PERIODICAMOUNT"))})
    private CustomMoney periodicAmount;

    /**
     * Creates an instance starting from now, with a duration of one day, $0 to
     * pay, and $0 paid periodically
     */
    public RecurringTransaction() {
        this(Instant.now(), Duration.of(1, DAYS), new CustomMoney(
                new BigDecimal(0)), new CustomMoney(new BigDecimal(0)));
    }

    /**
     * Creates an instance with the specified properties
     *
     * @param startingInstant when payments should start
     * @param timeToPay       time to wait until the next payment is demanded
     * @param capAmount       maximum amount to pay
     * @param periodicAmount  time to wait until the next payment is scheduled
     */
    public RecurringTransaction(Instant startingInstant, Duration timeToPay,
            CustomMoney capAmount, CustomMoney periodicAmount) {
        this.startingInstant = startingInstant;
        this.timeToPay = timeToPay;
        this.capAmount = capAmount;
        this.periodicAmount = periodicAmount;
    }

    public RecurringTransaction(RecurringTransaction r) {
        this.id = r.id;
        this.startingInstant = r.startingInstant;
        this.timeToPay = r.timeToPay;
        this.capAmount = r.capAmount;
        this.periodicAmount = r.periodicAmount;
    }
    
    

    /**
     * Retrieves stating instant/time
     *
     * @return starting instant
     */
    public Instant getStartingInstant() {
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

    @Access(AccessType.PROPERTY)
    @Column(name = "STARTINGTIME")
    @JsonIgnore
    public Timestamp getJPAStartingTime() {
        if (startingInstant == null) {
            return null;
        }
        return Timestamp.from(startingInstant);
    }

    public void setJPAStartingTime(Timestamp d) {
        this.startingInstant = d.toInstant();
    }

    /**
     * Gets time to wait until the next payment is demanded
     *
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

    @Access(AccessType.PROPERTY)
    @Column(name = "TIMETOPAY")
    @JsonIgnore
    public long getMillisToPay() {
        return timeToPay.toMillis();
    }

    public void setMillisToPay(long millis) {
        this.timeToPay = Duration.ofMillis(millis);
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
        return "RecurringTransaction{" + "startingInstant=" + startingInstant
                + ", timeToPay=" + timeToPay + ", capAmount=" + capAmount
                + ", periodicAmount=" + periodicAmount + '}';
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
