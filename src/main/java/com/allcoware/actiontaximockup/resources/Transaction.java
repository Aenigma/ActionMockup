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

import java.time.Instant;
import java.util.Objects;

/**
 * This class holds the information on a driver's transaction.
 *
 * @author alfred
 */
public class Transaction {

    private java.time.Instant transactionInstant;
    CustomMoney amount;

    /**
     * This method returns the transaction instant/time
     *
     * @return transaction instant
     */
    public java.time.Instant getInstant() {
        return transactionInstant;
    }

    /**
     * This method sets the transaction instant/time
     *
     * @param transactionInstant
     */
    public void setInstant(Instant transactionInstant) {
        this.transactionInstant = transactionInstant;
    }

    /**
     * This method returns the amount (receipt of transaction)
     *
     * @return amount
     */
    public CustomMoney getAmount() {
        return amount;
    }

    /**
     * This method sets amount (receipt of transaction)
     *
     * @param amount
     */
    public void setAmount(CustomMoney amount) {
        this.amount = amount;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.transactionInstant);
        hash = 53 * hash + Objects.hashCode(this.amount);
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
        final Transaction other = (Transaction) obj;
        if (!Objects.equals(this.transactionInstant, other.transactionInstant)) {
            return false;
        }
        if (!Objects.equals(this.amount, other.amount)) {
            return false;
        }
        return true;
    }
    
    
}
