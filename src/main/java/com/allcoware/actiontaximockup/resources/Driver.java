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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * This class holds the main information for a driver being phone number, full
 * name, cap ID, and transactions.
 *
 * @author alfred
 * @author Kevin Raoofi
 */
@Entity
public class Driver implements Serializable {

    @Id
    private long number;
    @OneToOne(cascade = {CascadeType.PERSIST})
    private Cab cab;
    private CustomMoney money;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phone;

    @ElementCollection(targetClass = Transaction.class)
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Collection<Transaction> transactions;

    @ElementCollection(targetClass = RecurringTransaction.class)
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Collection<RecurringTransaction> recurringTransactions;

    public Driver() {
        this.transactions = new ArrayList<>();
        this.recurringTransactions = new ArrayList<>();
    }

    /**
     * Copy constructor. Values created from this object should be a deep copy
     * and when {@link #equals(java.lang.Object)} is used, return true.
     *
     * @param d
     */
    public Driver(Driver d) {
        this.cab = d.cab;
        this.firstName = d.firstName;
        this.lastName = d.lastName;
        this.middleName = d.middleName;
        this.money = d.money;
        this.number = d.number;
        this.phone = d.phone;
        this.recurringTransactions = new ArrayList<>(d.recurringTransactions);
        this.transactions = new ArrayList<>(d.transactions);
    }

    /**
     * Retrieves driver's ID number
     *
     * @return driver's number
     */
    public long getNumber() {
        return number;
    }

    /**
     * Sets driver's ID number
     *
     * @param number
     */
    public void setNumber(long number) {
        this.number = number;
    }

    /**
     * Retrieves driver's cab info
     *
     * @return driver's Cab
     */
    public Cab getCab() {
        return cab;
    }

    /**
     * Sets driver's cab info
     *
     * @param cab
     */
    public void setCab(Cab cab) {
        this.cab = cab;
    }

    /**
     * Returns driver's money info
     *
     * @return money
     */
    public CustomMoney getMoney() {
        return money;
    }

    /**
     * Set's driver's money info
     *
     * @param money
     */
    public void setMoney(CustomMoney money) {
        this.money = money;
    }

    /**
     * Retrieves driver's first name
     *
     * @return driver's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set's driver's first name
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Retrieves driver's middle name
     *
     * @return driver's middle name
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Set's driver's middle name
     *
     * @param middleName
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * Retrieves driver's last name
     *
     * @return driver's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set's driver's last name
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Retrieves driver's phone number
     *
     * @return driver's phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Set's driver's phone number
     *
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Retrieves driver's transactions
     *
     * @return driver's transactions
     */
    public Collection<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }

    /**
     * Sets driver's transactions
     *
     * @param transactions
     */
    public void setTransactions(Collection<Transaction> transactions) {
        this.transactions = new ArrayList<>(transactions);
    }

    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    public void removeTransaction(Transaction t) {
        transactions.remove(t);
    }

    /**
     * Retrieves driver's recurring transactions
     *
     * @return driver's recurring transactions
     */
    public Collection<RecurringTransaction> getRecurringTransactions() {
        return new ArrayList<>(recurringTransactions);
    }

    /**
     * Sets driver's recurring transactions
     *
     * @param recurringTransactions
     */
    public void setRecurringTransactions(
            Collection<RecurringTransaction> recurringTransactions) {
        this.recurringTransactions = new ArrayList<>(recurringTransactions);
    }

    public void addRecurringTransaction(RecurringTransaction t) {
        recurringTransactions.add(t);
    }

    public void removeRecurringTransaction(RecurringTransaction t) {
        recurringTransactions.remove(t);
    }

    @Override
    public String toString() {
        return "Driver{" + "number=" + number + ", cab=" + cab + ", money="
                + money + ", firstName=" + firstName + ", middleName="
                + middleName + ", lastName=" + lastName + ", phone=" + phone
                + ", transactions=" + transactions + ", recurringTransactions="
                + recurringTransactions + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (int) (this.number ^ (this.number >>> 32));
        hash = 89 * hash + Objects.hashCode(this.cab);
        hash = 89 * hash + Objects.hashCode(this.money);
        hash = 89 * hash + Objects.hashCode(this.firstName);
        hash = 89 * hash + Objects.hashCode(this.middleName);
        hash = 89 * hash + Objects.hashCode(this.lastName);
        hash = 89 * hash + Objects.hashCode(this.phone);
        hash = 89 * hash + Objects.hashCode(this.transactions);
        hash = 89 * hash + Objects.hashCode(this.recurringTransactions);
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
        final Driver other = (Driver) obj;
        if (this.number != other.number) {
            return false;
        }
        if (!Objects.equals(this.cab, other.cab)) {
            return false;
        }
        if (!Objects.equals(this.money, other.money)) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.middleName, other.middleName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        if (!Objects.equals(this.transactions, other.transactions)) {
            return false;
        }
        if (!Objects.equals(this.recurringTransactions,
                other.recurringTransactions)) {
            return false;
        }
        return true;
    }
}
