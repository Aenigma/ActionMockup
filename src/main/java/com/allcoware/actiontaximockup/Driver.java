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

import java.util.Collection;

/**
 * This class holds the main information for a driver being phone number, full
 * name, cap ID, and transactions.
 *
 * @author alfred
 */
public class Driver {
    
    private long number;
    com.allcoware.actiontaximockup.Cab cab;
    CustomMoney money;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phone;
    private Collection<Transaction> transaction;
    private Collection<RecurringTransaction> recurringTransactions;

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
    public com.allcoware.actiontaximockup.Cab getCab() {
        return cab;
    }

    /**
     * Sets driver's cab info
     *
     * @param cab
     */
    public void setCab(com.allcoware.actiontaximockup.Cab cab) {
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
     * Retrieves driver's transaction
     *
     * @return driver's transaction
     */
    public Collection<Transaction> getTransaction() {
        return transaction;
    }

    /**
     * Sets driver's transaction
     *
     * @param transaction
     */
    public void setTransaction(Collection<Transaction> transaction) {
        this.transaction = transaction;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void addTransaction(Transaction t) {
        transaction.add(t);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void removeTransaction(Transaction t) {
        transaction.remove(t);
    }

    /**
     * Retrieves driver's recurring transaction
     *
     * @return driver's recurring transaction's
     */
    public Collection<RecurringTransaction> getReurringTransactions() {
        return recurringTransactions;
    }

    /**
     * Sets driver's recurring transaction
     *
     * @param recurringTransaction
     */
    public void setRecurringTransaction(Collection<RecurringTransaction> recurringTransaction) {
        this.recurringTransactions = recurringTransaction;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void addRecurringTransaction(RecurringTransaction t) {
        recurringTransactions.add(t);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void removeRecurringTransaction(RecurringTransaction t) {
        recurringTransactions.remove(t);
    }
}
